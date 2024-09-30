import java.io.*;
import java.util.*;

/*
 * 마법사 상어와 복제
 */

public class BOJ23290 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1}, dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[][][] fishs, copy, tmp;
	static int M, S, canEatFishCnt;
	static Point shark;
	static int[] sharkNextStep;
	static int[][] smell, v;
	
	public static void main(String[] args) throws Exception {
		initInput();		

		while(S-- > 0) {
			magic();
			smellDecrease();
			moveFish();
			getSharkNextStep();
			moveShark();
			addFishList();
		}
		
		System.out.println(getFishCnt());
	}
	
	static void smellDecrease() {
		for(int r = 0; r < smell.length; r++) {
			for(int c = 0; c < smell[0].length; c++) {
				if(smell[r][c] == 0) continue;
				smell[r][c] -= 1;
			}
		}
	}

	static int getFishCnt() {
		int result = 0;
		for(int r = 0; r < 4; r++) {
			for(int c = 0; c < 4; c++) {
				for(int k = 0; k < 8; k++) {
					result += fishs[r][c][k];
				}
			}
		}
		return result;
	}
	
	static void addFishList() {
		for(int r = 0; r < 4; r++) {
			for(int c = 0; c < 4; c++) {
				for(int k = 0; k < 8; k++) {
					fishs[r][c][k] += copy[r][c][k];
				}
			}
		}
	}
	
	static void moveShark() {
		for(int d = 0; d < 3; d++) {
			int dir = getDir(sharkNextStep[d]);
			int nr = shark.r + dr[dir];
			int nc = shark.c + dc[dir];
				
			shark.r = nr;
			shark.c = nc;
			
			boolean isDone = false;
			for(int k = 0; k < 8; k++) {
				if(fishs[nr][nc][k] > 0) {
					isDone = true;
					fishs[nr][nc][k] = 0;
				}
			}
				
			if(isDone) smell[nr][nc] = 3;
		}
	}

	static void getSharkNextStep()  {
		canEatFishCnt = -1;
		v = new int[4][4];
		moveShark(shark.r, shark.c, new int[3], 0, 0);
	}

	static void moveShark(int r, int c, int[] sharkStep, int fish, int depth) {
		
		if(depth == 3) {
			if(canEatFishCnt < fish) {
				sharkNextStep = Arrays.copyOf(sharkStep, 3);
				canEatFishCnt = fish;
			}
			return;
		}
		
		for(int d = 1; d < 5; d++) {
			int nextDir = getDir(d);
			int nr = r + dr[nextDir];
			int nc = c + dc[nextDir];
			
			if(!check(nr, nc)) continue;
			
			sharkStep[depth] = d;
			
			if(v[nr][nc] == 0) {
				int add = 0;
				for(int k = 0; k < 8; k++) add += fishs[nr][nc][k];
				fish += add;
			}
			v[nr][nc]++;
			
			moveShark(nr, nc, sharkStep, fish, depth + 1);
			v[nr][nc]--;
			if(v[nr][nc] == 0) {
				int add = 0;
				for(int k = 0; k < 8; k++) add += fishs[nr][nc][k];
				fish -= add;
			}
		}
	}
	
	static int getDir(int d) {
		if(d == 1) return 2; // 상
		if(d == 2) return 0; // 좌
		if(d == 3) return 6; // 하
		return 4; // 우
	}
	static void magic() {
		copy = new int[4][4][8];
		for(int r = 0; r < 4; r++) {
			for(int c = 0; c < 4; c++) {
				copy[r][c] = Arrays.copyOf(fishs[r][c], 8);
			}
		}
	}
	
	static void moveFish() {
		tmp = new int[4][4][8];
		
		for(int r = 0; r < 4; r++) {
			for(int c = 0; c < 4; c++) {
				for(int k = 0; k < 8; k++) {
					if(fishs[r][c][k] == 0) continue;
					moveFish(r, c, k);
				}
			}
		}
		
		for(int r = 0; r < 4; r++) {
			for(int c = 0; c < 4; c++) {
				fishs[r][c] = Arrays.copyOf(tmp[r][c], 8);
			}
		}
	}
	
	static void moveFish(int r, int c, int k) {
		
		for(int d = 0; d < 8; d++) {
			int nd = ((k - d) + 8) % 8;
			int nr = r + dr[nd];
			int nc = c + dc[nd];
				
			if(!check(nr, nc) || existShark(nr, nc) || smell(nr, nc)) continue;
			tmp[nr][nc][nd] += fishs[r][c][k];
			return;
		}
		
		tmp[r][c][k] += fishs[r][c][k];
	}
	
	static boolean smell(int r, int c) {
		return smell[r][c] > 0 ? true: false;
	}
	
	static boolean existShark(int r, int c) {
		return shark.r == r && shark.c == c;
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < 4 && c >= 0 && c < 4;
	}

	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./BAEKJOON/input/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); // 물고기 수
		S = Integer.parseInt(st.nextToken()); // 마법 연습 횟수
		
		fishs = new int[4][4][8];
		copy = new int[4][4][8];
		
		sharkNextStep = new int[3];
		smell = new int[4][4];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			
			fishs[r][c][d]++;
		}
		
		st = new StringTokenizer(br.readLine());
		shark = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
	}
}