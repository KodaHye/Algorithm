import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 치즈
 * 판의 가장자리에는 치즈가 놓여 있지 않으며 치즈에는 하나 이상의 구멍이 있을 수 있음
 * 치즈가 모두 녹아 없어지는데 걸리는 시간, 모두 녹기 한 시간 전에 남아 있는 치즈조각이 놓여있는 칸의 수
 */
public class BOJ2636 {
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
	static int N, M, time, left, map[][];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			time++;
			left = countCheese();
			bfs(0, 0);
			deleteCheese();
			if(checkCheese()) break;
		}
		
		System.out.println(time + "\n" + left);
	}

	private static void deleteCheese() {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(map[r][c] == -1) map[r][c] = 0;
			}
		}
	}

	private static void print(int[][] map) {
		for (int r = 0; r < map.length; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	}

	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	private static void bfs(int r, int c) {
		Queue<Point> queue = new LinkedList<>();
		boolean v[][] = new boolean[N][M];
		v[r][c] = true;
		
		queue.offer(new Point(r, c));
		while(!queue.isEmpty()) {
			
			Point p = queue.poll();
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(!check(nr, nc)) continue;
				
				if(map[p.r][p.c] == 0 && !v[nr][nc]) {
					if(map[nr][nc] == 1) map[nr][nc] = -1;
					v[nr][nc] = true;
					queue.add(new Point(nr, nc));
				}
			}
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}

	private static int countCheese() {
		int cheese = 0;
		
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(map[r][c] == 1) cheese++;
			}
		}
		return cheese;
	}

	private static boolean checkCheese() {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(map[r][c] == 1) return false;
			}
		}
		return true;
	}

}
