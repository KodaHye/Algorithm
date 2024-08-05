import java.io.*;
import java.util.*;

/*
백도어
 */

public class BOJ17396 {
    static long INF = Long.MAX_VALUE;
    static class Node implements Comparable<Node> {
        int e;
        long w;
        public Node(int e, long w) {
            this.e = e;
            this.w = w;
        }
        @Override
        public int compareTo(Node o) {
            return Long.compare(this.w, o.w);
        }
    }
    static boolean v[];
    static PriorityQueue<Node> pq;
    static ArrayList<Node> adj[];
    static int N, M;
    static long d[];

    public static void main(String[] args) throws Exception {
        initInput();
        dijkstra();
    }

    static void dijkstra() {
        Arrays.fill(d, INF);

        pq.add(new Node(0, 0));
        d[0] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(current.e == N - 1) break;
            if(v[current.e]) continue;

            v[current.e] = true;

            for(Node next: adj[current.e]) {
                if(next.e != N - 1 && v[next.e]) continue;
                if(d[next.e] > d[current.e] + next.w) {
                    d[next.e] = d[current.e] + next.w;
                    pq.add(new Node(next.e, d[next.e]));
                }
            }
        }

        System.out.println(d[N - 1] == INF ? -1 : d[N - 1]);
    }

    static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        pq = new PriorityQueue<>();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N];
        v = new boolean[N];
        d = new long[N];

        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            if(Integer.parseInt(st.nextToken()) == 1) v[i] = true;
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, t));
            adj[b].add(new Node(a, t));
        }
    }
}
