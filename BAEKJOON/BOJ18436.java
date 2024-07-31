import java.io.*;
import java.util.*;

/*
수열과 쿼리 37
 */

public class BOJ18436 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M;
    static int startIdx, evenCntTree[];

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void solution() throws Exception {
        M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int l = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(l == 1) modify(a, b);
            else if(l == 2) sb.append(getEvenCnt(a, b) + "\n");
            else if(l == 3) sb.append((b - a + 1 - getEvenCnt(a, b)) + "\n");
            else return;
        }

        System.out.print(sb);
    }

    static int getEvenCnt(int s, int e) {
        s += startIdx - 1;
        e += startIdx - 1;

        int result = 0;

        while(s <= e) {
            if(s % 2 == 1) {
                result += evenCntTree[s];
                s++;
            }
            if(e % 2 == 0) {
                result += evenCntTree[e];
                e--;
            }

            s /= 2;
            e /= 2;
        }

        return result;
    }

    static void modify(int i, int x) {
        i += startIdx - 1;

        if(x % 2 == 0) evenCntTree[i] = 1;
        else evenCntTree[i] = 0;

        i /= 2;
        while(i > 0) {
            updateTree(i);
            i /= 2;
        }
    }

    static void updateTree(int i) {
        evenCntTree[i] = evenCntTree[i * 2] + evenCntTree[i * 2 + 1];
    }
    static void initInput() throws Exception {
        N = Integer.parseInt(br.readLine());
        int k = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;

        evenCntTree = new int[(int) Math.pow(2, k)];
        startIdx = evenCntTree.length / 2;

        st = new StringTokenizer(br.readLine());
        for(int i = startIdx; i < startIdx + N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if(n % 2 == 0) evenCntTree[i]++;
        }

        setTree();
    }

    static void setTree() {
        for(int i = startIdx - 1; i >= 0; i--) {
            updateTree(i);
        }
    }
}
