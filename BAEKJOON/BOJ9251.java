import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ9251 {
    /*
    LCS
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String s1, s2;
    static int dp[][], result;

    public static void main(String[] args) throws Exception {
        s1 = br.readLine();
        s2 = br.readLine();

        dp = new int[s1.length() + 1][s2.length() + 1];

        for(int i = 0; i < s1.length(); i++) {
            for(int j = 0; j < s2.length(); j++) {
                if(s1.charAt(i) == s2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    result = Math.max(result, dp[i + 1][j + 1]);
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        System.out.println(result);
    }
}
