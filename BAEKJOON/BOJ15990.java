import java.io.*;

/*
1, 2, 3 더하기 5
 */

public class BOJ15990 {
    static int MOD = 1_000_000_009;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        long[][] dp = new long[100_001][4];

        dp[1][1] = 1; dp[2][2] = 1;
        dp[3][1] = 1; dp[3][2] = 1; dp[3][3] = 1;

        for(int i = 4; i < dp.length; i++) {
            dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % MOD;
            dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % MOD;
            dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % MOD;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < t; i++) {
            int num = Integer.parseInt(br.readLine());

            sb.append((dp[num][1] + dp[num][2] + dp[num][3]) % MOD).append("\n");
        }

        System.out.print(sb);
    }
}
