import java.io.*;
import java.util.*;

/*
커피숍2
 */

public class BOJ1275 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, Q, startIdx;
    static long tree[];

    public static void main(String[] args) throws Exception {
        initInput();
        setTree();
        solution();
    }

    static void solution() throws Exception {
        for(int testCase = 0; testCase < Q; testCase++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(getSum(x, y) + "\n");
            update(a, b);
        }

        System.out.print(sb);
    }

    static void update(int a, int b) {
        a += startIdx - 1;
        tree[a] = b;

        a /= 2;

        while(a > 0) {
            updateTree(a);
            a /= 2;
        }
    }

    static void updateTree(int idx) {
        tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
    }
    static long getSum(int s, int e) {

        if(e < s) {
            int tmp = s;
            s = e;
            e = tmp;
        }

        s += startIdx - 1;
        e += startIdx - 1;

        long sum = 0;

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

    static void setTree() {
        for(int i = startIdx - 1; i >= 0; i--) updateTree(i);
    }

    static void initInput() throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        int k = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        tree = new long[(int) Math.pow(2, k)];
        startIdx = tree.length / 2;

        st = new StringTokenizer(br.readLine());
        for(int i = startIdx; i < startIdx + N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }
    }
}
