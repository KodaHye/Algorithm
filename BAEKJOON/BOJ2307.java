import java.io.*;
import java.util.*;

/*
도로검문
 */

public class BOJ2307 {
    static class Edge {
        int s, e, w;
        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }

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
    static Edge ori[];
    static ArrayList<Node> adj[];
    static PriorityQueue<Node> pq;
    static int N, M, originDis, d[];
    static boolean v[];

    static int dijkstra(int s, int e, Edge edge) {
        pq.add(new Node(s, 0));

        Arrays.fill(d, Integer.MAX_VALUE);
        Arrays.fill(v, false);

        d[s] = 0;

        while(!pq.isEmpty()) {

            Node current = pq.poll();
            if(v[current.e]) continue;
            v[current.e] = true;

            for(Node next: adj[current.e]) {
                if(edge != null && current.e == edge.s && next.e == edge.e) continue;

                if(d[next.e] > d[current.e] + next.w) {
                    d[next.e] = d[current.e] + next.w;
                    pq.add(new Node(next.e, d[next.e]));
                }
            }
        }


        return d[e];
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ori = new Edge[M];
        adj = new ArrayList[N + 1];
        d = new int[N + 1];
        v = new boolean[N + 1];
        pq = new PriorityQueue<>();
        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            ori[i] = new Edge(a, b, w);
            adj[a].add(new Node(b, w));
            adj[b].add(new Node(a, w));
        }

        originDis = dijkstra(1, N, null);

        int dis = Integer.MIN_VALUE;

        for(int i = 0; i < ori.length; i++) {
            dis = Math.max(dis, dijkstra(1, N, ori[i]));
        }

        if(dis == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(dis - originDis);
    }
}
