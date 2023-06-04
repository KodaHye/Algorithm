import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 도넛 행성
 */
public class BOJ27211 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, count, arr[][];
	static int dr[] = {-1, 1, 0, 0}; // 상, 하, 좌, 우
	static int dc[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for (int r = 0; r < arr.length; r++) {
			st = new StringTokenizer(br.readLine());
			
			for (int c = 0; c < arr[r].length; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
				
		System.out.println(count);
	}

	private static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		boolean v[][] = new boolean[N][M];
		
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[r].length; c++) {
				if(arr[r][c] == 0 && !v[r][c]) {
					count++;
					
					queue.add(new Point(r, c));
					arr[r][c] = 1;
					v[r][c] = true;
					
					while(!queue.isEmpty()) {
						
						Point current = queue.poll();
						
						for(int d = 0; d < 4; d++) {
							int nr = current.r + dr[d];
							int nc = current.c + dc[d];
							
							if(nr == -1) nr = N - 1;
							if(nc == -1) nc = M - 1;							
							if(nr == N) nr = 0;							
							if(nc == M) nc = 0;
							
							if(!check(nr, nc)) continue;
							if(!v[nr][nc] && arr[nr][nc] == 0) {
								arr[nr][nc] = 1;
								
								queue.add(new Point(nr, nc));
								v[nr][nc] = true;
							}
						}
					}
				}
			}
		}
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
