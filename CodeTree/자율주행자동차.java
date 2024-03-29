import java.io.*;
import java.util.*;

public class 자율주행자동차 {
	static class Point {
		int r, c, dir;

		public Point(int r, int c, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
	static int result, map[][], dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
	static boolean check[][];
	static Point car;

	public static void main(String[] args) throws Exception {
		input();
		
		while(true) {
			
			int nr = car.r;
			int nc = car.c;
			int dir = car.dir;
			
			boolean move = false;
			for(int d = 1; d < 5; d++) {
				dir = dir - 1 < 0 ? 3: dir - 1;
				
				nr = car.r + dr[dir];
				nc = car.c + dc[dir];
				
				if(!check(nr, nc) || map[nr][nc] == 1 || check[nr][nc]) continue;
				
				if(map[nr][nc] == 0) {
					move = true;
					break;
				}
			}
			
			if(!move) {
				dir = (car.dir + 2) % 4;
				
				nr = car.r + dr[dir];
				nc = car.c + dc[dir];
				
				if(!check(nr, nc) || map[nr][nc] == 1) {
					nr -= dr[dir];
					nc -= dc[dir];
				}
			}
			
			if(nr == car.r && nc == car.c) break;
			if(move) {
				car.dir = dir;
			}
			car.r = nr;
			car.c = nc;
			check[nr][nc] = true;
		}
		
		cnt();
		System.out.println(result);
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < map.length && nc >=0 && nc < map[0].length;
	}

	private static void cnt() {
		for(int r = 0; r < check.length; r++) {
			for(int c = 0; c < check[0].length; c++) {
				if(check[r][c]) result++;
			}
		}
		
	}

	private static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = stoi(st);
		int m = stoi(st);
		
		map = new int[n][m];
		check = new boolean[n][m];
		
		st = new StringTokenizer(br.readLine());
		
		car = new Point(stoi(st), stoi(st), stoi(st));
		
		check[car.r][car.c] = true;
		
		for(int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < m; c++) {
				map[r][c] = stoi(st);
			}
		}
	}

	private static int stoi(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
