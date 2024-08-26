import java.io.*;
import java.util.*;

/*
문자열 제거
 */

public class BOJ21941 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String s = br.readLine();

        HashMap<String, Integer> map = new HashMap<>();

        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            st =  new StringTokenizer(br.readLine());
            map.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        int dp[] = new int[s.length() + 1];

        for(int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1] + 1;

            for(String key: map.keySet()) {
                if(i - key.length() < 0) continue;
                if(key.equals(s.substring(i - key.length(), i)))
                    dp[i] = Math.max(dp[i], dp[i - key.length()] + map.get(key));
            }
        }

        System.out.println(dp[dp.length - 1]);
    }
}
