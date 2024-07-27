import java.io.*;
import java.util.*;

/*
수들의 합 7
 */

public class BOJ2268 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M;
    static long tree[];

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static long sum(int a, int b) {
        long sum = 0;
        int startIdx = tree.length / 2;

        a += startIdx - 1;
        b += startIdx - 1;

        int min = Math.min(a, b);
        if(a != min) {
            b = a;
            a = min;
        }

        if(b - a == 1) return tree[a] + tree[b];
        while(a <= b) {
            if(a % 2 == 1) {
                sum += tree[a];
                a++;
            }

            if(b % 2 == 0) {
                sum += tree[b];
                b--;
            }

            a /= 2;
            b /= 2;
        }

        return sum;
    }

    static void modify(int i, int k) {
        int startIdx = tree.length / 2;
        i += startIdx - 1;

        tree[i] = k;

        i /= 2;
        while(i > 0) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
            i /= 2;
        }
    }
    static void solution() throws Exception {
        for(int testCase = 0; testCase < M; testCase++) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            if(k == 0) sb.append(sum(i, j) + "\n");
            if(k == 1) modify(i, j);
        }

        System.out.print(sb);
    }

    static void initInput() throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int k = getK(N) + 1;
        tree = new long[(int) Math.pow(2, k) + 1];
    }

    static int getK(int n) {
        int k = 0;
        while(n > 0) {
            n /= 2;
            k++;
        }

        return k;
    }
}
