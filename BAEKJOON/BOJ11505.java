import java.io.*;
import java.util.*;

/*
구간 곱 구하기
 */

public class BOJ11505 {
    static long mod = 1_000_000_007;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M, K;
    static long tree[];

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void solution() throws Exception {
        for(int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 1) modifyTree(b, c);
            else if(a == 2) getMultifly(b, c);
            else return;
        }

        System.out.print(sb);
    }

    static void getMultifly(int s, int e) {
        s += tree.length / 2 - 1;
        e += tree.length / 2 - 1;

        long result = 1;
        while(s <= e) {
            if(s % 2 == 1) {
                result = ((result % mod) * (tree[s] % mod)) % mod;
                s++;
            }

            if(e % 2 == 0) {
                result = ((result % mod) * (tree[e] % mod)) % mod;
                e--;
            }

            s /= 2;
            e /= 2;
        }

        sb.append(result + "\n");
    }

    static void modifyTree(int b, int c) {
        int startIdx = tree.length / 2 + (b - 1);
        tree[startIdx] = c;

        int p = startIdx / 2;
        while(p > 0) {
            tree[p] = multifly(p);
            p /= 2;
        }
    }
    static void initInput() throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int k = getK(N) + 1;
        tree = new long[(int) Math.pow(2, k + 1)];

        int startIdx = tree.length / 2;

        for(int i = startIdx; i < startIdx + N; i++) {
            tree[i] = Integer.parseInt(br.readLine());
        }

        setTree();
    }

    static void setTree() {
        int startIdx = tree.length / 2;

        for(int i = startIdx - 1; i >= 0; i--) {
            tree[i] = multifly(i);
        }
    }

    private static long multifly(int i) {
        long a = tree[i * 2] % mod;
        long b = tree[i * 2 + 1] % mod;
        return (a * b) % mod;
    }

    static int getK(int N) {
        int k = 0;

        while(N > 0) {
            N /= 2;
            k++;
        }

        return k;
    }
}
