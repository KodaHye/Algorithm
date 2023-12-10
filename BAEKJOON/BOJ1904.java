import java.util.Scanner;

public class BOJ1904 {
    static Scanner sc = new Scanner(System.in);
    static int N, dp[];

    public static void main(String[] args) {
        N = sc.nextInt();

        dp = new int[1_000_001];

        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i < N + 1; i++) {
          dp[i] = (dp[i - 2] + dp[i - 1]) % 15746;
        }

        System.out.println(dp[N] % 15746);
    }
}
