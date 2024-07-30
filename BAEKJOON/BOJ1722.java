import java.io.*;
import java.util.*;

/*
순열의 순서
 */

public class BOJ1722 {
    static int N;
    static boolean check[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        check = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        if(a == 1) {
            getNum(Long.parseLong(st.nextToken()));
            for(int i = 1; i < check.length; i++)
                if(!check[i]) sb.append(i);
        }

        if(a == 2) getOrder(st);
        System.out.println(sb);
    }

    private static void getOrder(StringTokenizer st) {
        long result = 0;

        for(int i = N - 1; i > 0; i--) {
            long cnt = getFactorial(i);

            int n = Integer.parseInt(st.nextToken());
            check[n] = true;

            int minCnt = getMinCnt(n);
            result += minCnt * cnt;
        }

        sb.append((result + 1));
    }

    static int getMinCnt(int n) {
        int cnt = 0;
        for(int i = 1; i < n; i++) {
            if(!check[i]) cnt++;
        }
        return cnt;
    }

    private static void getNum(long k) {
        for(int i = N - 1; i > 0; i--) {
            long cnt = getFactorial(i);

            if(k / cnt == 0) {
                int num = getMin(1);
                sb.append(num + " ");
            }
            else {
                int num = getMin(k % cnt == 0 ? k / cnt : k / cnt + 1);
                sb.append(num + " ");
                long tmp = (k / cnt);
                if(k % cnt == 0) tmp--;
                k -= ((int) tmp * cnt);
            }
        }
    }

    private static int getMin(long order) {
        int cnt = 0;
        for(int i = 1; i < check.length; i++) {
            if(!check[i]) {
                cnt++;

                if(cnt == order) {
                    check[i] = true;
                    return i;
                }
            }
        }

        return -1;
    }

    private static long getFactorial(int k) {
        long result = 1;
        while(k > 0) result *= k--;
        return result;
    }
}
