import java.io.*;
import java.util.*;

/*
 * 조건 주의하기!
 * ~하면 즉시 탈출, ~하면 게임이 끝남 
 * → 이런 조건은 while 제어로 하지 말고, while문 안에서 if()break로 처리하기 
 */

public class 메이즈러너 {
	
	static int N, M, K;
	static int[][] map;
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		public void move(int d) {
			this.r += dr[d];
			this.c += dc[d];
		}
	}
	static class Person {
		Point p;
		boolean isExit;
		
		public Person(Point p) {
			this.p = p;
		}
	}
	static Point e;
	static Person[] people;
	static HashSet<Integer>[][] peopleIdxMap;
	static int exitPeopleCnt, totalDisSum;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		initInput();
		
		int time = 1;
		
		while(time <= K) {
			
			// 1초마다 모든 참가자 한 칸씩 움직이기
			movePeople();
			if(exitPeopleCnt == M) break;
			
			// 미로 회전하기
			rotate();
			time++;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(totalDisSum).append("\n")
			.append(e.r + 1).append(" ").append(e.c + 1);
		
		System.out.println(sb);
	}
	
	static void rotate() {
		
		int[] recInfo = getRecInfo(); // [0]: r, [1]: c, [2]: size
		
		rotateExit(recInfo);
		rotateMap(recInfo);
	}
	
	static void rotateMap(int[] recInfo) {
		
		int rr = recInfo[0], rc = recInfo[1], rsize = recInfo[2];
		
		HashSet<Integer>[][] tSet = new HashSet[rsize][rsize];
		
		for(int r = 0; r < rsize; r++) {
			for(int c = 0; c < rsize; c++) tSet[r][c] = new HashSet<>();
		}
		
		int[][] tMap = new int[rsize][rsize];
			
		for(int r = 0; r < rsize; r++) {
			for(int c = 0; c < rsize; c++) {
				
 				tMap[r][c] = map[rsize - 1 - c + rr][r + rc];
				tSet[r][c] = peopleIdxMap[rsize - 1 - c + rr][r + rc];
				
				if(tMap[r][c] > 0) tMap[r][c]--;
			}
		}
		
		for(int r = 0; r < rsize; r++) {
			for(int c = 0; c < rsize; c++) {
				peopleIdxMap[r + rr][c + rc] = tSet[r][c];
				map[r + rr][c + rc] = tMap[r][c];
				
				if(peopleIdxMap[r + rr][c + rc].size() > 0) {
					for(int idx: peopleIdxMap[r + rr][c + rc]) {
						people[idx].p.r = r + rr;
						people[idx].p.c = c + rc;
					}
				}
			}
		}
	}
	
	static void rotateExit(int[] recInfo) {
		
		int r = e.r - recInfo[0], c = e.c - recInfo[1]; // (0, 0)으로 기준 맞춰주기
		
		int size = recInfo[2];
		
		int nr = c + recInfo[0], nc = size - 1 - r + recInfo[1];
		
		e.r = nr;
		e.c = nc;
	}
	
	static int[] getRecInfo() {
		// 가장 크기가 작은 크기를 갖는 정사각형,, 
		// 좌상단 r 좌표가 작은 것이 우선시, 그래도 같으면 c좌표가 작은게 우선시
		// 미로가 회전하면... 사람들도 같이 회전해줘야 됨!!
		// 90도로 회전하고,, 회전된 벽의 내구도는 1씩 깍임
		
		int rr = 0, rc = 0, rsize = 0;
		
		L: for(int s = 1; s < N + 1; s++) { // 정사각형의 크기
			
			for(int sr = 0; sr < N - s + 1; sr++) {
				for(int sc = 0; sc < N - s + 1; sc++) {
					
					boolean findPeople = false;
					boolean findExit = false;
					
					// 한 명 이상의 참가자와 출구를 포함한 가장 작은 정사각형
					for(int r = sr; r < sr + s; r++) {
						for(int c = sc; c < sc + s; c++) {
							if(peopleIdxMap[r][c].size() > 0) findPeople = true;
							if(r == e.r && c == e.c) findExit = true;
							
							// 사람도 찾았고, 출구도 찾았으며
							if(findPeople && findExit) {
								rr = sr;
								rc = sc;
								rsize = s;
								
								break L;
							}
						}
					}
				}
			}
		}
		
		return new int[] {rr, rc, rsize};
	}
	static void movePeople() {
		
		// 상, 하, 좌, 우로 움직일 수 있음 (O)
		// 벽이 없는 곳으로 (O)
		// 움직인 칸은 머물던 칸보다 출구까지의 최단거리가 가까워야 됨 (O)
		// 2개 이상이라면 상, 하로 움직이는 것을 우선시 (O)
		// 움직일 수 없으면 움직이지 않음 (O)
		// 2명 이상의 참가자가 동시에 있을 수 있음 (O)
		for(int i = 0; i < people.length; i++) {
			
			Person current = people[i];
			if(current.isExit) continue; // 나간 사람은 움직이지 않음
			
			int md = -1;
			int cDis = getDis(current.p.r, current.p.c, e.r, e.c);
			
			for(int d = 0; d < 4; d++) {
				int nr = current.p.r + dr[d];
				int nc = current.p.c + dc[d];
				
				if(!check(nr, nc) || isWall(nr, nc)) continue;
				int nDis = getDis(nr, nc, e.r, e.c);
				
				// 가까워진다면 이 방향으로 가는걸로
				if(nDis < cDis) {
					cDis = nDis;
					md = d;
				}
			}
			
			// 움직일 수 없으면 움직이지 않음
			if(md == -1) continue;
			
			totalDisSum++; // 사람들이 움직이는 거리 1 씩 증가시키기
			
			peopleIdxMap[current.p.r][current.p.c].remove(i); // 현재 위치 정보 없애기
			current.p.move(md); // md 방향으로 움직이기
			peopleIdxMap[current.p.r][current.p.c].add(i); //움직인 위치로 업데이트하기
			
			// 출구에 도착한거면, 즉시 탈출!
			if(current.p.r == e.r && current.p.c == e.c) {
				current.isExit = true;
				exitPeopleCnt++;
				peopleIdxMap[current.p.r][current.p.c].remove(i);
			}
		}
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
	
	static boolean isWall(int r, int c) {
		return map[r][c] > 0 && map[r][c] < 10;
	}
	
	static int getDis(int r1, int c1, int r2, int c2) {
		return Math.abs(r1 - r2) + Math.abs(c1 - c2);
	}
	
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/메이즈러너.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 미로의 크기
		M = Integer.parseInt(st.nextToken()); // 참가자의 수
		K = Integer.parseInt(st.nextToken()); // 게임 시간
		
		people = new Person[M];
		peopleIdxMap = new HashSet[N][N];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) peopleIdxMap[r][c] = new HashSet<>();
		}
		
		map = new int[N][N];
		
		// 미로에 대한 정보 ------------------------------
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 살마에 대한 정보 ------------------------------
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			
			people[i] = new Person(new Point(r, c));
			peopleIdxMap[r][c].add(i);
		}
		
		st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		
		e = new Point(r, c); // 출구
	}
}
