import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 보물섬
 * 욱지: L, 바다: W
 * 상하좌우로 이웃한 육지로만 이동 가능
 * 보물: 최단거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 묻혀있음
 */
public class BOJ2589 {
	static class Point {
		int r;
		int c;
		int l;
		public Point(int r, int c, int l) {
			super();
			this.r = r;
			this.c = c;
			this.l = l;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int W, H, minMax, dist;
	static char map[][];
	static boolean v[][];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new char[H][W];
		minMax = Integer.MIN_VALUE;
		
		for (int r = 0; r < map.length; r++) {
			String input = br.readLine();
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = input.charAt(c);
			}
		}
		
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(map[r][c] == 'L') {
					v = new boolean[H][W];
					bfs(new Point(r, c, 0));
					
					minMax  = Math.max(minMax, dist);
				}
			}
		}
		
		System.out.println(minMax);
	}
	
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	private static void bfs(Point p) {
		Queue<Point> queue = new LinkedList<>();
		
		queue.offer(new Point(p.r, p.c, p.l));
		v[p.r][p.c] = true;
		
		while(!queue.isEmpty()) {
			Point point = queue.poll();
			
			dist = point.l;
			
			for (int d = 0; d < 4; d++) {
				int nr = point.r + dr[d];
				int nc = point.c + dc[d];
				
				if(!check(nr, nc)) continue;
				if(!v[nr][nc] && map[nr][nc] == 'L') {
					queue.add(new Point(nr, nc, point.l + 1));
					v[nr][nc] = true;
				}
			}
		}
	}
	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < H && nc >= 0 && nc < W;
	}
	private static void print(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
}
