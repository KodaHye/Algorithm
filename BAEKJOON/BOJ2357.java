import java.io.*;
import java.util.*;

/*
최솟값과 최댓값
 */

public class BOJ2357 {
    static class Node {
        int min, max;
        public Node(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M;
    static Node tree[];

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
            getMinAndMax(a, b);
        }

        System.out.print(sb);
    }
    static void getMinAndMax(int s, int e) {
        int startIdx = tree.length / 2;
        s += startIdx - 1;
        e += startIdx - 1;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        while(s <= e) {
            min = Math.min(min, Math.min(tree[s].min, tree[e].min));
            max = Math.max(max, Math.max(tree[s].max, tree[e].max));

            if(s % 2 == 1) s++;
            if(e % 2 == 0) e--;

            s /= 2;
            e /= 2;
        }

        sb.append(min + " " + max + "\n");
    }
    static void setTree() {
        int startIdx = tree.length / 2 - 1;

        for(int i = startIdx; i >= 0; i--) {
            tree[i].min = Math.min(tree[i * 2].min, tree[i * 2 + 1].min);
            tree[i].max = Math.max(tree[i * 2].max, tree[i * 2 + 1].max);
        }
    }
    static void initInput() throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int k = getK(N) + 1;
        tree = new Node[(int) Math.pow(2, k) + 1];
        for(int i = 0; i < tree.length; i++) tree[i] = new Node(Integer.MAX_VALUE, Integer.MIN_VALUE);

        int startIdx = tree.length / 2;
        for(int i = startIdx; i < startIdx + N; i++) {
            int n = Integer.parseInt(br.readLine());
            tree[i].min = n;
            tree[i].max = n;
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
