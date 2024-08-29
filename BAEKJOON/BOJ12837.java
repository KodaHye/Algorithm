import java.io.*;
import java.util.*;

/*
가계부 (Hard)
 */

public class BOJ12837 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, Q, startIdx;
    static long tree[];

    static void solution() throws Exception {
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            if(n == 1) setTree(p, q);
            if(n == 2) sb.append(printTree(p, q)  + "\n");
        }

        System.out.print(sb);
    }

    static long printTree(int s, int e) {
        long sum = 0;
        s += startIdx - 1;
        e += startIdx - 1;

        while(s <= e) {
            if(s % 2 == 1) {
                sum += tree[s];
                s++;
            }

            if(e % 2 == 0) {
                sum += tree[e];
                e--;
            }

            s /= 2;
            e /= 2;
        }

        return sum;
    }
    static void setTree(int idx, int num) {
        idx += startIdx - 1;
        tree[idx] += num;

        idx /= 2;
        while(idx > 0) {
            tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
            idx /= 2;
        }
    }
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        int k = (int) (Math.ceil(Math.log(N) / Math.log(2))) + 1;
        tree = new long[(int) Math.pow(2, k)];

        startIdx = tree.length / 2;
        solution();
    }
}
