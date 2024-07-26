import java.io.*;
import java.util.*;

/*
수열과 쿼리 17
 */

public class BOJ14438 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M, tree[];

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void modifyTree(int a, int b) {
        int startIdx = tree.length / 2 + a - 1;

        tree[startIdx] = b;

        startIdx /= 2;
        while(startIdx > 0) {
            tree[startIdx] = Math.min(tree[startIdx * 2], tree[startIdx * 2 + 1]);
            startIdx /= 2;
        }
    }

    static int getMin(int a, int b) {
        int startIdx = tree.length / 2;
        int min = Integer.MAX_VALUE;
        a += startIdx - 1;
        b += startIdx - 1;

        while(a <= b) {
            min = Math.min(min, Math.min(tree[a], tree[b]));

            if(a % 2 == 1) a++;
            if(b % 2 == 0) b--;

            a /= 2;
            b /= 2;
        }

        return min;
    }
    static void solution() throws Exception {
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(k == 1) modifyTree(a, b);
            if(k == 2) sb.append(getMin(a, b) + "\n");
        }

        System.out.print(sb);
    }

    static void initInput() throws Exception {
        N = Integer.parseInt(br.readLine());

        int k = getK(N) + 1;

        tree = new int[(int) Math.pow(2, k) + 1];
        int startIdx = tree.length / 2;

        st = new StringTokenizer(br.readLine());
        for(int i = startIdx; i < startIdx + N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }

        setTree();

        M = Integer.parseInt(br.readLine());
    }

    static void setTree() {
        int startIdx = tree.length / 2 - 1;

        for(int i = startIdx; i >= 0; i--) {
            tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
        }
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
