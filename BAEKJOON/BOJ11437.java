import java.io.*;
import java.util.*;

/*
LCA
 */

public class BOJ11437 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M, p[], d[];
    static ArrayList<Integer> adj[];

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void solution() throws Exception {
        bfs();

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

        if(d[a] > d[b]) {
            int tmp = b;
            b = a;
            a = tmp;
        }

        while(d[a] != d[b]) b = p[b];

        while(a != b) {
            a = p[a];
            b = p[b];
        }
        return a;
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        d[1] = 1;

        while(!q.isEmpty()) {
            int current = q.poll();

            for(int next: adj[current]) {
                if(d[next] != 0) continue;
                q.add(next);
                d[next] = d[current] + 1;
                p[next] = current;
            }
        }
    }
    static void initInput() throws Exception {
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N + 1];
        p = new int[N + 1];
        d = new int[N + 1];

        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }
    }
}
