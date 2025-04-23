import java.io.*;

/*
 * 쉬운 계단 수
 */

public class BOJ10844_2 {
	
	static final int MOD = 1_000_000_000;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// dp[i][j]: 길이가 i면서 j로 끝나는 계단수의 수
		long[][] dp = new long[N + 1][10];
		
		long result = 0;
		
		for(int i = 1; i < dp.length; i++) {
			
			for(int j = 0; j < 10; j++) {
				if(i == 1) {
					if(j == 0) continue;
					dp[i][j] = 1;
				} else {
					if(j > 0) dp[i][j] += dp[i - 1][j - 1];
					if(j < 9) dp[i][j] += dp[i - 1][j + 1];
				}

				dp[i][j] %= MOD;
				
				if(i == N) result += dp[i][j];
			}
		}
		
		System.out.println(result % MOD);
	}
}
