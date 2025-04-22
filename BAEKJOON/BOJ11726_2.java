import java.io.*;

/*
 * 2×n 타일링
 */

public class BOJ11726_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[n + 1];
		
		dp[0] = 1;
		dp[1] = 1;
		
		for(int i = 2; i < dp.length; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % 10_007;
		}
		
		System.out.println(dp[n]);
	}
}
