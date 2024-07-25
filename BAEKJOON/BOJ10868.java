import java.io.*;
import java.util.*;

/*
최솟값
 */

public class BOJ10868 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, tree[];

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void solution() throws Exception {
        setTree();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(getMin(a, b) + "\n");
        }

        System.out.print(sb);
    }

    static long getMin(int a, int b) {
        int startIdx = tree.length / 2;

        a += startIdx - 1;
        b += startIdx - 1;

        long min = Integer.MAX_VALUE;

        while(a <= b) {
            min = Math.min(Math.min(tree[a], min), tree[b]);

            if(a % 2 == 1) a++;
            if(b % 2 == 0) b--;

            a /= 2;
            b /= 2;
        }

        return min;
    }

    static void setTree() {
        int startIdx = tree.length / 2 - 1;
        for(int i = startIdx; i >= 0; i--) {
            tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
        }
    }

    static void initInput() throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int K = getK(N) + 1;

        tree = new int[(int) Math.pow(2, K) + 1];
        Arrays.fill(tree, Integer.MAX_VALUE);

        int startIdx = tree.length / 2;

        for(int i = startIdx; i < startIdx + N; i++) {
            tree[i] = Integer.parseInt(br.readLine());
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
