import java.io.*;
import java.util.*;

/*
 * 왕실의 기사 대결
 */

public class 왕실의기사대결 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
	}
	
	static class Knight {
		Point p;
		int h, w, k, demage;
		boolean isDie;
		
		public Knight(Point p, int h, int w, int k) {
			this.p = p;
			this.h = h; // 세로 길이
			this.w = w; // 가로 길이
			this.k = k; // 초기 체력
		}

		@Override
		public String toString() {
			return "Knight [p=" + p + ", h=" + h + ", w=" + w + ", k=" + k + ", demage=" + demage + ", isDie=" + isDie
					+ "]";
		}
		
	}
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1}; // 0: 상, 1: 우, 2: 하, 3: 좌
	static BufferedReader br;
	static StringTokenizer st;
	static int L, N, Q;
	static int[][] map, knightMap;
	static boolean[] checkMove;
	static Knight[] knights;
	
	static void solution() throws Exception {
		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int i = Integer.parseInt(st.nextToken()); // i번 기사에게
			int d = Integer.parseInt(st.nextToken()); // 방향 d로 한 칸 이동하라는 명령
			
			if(knights[i].isDie) continue; // 사라진 기사에게 명령을 내리면 아무런 반응이 없음
			moveKnight(i, d);
		}

		int result = 0;
		for(Knight n: knights) {
			if(n == null || n.isDie) continue;
			result += n.demage;
		}
		
		System.out.println(result);
	}
	
	static void moveKnight(int id, int dir) {
//		printMap();
//		System.out.println(id + "를 " + dir + "로 이동하는건");
		
		if(!canMove(id, dir)) {
//			System.out.println("못가요 ㅠㅠ");
			return;
		}
		
		int[][] tmp = new int[L][L]; // knightMap을 변경하기 위해 사용하는 임시 배열
		
		Knight start = knights[id];
		
		for(int i = 1; i < checkMove.length; i++) {
			Knight current = knights[i];
			
			if(checkMove[i]) {
				// 좌표 옮기기
				current.p.r += dr[dir];
				current.p.c += dc[dir];
			}
			
			int demage = 0;
			// tmp에 기사의 위치를 다시 그리는 부분
			for(int r = current.p.r; r < current.p.r + current.h; r++) {
				for(int c = current.p.c; c <current.p.c + current.w; c++) {
					if(i != id && map[r][c] == 1 && checkMove[i]) {
						knights[i].k -= 1;
						knights[i].demage += 1;
					}
					tmp[r][c] = i;
				}
			}
			
			if(i != id) {
				// 체력 감소
				if(knights[i].k <= 0) {
					// System.out.println(i + "기사의 체력이 감소했어요");
					knights[i].isDie = true;
					
					// tmp에서 다시 없애기
					for(int r = current.p.r; r < current.p.r + current.h; r++) {
						for(int c = current.p.c; c <current.p.c + current.w; c++) {
							tmp[r][c] = 0;
						}
					}
				}
			}
		}
		
		knightMap = tmp;
//		System.out.println("갈 수 있어요!");
	}
	
	static boolean canMove(int id, int dir) {
		checkMove = new boolean[N + 1];

		Deque<Point> q = new ArrayDeque<Point>();
		boolean[][] v = new boolean[L][L];
		
		// 시작점에서 기사의 좌표 모두 q에 넣어줌. bfs할 때, 이동 방향은 dir
		Knight start = knights[id];
		checkMove[id] = true;
		
		for(int r = start.p.r; r < start.p.r + start.h; r++) {
			for(int c = start.p.c; c <start.p.c + start.w; c++) {
				q.add(new Point(r, c));
				v[r][c] = true;
			}
		}
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			int nr = current.r + dr[dir];
			int nc = current.c + dc[dir];
			
			// 범위를 벗어나거나, 벽이 있으면 못감
			if(!check(nr, nc) || map[nr][nc] == 2) return false;
			
			int nextIdx = knightMap[nr][nc]; // knightMap을 통해 기사의 idx 찾기
			if(v[nr][nc] || nextIdx == 0 || checkMove[nextIdx]) continue;
			
			if(nextIdx != id) {
				Knight next= knights[nextIdx];
				checkMove[nextIdx] = true;
				
				for(int r = next.p.r; r < next.p.r + next.h; r++) {
					for(int c = next.p.c; c <next.p.c + next.w; c++) {
						q.add(new Point(r, c));
						v[r][c] = true;
					}
				}
			}
		}
		
		return true; 
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < L && c >= 0 && c < L;
	}
	
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
	}
	
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/왕실의기사대결.txt"));
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken()); // 체스판의 크기
		N = Integer.parseInt(st.nextToken()); // 기사의 수
		Q = Integer.parseInt(st.nextToken()); // 명령의 수
		
		// 0: 빈칸, 1: 함정, 2: 벽
		map = new int[L][L];
		knightMap = new int[L][L];
		
		knights = new Knight[N + 1];
		
		for(int r = 0; r < L; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < L; c++) map[r][c] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i < knights.length; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			knights[i] = new Knight(new Point(r, c), h, w, k);
			for(int kr = r; kr < r + h; kr++) {
				for(int kc = c; kc < c + w; kc++) {
					knightMap[kr][kc] = i;
				}
			}
		}
	}
	
	static void printKnightMap() {
		System.out.println("knightMap 출력 ======================");
		for(int r = 0; r < knightMap.length; r++) {
			System.out.println(Arrays.toString(knightMap[r]));
		}
	}
	
	static void printKnightsInfo() {
		System.out.println("Knights 정보 출력 ==================");
		for(Knight n: knights) {
			if(n == null) continue;
			System.out.println(n.toString());
		}
	}
	
	static void printMap() {
		System.out.println("map 출력 =====================");
		for(int r = 0; r < map.length; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	}
}
