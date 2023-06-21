import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 마법사 상어와 토네이도
 */

public class BOJ20057 {
	static class Point {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, map[][];
	static long result;
	static int dr[] = {0, 1, 0, -1};
	static int dc[] = {-1, 0, 1, 0};
	static int scatterR[] = {0, -2, 2, 1, 1, 1, -1, -1, -1, 0};
	static int scatterC[] = {-2, 0, 0, -1, 0, 1, -1, 0, 1, -1};
	static double ratio[] = {5, 2, 2, 10, 7, 1, 10, 7, 1};
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			  
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		Point point = new Point(N / 2, N / 2);
		
		int l = 0;
		int d = 0;
		
		L: while(true) {
			
			if(d == 0 || d == 2) l++;
			
			int count = 0;
			
			while(count < l) {
				if(point.r == 0 && point.c == 0) break L;
				int nr = point.r + dr[d];
				int nc = point.c + dc[d];

				func(point.r, point.c, d);
				
				point.r = nr;
				point.c = nc;
				
				count++;
			}
			
			d =  (d + 1) % 4;
		}
		
		System.out.println(result);
	}

	private static void func(int r, int c, int d) {
		int sum = 0;
		
		int dirR[] = new int[10];
		int dirC[] = new int[10];
		
		if(d == 0) {
			dirR = scatterR;
			dirC = scatterC;
		}
		if(d == 1) {
			
			for(int i = 0; i < 10; i++) {
				dirR[i] = -scatterC[i];
			}
			
			dirC = scatterR;
		}
		if(d == 2) {
			
			dirR = scatterR;
			
			for(int i = 0; i < 10; i++) {
				dirC[i] = -scatterC[i];
			}
		}
		if(d == 3) {
			dirR = scatterC;
			dirC = scatterR;
		}
		
		r += dr[d];
		c += dc[d];
		
		for(int i = 0; i < 9; i++) {
			int nr = r + dirR[i];
			int nc = c + dirC[i];
			
			int tmp = (int) (map[r][c] * ratio[i] / 100);
			sum += tmp;
			
			if(!check(nr, nc)) { 
				result += tmp;
				continue;
			}
			
			map[nr][nc] += tmp;
		}
		
		int nr = r + dr[d];
		int nc = c + dc[d];
		
		if(!check(nr, nc)) {
			result += (map[r][c] - sum);
		} else {
			map[nr][nc] = map[nr][nc] + map[r][c] - sum;
		}
		
		map[r][c] = 0;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
