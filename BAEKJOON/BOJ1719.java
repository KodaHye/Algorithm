import java.io.*;
import java.util.*;

/*
택배
 */

public class BOJ1719 {
    static class Node implements Comparable<Node> {
        int s, e, w;

        public Node(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }
    static StringBuilder sb = new StringBuilder();
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int d[];
    static Node arr[][];
    static ArrayList<Node> adj[];
    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void solution() {
        for(int i = 1; i < adj.length; i++) {
            dijkstra(i);
        }

        for(int r = 1; r < arr.length; r++) {
            for(int c = 1; c < arr[r].length; c++) {
                if(r == c) sb.append("- ");
                else sb.append(arr[r][c].e + " ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void dijkstra(int s) {
        pq.clear();
        Arrays.fill(d, Integer.MAX_VALUE);

        d[s] = 0;

        for(int i = 0; i < adj[s].size(); i++) {
            int start = adj[s].get(i).e;
            pq.add(new Node(start, start, adj[s].get(i).w));
            d[adj[s].get(i).e] = adj[s].get(i).w;
        }

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            int i = current.e;

            if(arr[s][i].w > d[i]) {
                arr[s][i].w = d[i];
                arr[s][i].e = current.s;
            }
            for(Node next: adj[current.e]) {
                if(d[next.e] > d[current.e] + next.w) {
                    d[next.e] = d[current.e] + next.w;
                    pq.add(new Node(current.s, next.e, d[next.e]));
                }
            }
        }
    }

    static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new Node[n + 1][n + 1];
        for(int r = 0; r < arr.length; r++) {
            for(int c = 0; c < arr[r].length; c++) arr[r][c] = new Node(0, 0, Integer.MAX_VALUE);
        }
        adj = new ArrayList[n + 1];
        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        d = new int[n + 1];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[u].add(new Node(0, v, w));
            adj[v].add(new Node(0, u, w));
        }
    }
}
