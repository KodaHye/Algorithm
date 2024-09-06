import java.io.*;
import java.util.*;

/*
수들의 합 2
 */

public class BOJ2003 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long sum[] = new long[N + 1];

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i < N + 1; i++) {
            sum[i] = Integer.parseInt(st.nextToken());
            sum[i] += sum[i - 1];
        }

        int l = 0;
        int r = 0;
        int result = 0;
        while(l <= r && r < sum.length) {
            long diff = sum[r] - sum[l];

            if(diff < M) r++;
            else {
                if(diff == M) result++;
                l++;
            }
        }
        System.out.println(result);
    }
}
