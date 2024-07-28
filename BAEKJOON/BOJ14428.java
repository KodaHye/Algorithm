import java.io.*;
import java.util.*;

/*
수열과 쿼리 16
 */

public class BOJ14428 {
    static long INF = Long.MAX_VALUE;
    static class Node {
        int idx;
        long min;

        public Node(int idx, long min) {
            this.idx = idx;
            this.min = min;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static Node tree[];
    static int N, M, startIdx;

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void solution() throws Exception {

        for(int testCase = 0; testCase < M; testCase++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 1) modifyTree(b, c);
            if(a == 2) sb.append(getMinIdx(b, c) + "\n");

        }

        System.out.print(sb);
    }

    private static int getMinIdx(int s, int e) {
        int minIdx = Integer.MAX_VALUE;
        long minValue = INF;

        s += startIdx - 1;
        e += startIdx - 1;

        while(s <= e) {
            int checkIdx;
            if(tree[s].min <= tree[e].min) checkIdx = tree[s].idx;
            else checkIdx = tree[e].idx;

            if(minValue >= tree[checkIdx].min) {
                if(minValue > tree[checkIdx].min) {
                    minValue = tree[checkIdx].min;
                    minIdx = tree[checkIdx].idx;
                } else {
                    if(tree[checkIdx].idx < minIdx) minIdx = tree[checkIdx].idx;
                }
            }

            if(s % 2 == 1) s++;
            if(e % 2 == 0) e--;

            s /= 2;
            e /= 2;
        }

        return minIdx - startIdx + 1;
    }

    private static void modifyTree(int b, int c) {
        b += startIdx - 1;
        tree[b].min = c;

        b /= 2;

        while(b > 0) {
            int minNodeIdx = getMinNodeIdx(b);

            tree[b].min = tree[minNodeIdx].min;
            tree[b].idx = tree[minNodeIdx].idx;

            b /= 2;
        }
    }

    static void initInput() throws Exception {
        N = Integer.parseInt(br.readLine());

        int k = getK(N) + 1;

        tree = new Node[(int) Math.pow(2, k)];
        startIdx = tree.length / 2;

        for(int i = 0; i < tree.length; i++) tree[i] = new Node(0, INF);

        st = new StringTokenizer(br.readLine());

        for(int i = startIdx; i < startIdx + N; i++) {
            tree[i].idx = i;
            tree[i].min = Integer.parseInt(st.nextToken());
        }

        setTree();
        M = Integer.parseInt(br.readLine());
    }
    static void setTree() {
        int idx = startIdx - 1;

        for(int i = idx; i >= 0; i--) {

            int minNodeIdx = getMinNodeIdx(i);

            tree[i].min = tree[minNodeIdx].min;
            tree[i].idx = tree[minNodeIdx].idx;
        }
    }

    static int getMinNodeIdx(int i) {
        int minIdx;
        if(tree[i * 2].min <= tree[i * 2 + 1].min) {
            minIdx = i * 2;
        } else {
            minIdx = i * 2 + 1;
        }

        return minIdx;
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
