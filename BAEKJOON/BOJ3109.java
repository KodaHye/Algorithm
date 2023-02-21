

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 빵집
 */
public class BOJ3109 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char[][] map;
	static int R, C;
	// 상우, 우, 우하
	static int[] dr = {-1, 0, 1};
	static int[] dc = {1, 1, 1};
	static int count;
	static boolean flag;
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for (int i = 0; i < map.length; i++) {
			String input = br.readLine();
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		for(int r = 0; r < R; r++) {
			flag = false;
			// i번째 행 0번째 열에서 시작
			func(r, 0);
		}
		System.out.println(count);
	}
	private static void func(int r, int c) {
		if(c == C - 1) {
			count++;
			flag = true;
			return;
		}
		
		for(int i = 0; i < 3; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(check(nr, nc) && map[nr][nc] != 'x' && !flag) {
				map[nr][nc] = 'x';
				func(nr, nc);
			}
		}
	}
	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}
