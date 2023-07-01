import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 아기 상어
 */
public class BOJ17086 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, map[][], result;
	static int dr[] = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int dc[] = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<int[]> queue = new LinkedList<>();
		boolean v[][] = new boolean[N][M];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] == 1) {
					queue.add(new int[] {r, c, 0});
					v[r][c] = true;
				}
			}
		}
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			result = Math.max(result, current[2]);
			
			for(int d = 0; d < 8; d++) {
				int nr = current[0] + dr[d];
				int nc = current[1] + dc[d];
				
				if(!check(nr, nc)) continue;
				
				if(map[nr][nc] == 0 && !v[nr][nc]) {
					v[nr][nc] = true;
					queue.add(new int[] {nr, nc, current[2] + 1});
				}
			}
		}
		
		System.out.println(result);
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
