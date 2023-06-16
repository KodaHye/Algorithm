import java.io.*;


public class SOF413 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, dp[];

    public static void main(String args[]) throws Exception {
        N = Integer.parseInt(br.readLine());

        dp = new int[17];

        dp[1] = 4;

        for(int i = 2; i < 17; i++) {
            dp[i] = dp[i - 1] + (int) Math.pow(4, i - 2) + (int) Math.pow(2, i - 1) + (int) Math.pow(2, 2 * i - 3);
        }

        System.out.println(dp[N + 1]);
    }
}