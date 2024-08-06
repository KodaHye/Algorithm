import java.io.*;
import java.util.*;

/*
특정한 최단 경로
 */

public class BOJ1504 {
    static int INF = 8_000_001;
    static class Node implements Comparable<Node> {
        int e, w;
        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }
    static int N, M, S, E, d[];
    static ArrayList<Node> adj[];
    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void solution() {
        int result;
        int d1 = dijkstra(S, E);
        int d2 = dijkstra(1, S) + dijkstra(adj.length - 1, E);
        int d3 = dijkstra(1, E) + dijkstra(adj.length - 1, S);

        result = Math.min(d1 + d2, d1 + d3);
        if(result >= INF) result = -1;
        System.out.println(result);
    }

    static int dijkstra(int a, int b) {
        int shortDis = INF;
        PriorityQueue<Node> pq = new PriorityQueue<>();

        Arrays.fill(d, INF);

        pq.add(new Node(a, 0));
        d[a] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(current.e == b) shortDis = Math.min(shortDis, d[current.e]);
            for(Node next: adj[current.e]) {
                if(d[next.e] > d[current.e] + next.w) {
                    d[next.e] = d[current.e] + next.w;
                    pq.add(new Node(next.e, d[next.e]));
                }
            }
        }
        return shortDis;
    }
    static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        d = new int[N + 1];

        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[u].add(new Node(v, c));
            adj[v].add(new Node(u, c));
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
    }
}
