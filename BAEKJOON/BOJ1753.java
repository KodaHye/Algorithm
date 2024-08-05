import java.io.*;
import java.util.*;

/*
최단경로
 */

public class BOJ1753 {
    static int INF = Integer.MAX_VALUE;
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
    static int V, E, S, dis[];
    static ArrayList<Node> adj[];
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        initInput();
        dijkstra();
    }

    static void dijkstra() {
        Arrays.fill(dis, INF);
        pq.add(new Node(S, 0));

        dis[S] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            for(Node next: adj[current.e]) {
                if(dis[next.e] > dis[current.e] + next.w) {
                    dis[next.e] = dis[current.e] + next.w;
                    pq.add(new Node(next.e, dis[next.e]));
                }
            }
        }

        for(int i = 1; i < dis.length; i++) sb.append(dis[i] == INF ? "INF": dis[i]).append("\n");

        System.out.print(sb);
    }

    static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        S = Integer.parseInt(br.readLine());

        adj = new ArrayList[V + 1];
        dis = new int[V + 1];

        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[u].add(new Node(v, w));
        }
    }
}
