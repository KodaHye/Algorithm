import java.io.*;

/*
 * 이친수
 * 이차원 DP 활용
 */

public class BOJ2193 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// dp[i][0]: 0으로 끝나는 이친수 개수
		// dp[i][1]: 1로 끝나는 이친수 개수
		long[][] dp = new long[N + 1][2];
		
		dp[1][1] = 1;
		
		for(int i = 2; i < dp.length; i++) {
			dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
			dp[i][1] = dp[i - 1][0];
		}
		
		System.out.println(dp[N][0] + dp[N][1]);
	}
}
