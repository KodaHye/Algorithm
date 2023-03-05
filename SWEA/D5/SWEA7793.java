package D5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 오! 나의 여신님
 * 계속 오류가 나서 수정해봤는데, 마지막은 시간초과가 나옵니다..
 */
public class SWEA7793 {
	static class Point {
		int r;
		int c;
		int level;
		char type;

		public Point(int r, int c, int level, char type) {
			super();
			this.r = r;
			this.c = c;
			this.level = level;
			this.type = type;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T, N, M, time;
	static char map[][];
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][] v;
	static ArrayList<Point> list;
	static Point suPoint;
	static int Level;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case < T + 1; test_case++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new char[N][M];
			v = new boolean[N][M];
			time = 0;

			for (int r = 0; r < map.length; r++) {
				String input = br.readLine();
				for (int c = 0; c < map[r].length; c++) {
					map[r][c] = input.charAt(c);
				}
			}

			func();

			if(Level == 0) {
				sb.append("#" + test_case + " " + "GAME OVER" + "\n");
			} else {
				sb.append("#" + test_case + " " + Level + "\n");
			}
		}
		System.out.println(sb);
	}

	private static void func() {
		Queue<Point> queue = new LinkedList<>();

		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if (map[r][c] == '*') {
					queue.offer(new Point(r, c, 0, '*'));
				}
				if (map[r][c] == 'S') {
					queue.offer(new Point(r, c, 0, 'S'));
				}
			}
		}

		while (!queue.isEmpty()) {

			Point point = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = point.r + dr[d];
				int nc = point.c + dc[d];

				if (!check(nr, nc))
					continue;
				if (point.type == '*' && map[nr][nc] != 'X' && map[nr][nc] != 'D' && !v[nr][nc]) {
					Level = Math.max(point.level + 1, Level);
					v[nr][nc] = true;
					map[nr][nc] = '*';
					queue.add(new Point(nr, nc, point.level + 1, '*'));
				}
				if (point.type == 'S' && map[nr][nc] != 'X' && map[nr][nc] != '*') {
					Level = Math.max(point.level + 1, Level);
					if(map[nr][nc] == 'D') return;
					map[nr][nc] = 'S';
					queue.add(new Point(nr, nc, point.level + 1, 'S'));
				}
			}

		}
		if(!findSu()) {
			Level = 0;
			return;
		}
	}

	private static void print(char[][] map2) {
		for (int i = 0; i < map2.length; i++) {
			System.out.println(Arrays.toString(map2[i]));
		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}

	private static boolean findSu() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 'S') {
					return true;
				}
			}
		}
		return false;
	}
}