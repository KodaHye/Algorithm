

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 불!
 * 기본적인 BFS 문제
 * 처음 poll 하고 방문처리 잊지말 것!
 */

public class BOJ4179 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, time;
	static char map[][];
	static Queue<Point> queue;
	static String answer;

	static class Point {
		int r;
		int c;
		int time;
		char ch;

		public Point(int r, int c, int time, char ch) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
			this.ch = ch;
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		queue = new LinkedList<>();

		for (int r = 0; r < map.length; r++) {
			String input = br.readLine();
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = input.charAt(c);
			}
		}

		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if (map[r][c] == 'F')
					queue.add(new Point(r, c, 1, 'F'));
			}
		}

		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if (map[r][c] == 'J')
					queue.add(new Point(r, c, 1, 'J'));
			}
		}

		func();

		answer = time == 0 ? "IMPOSSIBLE" : String.valueOf(time);
		System.out.println(answer);
	}

	static int dr[] = { 1, -1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	private static void func() {
		boolean v[][] = new boolean[R][C];

		while (!queue.isEmpty()) {
			Point point = queue.poll();
			v[point.r][point.c] = true;
			if (point.ch == 'J' && (point.r == 0 || point.r == R - 1 || point.c == 0 || point.c == C - 1)) {
				time = point.time;
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nr = point.r + dr[i];
				int nc = point.c + dc[i];

				if (!check(nr, nc)) continue;

				if (map[nr][nc] != '#' && !v[nr][nc]) {
					map[nr][nc] = point.ch;
					v[nr][nc] = true;
					queue.add(new Point(nr, nc, point.time + 1, point.ch));

				}
			}
		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}
