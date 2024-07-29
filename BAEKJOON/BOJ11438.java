import java.io.*;
import java.util.*;

/*
LCA 2
 */

public class BOJ11438 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M, p[], d[], parents[][];
    static ArrayList<Integer> adj[];

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void solution() throws Exception {
        M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++) {
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

        int diff = Math.abs(depthA - depthB);
        while(diff > 0) {
            int k = getK(diff);
            b = parents[k][b];
            diff -= (int) Math.pow(2, k);
        }

        for(int i = parents.length - 1; i >= 0; i--) {
            if(parents[i][a] != parents[i][b]) {
                a = parents[i][a];
                b = parents[i][b];
            }
        }

        int result = a;
        if(a != b) result = parents[0][result];
        return result;
    }

    static int getK(int n) {
        return (int) (Math.log(n) / Math.log(2));
    }
    static void initInput() throws Exception {
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N + 1];
        d = new int[N + 1];
        p = new int[N + 1];

        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }
        int depth = bfs();
        parents = new int[(int) (Math.log(depth) / Math.log(2)) + 1][N + 1];

        parents[0] = Arrays.copyOf(p, p.length);

        for(int k = 1; k < parents.length; k++) {
            for(int n = 1; n < parents[k].length; n++) {
                parents[k][n] = parents[k - 1][parents[k - 1][n]];
            }
        }
    }

    static int bfs() {
        int depth = Integer.MIN_VALUE;

        Queue<Integer> q = new PriorityQueue<>();
        q.add(1);
        d[1] = 1;

        while(!q.isEmpty()) {
            int current = q.poll();
            depth = Math.max(depth, d[current]);

            for(int next: adj[current]) {
                if(d[next] != 0) continue;
                q.add(next);
                p[next] = current;
                d[next] = d[current] + 1;
            }
        }

        return depth;
    }
}
