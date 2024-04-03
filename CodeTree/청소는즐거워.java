import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
배열을 참조했을 때, 참조한 것의 값을 바꾸면 원본도 바뀐다는거 명심!
 */

public class 청소는즐거워 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N, middle, vecR[], vecC[], map[][], result;
	static Point p;
	static int dr[] = {0, 1, 0, -1};
	static int dc[] = {-1, 0, 1, 0};
	static int cleanr[] = {-1, 1, -1, 1, 0, -2, 2, -1, 1, 0};
	static int cleanc[] = {-1, -1, 0, 0, -2, 0, 0, 1, 1, -1};
	static double cleanPer[] = {0.1, 0.1, 0.07, 0.07, 0.05, 0.02, 0.02, 0.01, 0.01};
	
	public static void main(String[] args) throws Exception {
		initInput();
		func();
		System.out.println(result);
	}

	private static void func() {
		int cnt = 0;
		int rotateLen = 1;
		int nr = 0;
		int nc = 0;
		int dir = 0;
		
		L: while(true) {
			
			int len = rotateLen;
			while(len-- > 0) {
				nr = p.r + dr[dir];
				nc = p.c + dc[dir];

				int dust = map[nr][nc];
				map[nr][nc] = 0;
				
				clean(nr, nc, dust, dir);
				
				p.r = nr;
				p.c = nc;

				if(checkEnd()) break L;
			}
			
			cnt++;
			if(cnt % 2 == 0) {
				cnt = 0;
				rotateLen++;
			}
			
			dir = (dir + 1) % 4; 
		}
		
		p.r = nr;
		p.c = nc;
	}

	private static int clean(int r, int c, int dust, int dir) {
		int sum = 0;
		
		dirVector(dir);
		
		for(int i = 0; i < 10; i++) {
			int nr = r + vecR[i];
			int nc = c + vecC[i];
			
			int plusDust = 0;
			if(i == 9) plusDust = dust - sum;
			else plusDust = (int) (cleanPer[i] * dust);
			
			if(!check(nr, nc)) {
				result += plusDust;
				sum += plusDust;
				continue;
			}
			
			map[nr][nc] += plusDust;
			sum += plusDust;
		}
		
		return sum;
	}

	private static void dirVector(int dir) {
		if(dir == 0){
			vecR = Arrays.copyOf(cleanr, cleanr.length);
			vecC = Arrays.copyOf(cleanc, cleanc.length);
		} else if(dir == 1) {
			vecR = Arrays.copyOf(cleanc, cleanc.length);
			vecC = Arrays.copyOf(cleanr, cleanr.length);
			
			for(int i = 0; i < vecR.length; i++) {
				vecR[i] *= -1;
			}
		} else if(dir == 2) {
			vecR = Arrays.copyOf(cleanr, cleanr.length);
			vecC = Arrays.copyOf(cleanc, cleanc.length);
			
			for(int i = 0; i < vecC.length; i++) {
				vecC[i] *= -1;
			}
		} else if(dir == 3) {
			vecR = Arrays.copyOf(cleanc, cleanc.length);
			vecC = Arrays.copyOf(cleanr, cleanr.length);
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >=0 && nc < N;
	}

	private static boolean checkEnd() {
		return p.r == 0 && p.c == 0;
	}

	private static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		middle = N >> 1;
		
		vecR = new int[9];
		vecC = new int[9];
		
		p = new Point(middle, middle);
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
