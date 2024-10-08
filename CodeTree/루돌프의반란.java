import java.io.*;
import java.util.*;

/*
 * 루돌프의 반란 
 * 잘못접근했던 부분
 * 기절은 2 턴 만큼 하는 것이기 때문에 += 2가 아니라 값을 2로 세팅해줘야 됨
 * recur() 부분에서 !check(nr, nc)에서 범위를 벗어났을 때, isDie 처리를 안해줘서 틀렸였음
 * 재귀함수에 대한 기저 설정 잘 하기
 */

public class 루돌프의반란 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r; 
			this.c = c;
		}
	}
	static class Santa {
		Point p;
		int score, kizeul;
		boolean isDie;
		public Santa(Point p) {
			this.p = p;
		}
	}
	static Point ru;
	static Santa[] santas;
	static int[][] map;
	static int N, M, P, C, D, dieSantaCnt;
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0}, dc = {-1, 0, 1, 1, 1, 0, -1, -1};
	static StringBuilder sb = new StringBuilder();
	
	static void solution() {

 		while(M-- > 0) {
			hillSanta();
			moveRu();		
			if(dieSantaCnt == P) break;
			moveSanta();
			if(dieSantaCnt == P) break;
			addScoreSanta();
		}
		
		for(Santa s: santas) {
			if(s == null) continue;
			sb.append(s.score +" ");
		}
		
		System.out.println(sb);
	}
	
	static void addScoreSanta() {
		for(Santa s: santas) {
			if(s == null || s.isDie) continue;
			s.score += 1;
		}
	}
	static void hillSanta() {
		for(Santa s: santas) {
			if(s == null || s.kizeul == 0) continue;
			s.kizeul -= 1;
		}
	}
	
	// (3) 산타의 움직임
	static void moveSanta() {
		// 1번부터 P번까지 순서대로 움직임
		for(int i = 1; i < santas.length; i++) {
			Santa s = santas[i];
			
			if(s.kizeul > 0 || s.isDie) continue; // 기절했거나 이미 게임에서 탈락한 산타 움직일 수 없음
			
			// 루돌프에게 가장 가까워지는 방향으로 한 칸 이동
			// 상하좌우 중 한 칸 (상, 우, 하, 좌)
			int minDis = getRuAndSantaDis(ru.r, ru.c, s.p.r, s.p.c);
			int toDir = 0;
			
			for(int d = 1; d < 8; d += 2) {
				int nr = s.p.r + dr[d];
				int nc = s.p.c + dc[d];
				
				// 다른 산타가 있는 칸이나 게임판 밖으로 움직일 수 없음
				if(!check(nr, nc) || map[nr][nc] != 0) continue;
				int dis = getRuAndSantaDis(ru.r, ru.c, nr, nc);
				
				if(dis < minDis) {
					minDis = dis;
					toDir = d;
				}
			}
			
			// 움직일 수 있는 칸이 없는 경우
			if(toDir == 0) continue;
			
			int santaR = s.p.r;
			int santaC = s.p.c;
			int nr = santaR + dr[toDir];
			int nc = santaC + dc[toDir];
			
			// santa 위치를 map에서 없애줘야 됨
			map[santaR][santaC] = 0;

			s.p.r = nr;
			s.p.c = nc;
			
			// 충돌
			// 산타가 움직여서 루돌프와 충돌한 경우
			if(nr == ru.r && nc == ru.c) {
				s.score += D;
				// 산타는 자신이 이동해온 반대 방향으로 D칸만큼 밀려남
				
				// 충돌 이후 산타 기절 상태
				santas[i].kizeul += 2;
				int moveBackDir = (toDir + 4) % 8;
				// 산타가 이동해온 반대 방향만큼 D칸 만큼 밀려나기
				moveBackSanta(i, moveBackDir, D);
				continue;
			}
			
			map[santaR][santaC] = 0;
			map[nr][nc] = i;
		}
	}
	
	// (2) 루돌프의 움직임
	static void moveRu() {
		// 가장 가까운 산타한게 1칸 돌진
		// 탈락하지 않은 산타여야 함
		
		int minDis = Integer.MAX_VALUE;
		int toSantaId = 0; // 돌진할 산타의 id 저장

		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				int santaId = map[r][c];
				if(santaId == 0 || santas[santaId].isDie) continue;
				
				int dis = getRuAndSantaDis(ru.r, ru.c, r, c);
				
				if(minDis < dis) continue;
				minDis = dis;
				toSantaId = santaId;
			}
		}
		
		// santaId가 0이라면 어떡하지?
		// 루돌프 1칸 움직이기
//		if(toSantaId == 0) return;
		minDis = Integer.MAX_VALUE;
		
		int santaR = santas[toSantaId].p.r;
		int santaC = santas[toSantaId].p.c;
		int toDir = 0; // 루돌프가 돌진할 방향
		
		for(int d = 0; d < 8; d++) {
			int nr = ru.r + dr[d];
			int nc = ru.c + dc[d];
			
			if(!check(nr, nc)) continue;
			int dis = getRuAndSantaDis(nr, nc, santaR, santaC);
			if(dis < minDis) {
				minDis = dis;
				toDir = d;
			}
		}
		
		// 루돌프의 위치 업데이트
		int nr = ru.r + dr[toDir];
		int nc = ru.c + dc[toDir];
		
		ru.r = nr;
		ru.c = nc;

		// 충돌
		// 루돌프가 움직여서 충돌이 일어난 경우
		if(map[nr][nc] != 0) {
			int santaId = map[nr][nc];
			
			map[nr][nc] = 0;
			santas[santaId].score += C;

			// 충돌 이후 산타 기절 상태
			santas[santaId].kizeul = 2;
			
			// 루돌프가 이동해온 방향만큼 C칸 만큼 밀려나기
//			System.out.println("루돌프랑 충돌해서 산타 움직인다");
			moveBackSanta(santaId, toDir, C);
		}
	}
	
	// santaId인 산타가 toDir 방향으로 dis칸 만큼 밀려나게 됨
	static void moveBackSanta(int santaId, int toDir, int dis) {
		int santaR = santas[santaId].p.r;
		int santaC = santas[santaId].p.c;
		map[santaR][santaC] = 0;
		
		int nr = santaR + dr[toDir] * dis;
		int nc = santaC + dc[toDir] * dis;
			
		// 게임판 밖이라면 산타 탈락
		if(!check(nr, nc)) {
			santas[santaId].isDie = true;
			dieSantaCnt += 1;
			return;
		}
		
		santas[santaId].p.r = nr;
		santas[santaId].p.c = nc;
		
		if(map[nr][nc] == 0) map[nr][nc] = santaId;

		// 상호작용
		else recur(santaId, nr, nc, toDir);
	}
	
	static void recur(int santaId, int r, int c, int d) {
		// 재귀호출에 대한 기저 부분 설정 잘 하기
		if(!check(r, c)) {
			santas[santaId].isDie = true;
			dieSantaCnt += 1;
			return;
		}
		
		int currentId = map[r][c];
		map[r][c] = santaId;
		
		santas[santaId].p.r = r;
		santas[santaId].p.c = c;

		if(currentId == 0) return;
		else {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			recur(currentId, nr, nc, d);
		}
	}
	// 산타와 루돌프 사이 거리를 구하는 함수
	static int getRuAndSantaDis(int r, int c, int rr, int cc) {
		return (r - rr) * (r - rr) + (c - cc) * (c - cc);
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
	}
	
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
	}
	
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/루돌프의반란.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 게임판의 크기
		M = Integer.parseInt(st.nextToken()); // 게임 턴 수
		P = Integer.parseInt(st.nextToken()); // 산타의 수
		C = Integer.parseInt(st.nextToken()); // 루돌프의 힘
		D = Integer.parseInt(st.nextToken()); // 산타의 힘
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		
		ru = new Point(r,  c);
		santas = new Santa[P + 1];
		map = new int[N][N];
		
		for(int i = 1; i < santas.length; i++) {
			st = new StringTokenizer(br.readLine());
			
			int id = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			
			santas[id] = new Santa(new Point(r, c));
			map[r][c] = id;
		}
		
//		printMap();
//		printSantaInfo();
	}
	
	static void printMap() {
		System.out.println("PrintMap=======================");
		for(int r = 0; r < map.length; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	}
	
	static void printSantaInfo() {
		System.out.println("Santas Info ====================");
		for(Santa s: santas) {
			if(s == null) continue;
			System.out.println(s.toString());
		}
	}
}
