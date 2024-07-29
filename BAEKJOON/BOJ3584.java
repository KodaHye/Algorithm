import java.util.*;
import java.io.*;

/*
가장 가까운 공통 조상
 */

public class BOJ3584 {
    static int N, T, d[], p[];
    static boolean isNotRoot[];
    static ArrayList<Integer> adj[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < T; testCase++) {
            N = Integer.parseInt(br.readLine());
            adj = new ArrayList[N + 1];
            d = new int[N + 1];
            p = new int[N + 1];
            isNotRoot = new boolean[N + 1];

            for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

            for(int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                adj[a].add(b);
                isNotRoot[b] = true;
            }

            int root = 0;
            for(int i = 1; i < isNotRoot.length; i++) {
                if(!isNotRoot[i]) {
                    root = i;
                    break;
                }
            }

            bfs(root);

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(LCA(a, b) + "\n");
        }

        System.out.print(sb);
    }

    static int LCA(int a, int b) {
        int depthA = d[a];
        int depthB = d[b];

        if(depthA > depthB) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        for(int i = 0; i < Math.abs(depthA - depthB); i++) {
            b = p[b];
        }

        while(a != b) {
            a = p[a];
            b = p[b];
        }

        return a;
    }

    static void bfs(int root) {
        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        d[root] = 1;

        while(!q.isEmpty()) {
            int current = q.poll();

            for(int next: adj[current]) {
                if(d[next] != 0) continue;;
                d[next] = d[current] + 1;
                p[next] = current;
                q.add(next);
            }
        }
    }
}
