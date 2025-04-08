import java.io.*;
import java.util.*;

/*
 * 재귀함수 기저조건 설정 잘 하기!!! (ㅠ_ㅠ)
 */

public class 왕실의기사대결2 {
	
	static final int EMPTY = 0, TRICK = 1, WALL = 2;
	static BufferedReader br;
	static StringTokenizer st;
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
		
		void move(int d) {
			this.r += dr[d];
			this.c += dc[d];
		}
	}
	
	static class Knight {
		Point p;
		int h, w, k, tCnt; // 구역 안에 있는 트릭 개수
		int attackCnt;
		boolean isOut;
		
		public Knight(Point p, int h, int w, int k) {
			this.p = p;
			this.h = h;
			this.w = w;
			this.k = k;
		}
	}

	static boolean moveFlag;
	static int L, N, Q;
	static int[][] map, kMap; // map: 기본 맵의 정보, kMap: 기사들의 위치 정보
	static Knight[] knights;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static HashSet<Integer> moveSet;
	
	public static void main(String[] args) throws Exception {
		initInput();
		System.out.println(solution());
	}
	
	static int solution() throws Exception {
		
		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int i = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			// i번 기사에게 방향 d로 한 칸 이동
			move(i, d);
		}
		return getLiveKnightAttackCnt();
	}
	
	static int getLiveKnightAttackCnt() {
		int result = 0;
		
		for(Knight k: knights) {
			if(k == null || k.isOut) continue;
			
			result += k.attackCnt;
		}
		return result;
	}
	
	// i번 기사에게 방향 d로 한 칸 이동
	// 만약 이동하려는 위치에 다른 기사가 있다면 그 기사도 함께 연쇄적으로 밀려남
	// 기사가 이동하려는 방향 끝에 벽이 있다면 모든 기사는 이동할 수 없게 됨
	// 일단 어떻게 연쇄적으로 이동해야 되는지 확인하고, 맨 끝에 벽이 있는지 확인
	static void move(int i, int d) {
		// 이미 사라진 기사에게 명령을 내리면 아무런 반응 X
		if(knights[i].isOut) return;
		
		moveSet = new HashSet<>();
		moveFlag = true;
		canMove(i, d);
		
		if(!moveFlag) return; // 움직일 수 없으면 X
		
		// 대결 대미지
		// 밀려난 기사들은 피해를 입게 됨
		// 기사가 이동한 곳에서 w x h 직사각형 내에 놓여있는 함정의 수만큼 피해를 입게 됨
		// 기사에 대한 정보 업데이트하기
		for(int idx: moveSet) {
			// 기준점을 d방향으로 옮기기
			Knight current = knights[idx];
			
			// 상, 하로 움직이는 경우
			if(d == 0 || d == 2) {
				
				int rr = current.p.r; // 아래로 움직이는 경우 맨 위에 행이 없어짐
				if(d == 0) rr += current.h - 1; // 위로 움직일 경우 맨 아래 행이 없어짐
				
				int cr = current.p.r;
				if(d == 0) cr -= 1; // 위로 움직이는 경우 맨 위에 행 -1 행이 생겨남
				else cr += current.h; // 아래로 움직이는 경우 맨 아래행 +1 행이 생겨남
				
				for(int c = current.p.c; c < current.p.c + current.w; c++) {
					if(map[rr][c] == TRICK) current.tCnt--;
					if(kMap[rr][c] == idx) kMap[rr][c] = 0;
					
					kMap[cr][c] = idx;
					if(map[cr][c] == TRICK) current.tCnt++;
				}
			} 

			// 좌, 우로 움직이는 경우
			if(d == 1 || d == 3) {
				// d == 1: 오른쪽, d == 3: 왼쪽
				int rc = current.p.c;
				
				// 왼쪽으로 가면 맨 오른쪽 열이 사라짐
				if(d == 3) rc += (current.w - 1);
				
				int cc = current.p.c;
				// 오른쪽으로 가면 맨 오른쪽 열 + 1 열이 생김
				if(d == 1) cc += current.w;
				else cc -= 1;
				
				for(int r = current.p.r; r < current.p.r + current.h; r++) {
					if(map[r][rc] == TRICK) current.tCnt--;
					if(kMap[r][rc] == idx) kMap[r][rc] = 0;
					
					kMap[r][cc] = idx;
					if(map[r][cc] == TRICK) current.tCnt++;
				}
			}
			
			current.p.move(d);

			// idx 체력 update하기
			if(idx != i) {
				current.k -= current.tCnt;
				current.attackCnt += current.tCnt;
			}
			
			if(current.k <= 0) {
				current.isOut = true;
				removeKMap(idx);
			}
		}
	}
	
	// kMap에서 idx에 대한 정보 없애기
	static void removeKMap(int idx) {
		Knight current = knights[idx];
		
		for(int r = current.p.r; r < current.p.r + current.h; r++) {
			for(int c = current.p.c; c < current.p.c + current.w; c++) {
				kMap[r][c] = 0;
			}
		}
	}
	
	// 연쇄적으로 움직일 수 있는지 확인
	static void canMove(int i, int d) {
		if(!moveFlag) return;
		
		Knight current = knights[i];
		moveSet.add(i); // 움직이는 애들에 추가해주기
		
		HashSet<Integer> nearSet = new HashSet<Integer>();
		
		// 상, 하 일때
		if(d == 0 || d == 2) {
			for(int c = current.p.c; c < current.p.c + current.w; c++) {
				int nr = current.p.r;
				
				if(d == 0) nr -= 1;
				if(d == 2) nr += current.h;
				
				if(nr < 0 || nr >= L || map[nr][c] == WALL) {
					moveFlag = false;
					return;
				}
				if(kMap[nr][c] > 0) nearSet.add(kMap[nr][c]);
			}
		}
		
		// 좌, 우 일때
		if(d == 1 || d == 3) {
			
			for(int r = current.p.r; r < current.p.r + current.h; r++) {
				int nc = current.p.c;
				if(d == 1) nc += current.w;
				if(d == 3) nc -= 1;
				
				// 맨 끝에 벽이 있다면 false 반환
				if(nc < 0 || nc >= L || map[r][nc] == WALL) {
					moveFlag = false;
					return;
				}
				
				// 근처에 있는거
				if(kMap[r][nc] > 0) nearSet.add(kMap[r][nc]);
			}
		}
			
		
		for(int next: nearSet) canMove(next, d);
	}

	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/왕실의기사대결.txt"));
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken()); // 체스판의 크기
		N = Integer.parseInt(st.nextToken()); // 기상들의 수
		Q = Integer.parseInt(st.nextToken()); // 명령의 수
		
		// map에 대한 정보 받기! ------------------------
		map = new int[L][L];
		
		for(int r = 0; r < L; r++) {
			st = new StringTokenizer(br.readLine());
			
			for(int c = 0; c < L; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 기사 정보 입력 받기! ---------------------------
		knights = new Knight[N + 1]; // 1번부터 N번까지니까 배열은 N + 1
		kMap = new int[L][L];
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			 knights[i] = new Knight(new Point(r, c), h, w, k);
			 
			 // knights[i]의 정보를 kMap에 표시하기
			 markKMap(i);
		}
	}


	private static void markKMap(int idx) {
		Knight knight = knights[idx];
		
		int sr = knight.p.r, sc = knight.p.c;
		int h = knight.h, w = knight.w;
	
		for(int r = sr; r < sr + h; r++) {
			for(int c = sc; c < sc + w; c++) {
				kMap[r][c] = idx;
				
				if(map[r][c] == TRICK) knight.tCnt++;
			}
		}
	}
}
