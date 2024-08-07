import java.io.*;
import java.util.*;

/*
음주 코딩
 */

public class BOJ5676 {
    static class Node {
        int zero, minus;

        public Node(int zero, int minus) {
            this.zero = zero;
            this.minus = minus;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N, K, startIdx;
    static Node tree[];
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {

        String input;
        while((input = br.readLine()) != null) {
            st = new StringTokenizer(input);

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            int k = (int) (Math.ceil(Math.log(N) / Math.log(2))) + 1;
            tree = new Node[(int) Math.pow(2, k)];
            startIdx = tree.length / 2;

            setTree();

            for(int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());

                int ch = st.nextToken().charAt(0);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(ch == 'C') modifyTree(a, b);
                if(ch == 'P') getMultifly(a, b);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void modifyTree(int a, int b) {
        int idx = startIdx + a - 1;

        tree[idx].zero = 0;
        tree[idx].minus = 0;

        if(b == 0) tree[idx].zero += 1;
        if(b < 0) tree[idx].minus += 1;

        idx /= 2;
        while(idx > 0) {
            updateTree(idx);
            idx /= 2;
        }
    }

    static void updateTree(int i) {
        int zeroCnt = 0, plusCnt = 0, minusCnt = 0;

        if(tree[i * 2] != null) {
            zeroCnt += tree[i * 2].zero;
            minusCnt += tree[i * 2].minus;
        }

        if(tree[i * 2 + 1] != null) {
            zeroCnt += tree[i * 2 + 1].zero;
            minusCnt += tree[i * 2 + 1].minus;
        }

        tree[i].zero = zeroCnt;
        tree[i].minus = minusCnt;

    }
    static void getMultifly(int a, int b) {
        a += startIdx - 1;
        b += startIdx - 1;

        int zeroCnt = 0;
        int minusCnt = 0;

        while(a <= b) {
            if(a % 2 == 1) {
                zeroCnt += tree[a].zero;
                minusCnt += tree[a].minus;
                a++;
            }

            if(b % 2 ==0) {
                zeroCnt += tree[b].zero;
                minusCnt += tree[b].minus;
                b--;
            }

            a /= 2;
            b /= 2;
        }

        if(zeroCnt > 0) sb.append('0');
        else if(minusCnt % 2 == 0) sb.append('+');
        else sb.append('-');
    }
    static void setTree() throws Exception {
        st = new StringTokenizer(br.readLine());

        for(int i = startIdx; i < startIdx + N; i++) {
            int num = Integer.parseInt(st.nextToken());
            int zeroCnt = 0, minusCnt = 0;

            if(num == 0) zeroCnt++;
            if(num < 0) minusCnt++;

            tree[i] = new Node(zeroCnt, minusCnt);
        }

        for(int i = startIdx - 1; i >= 0; i--) {
            tree[i] = new Node(0, 0);
            updateTree(i);
        }
    }
}
