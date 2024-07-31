import java.io.*;
import java.util.*;

/*
트리
 */

public class BOJ10838 {
    static int INF = 1_000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M, p[], color[];
    static HashSet<Integer> set;
    static boolean check[];

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void solution() throws Exception {
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            if(r == 1) paint(st);
            if(r == 2) move(st);
            if(r == 3) sb.append(count(st) + "\n");
        }

        System.out.print(sb);
    }

    static int count(StringTokenizer st) {
        set.clear();

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int root = LCA(a, b);
        while(a != root) {
            set.add(color[a]);
            a = p[a];
        }

        while(b != root) {
            set.add(color[b]);
            b = p[b];
        }

        return set.size();
    }

    static void move(StringTokenizer st) {
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        p[a] = b;
    }
    static int LCA(int a, int b) {
        check = new boolean[N];

        for(int i = 0; i < INF; i++) {
            check[a] = true;
            a = p[a];
        }

        for(int i = 0; i < INF; i++) {
            if(check[b]) return b;
            b = p[b];
        }

        return -1;
    }
    static void paint(StringTokenizer st) {
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        int root = LCA(a, b);
        while(a != root) {
            color[a] = c;
            a = p[a];
        }

        while(b != root) {
            color[b] =c;
            b = p[b];
        }
    }

    static void initInput() throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        p = new int[N];

        color = new int[N];
        set = new HashSet<>();
    }
}
