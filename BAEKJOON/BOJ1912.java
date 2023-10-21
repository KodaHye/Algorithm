import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
연속합
 */
public class BOJ1912 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, arr[], dp[], max;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        arr = new int[N];
        dp = new int[N];
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        dp[0] = arr[0];
        max = dp[0];

        for(int i = 1; i < N; i++) {
            if(arr[i] + dp[i - 1] < arr[i]) {
                dp[i] = arr[i];
            } else {
                dp[i] = arr[i] + dp[i - 1];
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
