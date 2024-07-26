import java.io.*;
import java.util.*;

/*
구간 합 구하기
 */

public class BOJ2042 {
    static BufferedReader br;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M, K, startIdx;
    static long tree[];
    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void solution() throws Exception {
        setTree();

        for(int i = 0; i < K; i++) {

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a == 1) change(b, c);
            else if(a == 2) getSum(b, c);
            else return;
        }

        System.out.print(sb);
    }

    private static void getSum(int s, long e) {
        long sum = 0;

        s += startIdx - 1;
        e += startIdx - 1;

        while(s <= e) {

            if(s % 2 == 1) {
                sum += tree[s];
                s++;
            }
            if(e % 2 == 0) {
                sum += tree[(int) e];
                e--;
            }

            s /= 2;
            e /= 2;
        }

        sb.append(sum + "\n");
    }


    private static void change(int idx, long c) {
        tree[startIdx + idx - 1] = c;

        int i = (startIdx + idx - 1) / 2;
        while(i > 0) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
            i /= 2;
        }
    }

    static void initInput() throws Exception {
        System.setIn(new FileInputStream("C:\\Users\\kodhd\\Desktop\\Algorithm\\BAEKJOON\\input\\BOJ2042.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int cnt = getK(N);
        tree = new long[(int) Math.pow(2, cnt + 1)];

        startIdx = tree.length / 2;
        for(int i = startIdx; i < startIdx + N; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }
    }

    private static void setTree() {
        for(int i = startIdx - 1; i >= 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    private static int getK(int n) {
        int cnt = 0;
        while(n != 0) {
            n /= 2;
            cnt++;
        }
        return cnt;
    }
}
