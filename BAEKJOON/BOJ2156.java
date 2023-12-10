import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2156 {
    /*
    포도주 시식
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, arr[], dp[];

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());

        arr = new int[10_001];
        dp = new int[10_001]; // dp[i]: i번째 포도주를 마실 때, 마실 수 있는 최대량

        for(int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];

        for(int i = 3; i < n + 1; i++) {
            dp[i] = Math.max(arr[i] + arr[i - 1] + dp[i - 3], arr[i] + dp[i - 2]);
            dp[i] = Math.max(dp[i - 1], dp[i]); // 해당 포도주를 마시지 않을 때도 고려해야 됨
        }

        Arrays.sort(dp);

        System.out.println(dp[10_000]);
    }
}
