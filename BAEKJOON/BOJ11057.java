import java.util.Scanner;

/*
오르막 수
 */

public class BOJ11057 {
    static Scanner sc = new Scanner(System.in);
    static int N, dp[][];

    public static void main(String[] args) {
        N = sc.nextInt();

        dp = new int[N + 1][10]; // i 번째에 j로 끝났을 때 가능한 오르막 수 개수

        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        if(N == 1) {
            System.out.println(10);
            return;
        }

        for (int i = 0; i < 10; i++) {
            dp[2][i] = i + 1;
        }

        for (int i = 3; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                for(int k = 0; k < j + 1; k++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][k]) % 10007;
                }
            }
        }

        int result = 0;

        for (int i = 0; i < 10; i++) {
            result = (result + dp[N][i]) % 10007;
        }

        System.out.println(result);
    }
}
