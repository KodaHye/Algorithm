import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 불
 */
public class BOJ5427 {
	static class Point {
		int r;
		int c;
		int t;
		char s;
		public Point(int r, int c, int t, char s) {
			super();
			this.r = r;
			this.c = c;
			this.t = t;
			this.s = s;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T, w, h, time;
	static String result;
	static char map[][];
	static Queue<Point> queue;
	static boolean fire[][], sang[][];
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 0; test_case < T; test_case++) {
			st = new StringTokenizer(br.readLine());
			
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			time = 0;
			map = new char[h][w];
			queue = new LinkedList<>();
			
			for (int r = 0; r < map.length; r++) {
				String input = br.readLine();
				for (int c = 0; c < map[r].length; c++) {
					map[r][c] = input.charAt(c);
				}
			}
			
			// 불을 먼저 Queue에 놔줘야 나중에 사람이 움직이지 못할 수 있음
			// 어짜피 Queue에는 불이 확산되기 전 사람의 좌표를 가지고 있으므로 불 먼저 Queue에 담아주기!
			for(int r = 0; r < map.length; r++) {
				for (int c = 0; c < map[r].length; c++) {
					if(map[r][c] == '*') queue.add(new Point(r, c, 0, map[r][c]));
				}
			}
			
			for(int r = 0; r < map.length; r++) {
				for (int c = 0; c  < map[r].length; c ++) {
					if(map[r][c] == '@') queue.add(new Point(r, c, 0, map[r][c]));
				}
			}
			
			
			
			result = bfs() == true ? String.valueOf(time + 1) : "IMPOSSIBLE";
			
			sb.append(result + "\n");
		}
		System.out.println(sb);
	}

	private static void print(char[][] map) {
		for (int r = 0; r < map.length; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	}

	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	private static boolean bfs() {
		sang = new boolean[h][w];
		fire = new boolean[h][w];
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(p.s == '@') {
					sang[p.r][p.c] = true;
					if(!check(nr, nc)) {
						time = p.t;
						return true;
					}
					if(!sang[nr][nc] && map[nr][nc] != '#' && map[nr][nc] != '*'){
						map[nr][nc] = p.s;
						sang[nr][nc] = true;
						queue.add(new Point(nr, nc, p.t + 1, p.s));
					}
				}
				
				if(p.s == '*') {
					fire[p.r][p.c] = true;
					if(!check(nr, nc)) continue;
					if(!fire[nr][nc] && map[nr][nc] != '#') {
						map[nr][nc] = p.s;
						fire[nr][nc] = true;
						queue.add(new Point(nr, nc, p.t + 1, p.s));
					}
				}
			}
		}
		return false;
	}
	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < h && nc >= 0 && nc < w;
	}
}
