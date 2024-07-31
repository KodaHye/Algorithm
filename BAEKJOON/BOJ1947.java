import java.io.*;

/*
선물 전달
 */

public class BOJ1947 {
    static int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long dp[] = new long[N + 1];

        for(int i = 2; i < N + 1; i++) {
            if(i == 2) dp[i] = 1;
            else dp[i] = ((i - 1) * (dp[i - 1]+ dp[i - 2])) % INF;
        }

        System.out.println(dp[N]);
    }
}