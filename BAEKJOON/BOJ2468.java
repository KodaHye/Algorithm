import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 안전 영역
 */
public class BOJ2468 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;
	static int[][] mapCopy;
	static int N, count, max;
	static int result;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		mapCopy = new int[N][N];

		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(map[i][j], max);
			}
		}

		for (int i = 0; i < max; i++) {
			count = 0;
			copyArr(map, mapCopy);
			for (int r = 0; r < map.length; r++) {
				for (int c = 0; c < map[r].length; c++) {
					if (mapCopy[r][c] <= i)
						mapCopy[r][c] = 0;
				}
			}

			for (int r = 0; r < map.length; r++) {
				for (int c = 0; c < map[r].length; c++) {
					if (mapCopy[r][c] != 0) {
						bfs(r, c);
						count++;
					}

				}
			}
			result = Math.max(result, count);
		}
		System.out.println(result);
	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();

		queue.offer(new int[] { r, c });
		mapCopy[r][c] = 0;

		while (!queue.isEmpty()) {
			int[] point = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nr = point[0] + dr[i];
				int nc = point[1] + dc[i];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N && mapCopy[nr][nc] != 0) {
					mapCopy[nr][nc] = 0;
					queue.add(new int[] { nr, nc });
				}
			}
		}

	}

	public static void copyArr(int[][] ori, int[][] copy) {
		for (int i = 0; i < ori.length; i++) {
			copy[i] = Arrays.copyOf(ori[i], ori[i].length);
		}
	}
}
