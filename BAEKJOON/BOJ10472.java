import java.io.*;
import java.util.Arrays;

/*
 * 십자뒤집기
 */

public class BOJ10472 {
	
	static int[] dr = {-1, 1, 0, 0, 0}, dc = {0, 0, -1, 1, 0};
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[][] map = new char[3][3];
		
		StringBuilder sb = new StringBuilder();
		
		while(N -- > 0) {
			
			for(int r = 0; r < 3; r++) {
				map[r] = br.readLine().toCharArray();
			}
			
			result = Integer.MAX_VALUE;
			powerset(0, 0, map);
			
			sb.append(result).append("\n");
		}
		
		System.out.println(sb);
	}

	static void powerset(int idx, int cnt, char[][] map) {

		if(checkMap(map)) result = Math.min(result, cnt);
		
		if(idx == 9) return;

		char[][] copy = new char[3][3];
		for(int i = 0; i < 3; i++) copy[i] = Arrays.copyOf(map[i], 3);
		
		powerset(idx + 1, cnt, copy);

		click(idx, copy);
		powerset(idx + 1, cnt + 1, copy);
	}
	
	private static boolean checkMap(char[][] map) {
		
		for(char[] r: map) {
			for(char c: r) {
				if(c == '*') return false;
			}
		}
		
		return true;
	}

	static void click(int idx, char[][] map) {
		int r = idx / 3;
		int c = idx % 3;
		
		for(int d = 0; d < 5; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr < 0 || nr >= 3 || nc < 0 || nc >= 3) continue;
			
			if(map[nr][nc] == '.') map[nr][nc] = '*';
			else map[nr][nc] = '.';
		}
	}
}
