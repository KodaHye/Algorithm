import java.io.*;
import java.util.*;

/*
파닭파닭
 */

public class BOJ14627 {
    static int S, C, start, end, pa[];
    static long sum;

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }
    public static void solution() {
        long paLength = binarySearch();
        System.out.print(sum - (paLength * C));
    }
    public static int binarySearch() {
        int result = 0;

        while(start <= end) {
            long m = (start + end) / 2;

            if(!available(m)) end = (int) (m - 1);
            else {
                result = (int) m;
                start = (int) (m + 1);
            }
        }

        return result;
    }

    public static boolean available(long m) {
        int cnt = 0;

        for(int p: pa) {
            while(true) {
                if(cnt == C || p < m) break;
                p -= m;
                cnt++;
            }
        }

        if(cnt >= C) return true;
        return false;
    }
    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        start = 1;
        end = Integer.MIN_VALUE;
        pa = new int[S];

        for(int i = 0; i < S; i++) {
            pa[i] = Integer.parseInt(br.readLine());
            end = Math.max(end, pa[i]);
            sum += pa[i];
        }
    }
}
