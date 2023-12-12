import java.util.Scanner;

public class BOJ1699 {
    /*
    제곱수의 합
     */
    static Scanner sc = new Scanner(System.in);
    static int N, dp[];
    public static void main(String[] args) {
        N = sc.nextInt();

        dp = new int[100_001];

        for(int i = 1; i < N + 1; i++) {
            dp[i] = i;

            for(int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        System.out.println(dp[N]);
    }
}
