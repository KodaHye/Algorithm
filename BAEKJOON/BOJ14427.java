import java.io.*;
import java.util.*;

/*
수열과 쿼리 15
 */

public class BOJ14427 {
    static class Node {
        int idx, value;
        public Node(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M, startIdx;
    static Node tree[];

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void solution() throws Exception {
        for(int tc = 0; tc < M; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if(n == 1) {
                int i = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                modifyTree(i, v);
            }

            if(n == 2) sb.append(tree[1].idx + "\n");
        }

        System.out.print(sb);
    }

    static void modifyTree(int i, int v) {
        i += startIdx - 1;
        tree[i].value = v;

        i /= 2;
        while(i > 0) {
            setNodeInfo(i);
            i /= 2;
        }
    }
    static void initInput() throws Exception {
        N = Integer.parseInt(br.readLine());

        int k =  (int) (Math.ceil((Math.log(N) / Math.log(2)))) + 1;
        tree = new Node[(int) Math.pow(2, k)];
        startIdx = tree.length / 2;

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < tree.length; i++) tree[i] = new Node(0, Integer.MAX_VALUE);
        for(int i = startIdx; i < startIdx + N; i++) {
            tree[i].idx = i - startIdx + 1;
            tree[i].value = Integer.parseInt(st.nextToken());
        }
        setTree();
        M = Integer.parseInt(br.readLine());
    }

    static void setTree() {
        for(int i = startIdx - 1; i > 0; i--) {
            setNodeInfo(i);
        }
    }

    static void setNodeInfo(int i) {
        int idx;
        int value;

        if(tree[i * 2].value <= tree[i * 2 + 1].value) {
            idx = tree[i * 2].idx;
            value = tree[i * 2].value;
        } else {
            idx = tree[i * 2 + 1].idx;
            value = tree[i * 2 + 1].value;
        }

        tree[i].value = value; tree[i].idx = idx;
    }
}
