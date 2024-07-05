import java.io.*;
import java.util.*;

public class BOJ10986 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long sum[] = new long[N + 1];
        long check[] = new long[M];

        st = new StringTokenizer(br.readLine());
        long result = 0;
        for (int i = 1; i < sum.length; i++) {
            sum[i] = (sum[i - 1] + Integer.parseInt(st.nextToken())) % M;
            check[(int) sum[i]]++;
        }

        for(int i = 0; i < check.length; i++) {
            if(check[i] != 0) result += check[i] * (check[i] - 1) / 2;
        }
        result += check[0];

        System.out.println(result);
    }
}
