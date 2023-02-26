import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 공주님을 구해라
 */
public class BOJ17836 {
	static class Point {
		int r;
		int c;
		int t;
		boolean hasGram;
		public Point(int r, int c, int t, boolean hasGram) {
			super();
			this.r = r;
			this.c = c;
			this.t = t;
			this.hasGram = hasGram;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, T, time, map[][];
	static boolean v[][][];
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		v = new boolean[2][N][M];
		
		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs(0, 0);
		
		if(!flag) System.out.println("Fail");
		else {
			if(time > T) {
				System.out.println("Fail");
			} else {
				System.out.println(time);
			}
		}
	}

	private static void bfs(int r, int c) {
		Queue<Point> queue = new LinkedList<>();
		
		queue.offer(new Point(r, c, 0, false));
		v[0][r][c] = true;
		
		while(!queue.isEmpty()) {
			Point point = queue.poll();
	
			if(point.r == N - 1 && point.c == M - 1) {
				flag = true;
				time = point.t;
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = point.r + dr[i];
				int nc = point.c + dc[i];
				
				if(!check(nr, nc)) continue;
				if(!point.hasGram) { // 그램을 가지고 있지 않을 경우
					if(!v[0][nr][nc] && map[nr][nc] == 0) {
						queue.add(new Point(nr, nc, point.t + 1, point.hasGram));
						v[0][nr][nc] = true;
					} else if(!v[0][nr][nc] && map[nr][nc] == 2) {
						queue.add(new Point(nr, nc, point.t + 1, true));
						v[0][nr][nc] = true;
					}
				}
				else { // 그램을 가지고 있을 경우
					if(!v[1][nr][nc]) {
						queue.add(new Point(nr, nc, point.t + 1, point.hasGram));
						v[1][nr][nc] = true;
					}
				}
			}
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
