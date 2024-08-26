import java.io.*;
import java.util.*;

/*
공주님을 구해라!
 */

public class BOJ17836 {
	static class Point {
		int r, c, t;
		boolean gram;
		public Point(int r, int c, int t, boolean gram) {
			this.r = r;
			this.c = c;
			this.t = t;
			this.gram = gram;
		}
	}
	static int N, M, T, result, map[][];
	static int dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, -1, 1};
	static boolean v[][][];

	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0, 0, false));
		v[0][0][0] = true;

		while(!q.isEmpty()) {
			Point current = q.poll();

			if(current.r == N - 1 && current.c == M - 1) {
				result = Math.min(current.t, result);
				return;
			}

			for(int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];

				if(!check(nr, nc)) continue;

				if(current.gram) {
					if(v[1][nr][nc]) continue;
					q.add(new Point(nr, nc, current.t + 1, true));
					v[1][nr][nc] = true;
				} else {
					if(v[0][nr][nc]) continue;
					if(map[nr][nc] == 0) {
						q.add(new Point(nr, nc, current.t + 1, false));
						v[0][nr][nc] = true;
					}
					if(map[nr][nc] == 2) {
						q.add(new Point(nr, nc, current.t + 1, true));
						v[1][nr][nc] = true;
					}
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input[] = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		T = Integer.parseInt(input[2]);

		result = Integer.MAX_VALUE;
		map = new int[N][M];
		v = new boolean[2][N][M];

		for(int r = 0; r < N; r++) {
			String s = br.readLine();
			for(int c = 0; c < M; c++) {
				map[r][c] = s.charAt(c * 2) - '0';
			}
		}

		bfs();
		System.out.println(result == Integer.MAX_VALUE || result > T ? "Fail" : result);
	}
}