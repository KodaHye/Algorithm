import java.io.*;
import java.util.*;

/*
정점들의 거리
 */

public class BOJ1761 {
    static class Node {
        int e, w;
        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static ArrayList<Node> adj[];
    static int N, M, depth[], dis[], p[], parents[][];
    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void solution() throws Exception {
        bfs();
        fillParents();

        for(int testCase = 0; testCase < M; testCase++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(getDis(a, b) + "\n");
        }

        System.out.print(sb);
    }

    static void fillParents() {
        int depth = (int) (Math.log(N) / Math.log(2)) + 1;

        parents = new int[depth][N + 1];
        parents[0] = Arrays.copyOf(p, p.length);

        for(int k = 1; k < parents.length; k++) {
            for(int n = 1; n < parents[k].length; n++)
                parents[k][n] = parents[k - 1][parents[k - 1][n]];
        }

    }
    static int getDis(int a, int b) {
        int sumDis = dis[a] + dis[b];
        int depthA = depth[a];
        int depthB = depth[b];

        if(depthA > depthB) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        int diff = Math.abs(depthA - depthB);
        while(diff > 0) {
            int k = (int) (Math.log(diff) / Math.log(2));
            b = parents[k][b];
            diff -= (int) Math.pow(2, k);
        }

        for(int k = parents.length - 1; k >= 0; k--) {
            if(parents[k][a] != parents[k][b]) {
                a = parents[k][a];
                b = parents[k][b];
            }
        }

        int p = a;
        if(a != b) p = parents[0][a];


        return sumDis - 2 * dis[p];
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(1, 0));
        depth[1] = 1;

        while(!q.isEmpty()) {
            Node current = q.poll();

            for(Node next: adj[current.e]) {
                if(depth[next.e] != 0) continue;
                depth[next.e] = depth[current.e] + 1;
                p[next.e] = current.e;
                dis[next.e] = dis[current.e] + next.w;
                q.add(next);
            }
        }
    }
    static void initInput() throws Exception {
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N + 1];
        depth = new int[N + 1];
        dis = new int[N + 1];
        p = new int[N + 1];

        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, c));
            adj[b].add(new Node(a, c));
        }

        M = Integer.parseInt(br.readLine());
    }
}
