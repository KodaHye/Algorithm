import java.io.*;

/*
1, 2, 3 더하기 6
 */
public class BOJ15991 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        final int MOD = 1_000_000_009;

        long[] dp = new long[100_001];
        dp[0] = 1; dp[1] = 1; dp[2] = 2; dp[3] = 2; dp[4] = 3;

        for(int i = 5; i < dp.length; i++) {

            for(int n = 1; n < 4; n++) {
                int nIdx = i - (2 * n);
                if(nIdx < 0) break;
                dp[i] += dp[nIdx];
            }
            dp[i] %= MOD;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append("\n");
        }

        System.out.print(sb);
    }
}
