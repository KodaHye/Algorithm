import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 경쟁적 전염
 * 입력
 * - N: 시험관의 정보, K: 바이러스의 번호
 * - 바이러스 위치 정보
 * - S, X, Y
 */
public class BOJ18405 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K, S, X, Y, map[][];

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		if(S == 0) {
			System.out.println(map[X - 1][Y - 1]);
			return;
		} else {
			bfs();
			System.out.println(map[X - 1][Y - 1]);
		}
	}

	private static void print(int[][] map) {
		for (int r = 0; r < map.length; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		boolean v[][] = new boolean[N][N];
		for(int i = 1; i <= K; i++) {
			for (int r = 0; r < map.length; r++) {
				for (int c = 0; c < map[r].length; c++) {
					if(map[r][c] == i) {
						queue.offer(new int[] {r, c, 0});
						v[r][c] = true;
					}
				}
			}
		}

		while (!queue.isEmpty()) {
			int[] point = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nr = point[0] + dr[i];
				int nc = point[1] + dc[i];

				if (!check(nr, nc))
					continue;
				if (map[nr][nc] == 0 && !v[nr][nc]) {
					map[nr][nc] = map[point[0]][point[1]];
					v[nr][nc] = true;
					if (point[2] + 1 < S) {
						queue.add(new int[] { nr, nc, point[2] + 1 });
					}
				}
			}
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
