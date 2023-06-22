import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 로봇 청소기
 */
public class BOJ14503 {
	static class Point {
		int r, c, d;
		public Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, map[][], result;
	static int dr[] = {-1, 0, 1, 0};
	static int dc[] = {0, 1, 0, -1};
	static Point machine;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		
		machine = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		func();
		
		System.out.println(result);
	}

	private static void func() {
		while(true) {
			if(map[machine.r][machine.c] == 0) {
				result++;
				map[machine.r][machine.c] = 2; // 청소한 곳은 2로 체크하기
			}
			
			int noClean = 0;
			
			for(int d = 0; d < 4; d++) {
				int nr = machine.r + dr[d];
				int nc = machine.c + dc[d];
				
				if(!check(nr, nc)) continue;
				if(map[nr][nc] == 0) noClean++;
			}
			
			if(noClean == 0) {
				int d = (machine.d + 2) % 4;
				
				int nr = machine.r + dr[d];
				int nc = machine.c + dc[d];
				
				// 바라보는 방향의 뒤 쪽이 벽이라 후진할 수 없으면 작동을 멈춤
				if(!check(nr, nc) || map[nr][nc] == 1) break;
				
				// 바라보는 방향을 유지한 채로 한칸 후진할 수 있으면 후진하고 1번으로 돌아감
				machine.r = nr;
				machine.c = nc;
				
			} else {
				while(true) {
					machine.d = (machine.d - 1) % 4 < 0 ? (machine.d - 1) % 4 + 4 : (machine.d - 1) % 4;
					
					int nr = machine.r + dr[machine.d];
					int nc = machine.c + dc[machine.d];
					
					if(!check(nr, nc) || map[nr][nc] == 1) continue;
					if(map[nr][nc] == 0) {
						machine.r = nr;
						machine.c = nc;
						break;
					}
				}
			}
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
