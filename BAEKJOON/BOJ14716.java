import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 현수막
 */
public class BOJ14716 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int M, N, map[][], result;
	static int dr[] = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int dc[] = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		
		for (int r = 0; r < M; r++) {
			st = new StringTokenizer(br.readLine());
			
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int r = 0; r < M; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c] == 1) bfs(r, c);
			}
		}
		
		System.out.println(result);
	}
	
	private static void bfs(int r, int c) {
		result++;
		
		map[r][c] = 0;
		
		Queue<int[]> queue = new LinkedList<>();
		
		queue.add(new int[] {r, c});
		
		while(!queue.isEmpty()) {
			int current[] = queue.poll();
			
			for(int d = 0; d < 8; d++) {
				int nr = current[0] + dr[d];
				int nc = current[1] + dc[d];
				
				if(!check(nr, nc)) continue;
				if(map[nr][nc] == 1) {
					map[nr][nc] = 0;
					queue.add(new int[] {nr, nc});
				}
			}
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < M && nc >= 0 && nc < N;
	}
}
