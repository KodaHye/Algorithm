import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
계단 오르기
 */
public class BOJ2579 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];
        int dp[] = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = arr[0];

        for(int i = 1; i < N; i++) {
            if(i == 1) {
                dp[i] = arr[0] + arr[1];
            } else if(i == 2) {
                dp[i] = Math.max(arr[0], arr[1]) + arr[i];
            } else dp[i] = Math.max(dp[i - 3] + arr[i - 1], dp[i - 2]) + arr[i];
        }

        System.out.println(dp[N - 1]);
    }
}
