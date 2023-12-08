import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1463 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, dp[];

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        dp = new int[1_000_001];

        dp[1] = 0;

        for(int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;

            if(i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            if(i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
        }

        System.out.println(dp[N]);
    }
}
