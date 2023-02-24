import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 치즈
 * 4변 중에서 적어도 2변 이상이 실내온도의 공기와 접촉하면 한 시간 만에 없어짐
 */
public class BOJ2638 {
	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, result, count, map[][];
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static Queue<int[]> queue;
	static boolean v[][];
	static int space[][];
	static Point[] points;;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = 0;
		map = new int[N][M];
		v = new boolean[N][M];
		space = new int[N][M];

		queue = new LinkedList<>();

		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			result++;
			
			v = new boolean[N][M];
			bfs(0, 0);

			originCheese();
			
			if (checkMap()) break;
		}
		System.out.println(result);
	}

	private static void originCheese() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(map[i][j] != 0) map[i][j] = 1;
			}
		}
		
	}

	private static boolean checkMap() {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if (map[r][c] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	private static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		v[r][c] = true;

		queue.offer(new int[] { r, c });
		

		while (!queue.isEmpty()) {
			int[] point = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nr = point[0] + dr[i];
				int nc = point[1] + dc[i];

				if (!check(nr, nc))
					continue;
				if (map[nr][nc] != 0) {
					map[nr][nc] += 1;
				}
				if (map[nr][nc] == 3) {
					map[nr][nc] = 0;
					v[nr][nc] = true;
				}

				if (map[nr][nc] == 0 && !v[nr][nc]) {
					v[nr][nc] = true;
					queue.add(new int[] { nr, nc });
					
				}
			}
		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
