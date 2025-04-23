import java.io.*;
import java.util.*;

/*
 * 가장 큰 정사각형
 */

public class BOJ1915_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[n + 1][m + 1];
		
		int result = 0;
		
		for(int r = 1; r < n + 1; r++) {
			String s = br.readLine();
			
			for(int c = 1; c < m + 1; c++) {
				int current = s.charAt(c - 1) - '0';
				
				if(current == 0) continue;
				dp[r][c] = Math.min(dp[r - 1][c - 1], Math.min(dp[r][c - 1], dp[r - 1][c])) + 1;
				
				result = Math.max(result, dp[r][c]);
			}
		}
		
		System.out.println(result * result);
	}
}
