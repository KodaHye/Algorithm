import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ10844 {
    /*
    쉬운 계단 수
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static long dp[][];
    static int mod = 1_000_000_000;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        dp = new long[N + 1][10];

        for(int j = 1; j < 10; j++) dp[1][j] = 1;

        for(int i = 2; i < N + 1; i++) {
            for(int j = 0; j < 10; j++) {
                if(j == 0) dp[i][j] = dp[i - 1][j + 1];
                else if(j == 9) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
            }
        }

        long answer = 0;
        for(int j = 0; j < 10; j++) {
            answer += dp[N][j];
        }

        System.out.println(answer % mod);
    }
}
