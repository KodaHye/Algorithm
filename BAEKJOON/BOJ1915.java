import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1915 {
    /*
    가장 큰 정사각형
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, arr[][], dp[][], result;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n + 1][m + 1];
        dp = new int[n + 1][m + 1];

        for(int i = 1; i < arr.length; i++) {
            String input = br.readLine();

            for(int j = 1; j < arr[i].length; j++) {
                arr[i][j] = input.charAt(j - 1) - '0';
            }
        }

        for(int i = 1; i < arr.length; i++) {
            for(int j = 1; j< arr[i].length; j++) {
                if(arr[i][j] == 0){
                    dp[i][j] = 0;
                }

                if(arr[i][j] == 1) {
                    int min = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                    dp[i][j] = min + 1;

                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        System.out.println(result * result);
    }
}
