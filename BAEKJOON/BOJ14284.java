import java.io.*;
import java.util.*;

/*
간선 이어가기 2
 */

public class BOJ14284 {
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
    static int S, T, d[];
    static ArrayList<Node> adj[];
    static PriorityQueue<Node> q;

    static void dijkstra() {
        d = new int[adj.length];
        Arrays.fill(d, Integer.MAX_VALUE);
        q = new PriorityQueue<>();

        q.add(new Node(S, 0));
        d[S] = 0;

        while(!q.isEmpty()) {
            Node current = q.poll();

            if(current.e == T) break;
            for(Node next: adj[current.e]) {

                if(d[next.e] > d[current.e] + next.w) {
                    d[next.e] = d[current.e] + next.w;
                    q.add(new Node(next.e, d[next.e]));
                }
            }
        }

    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, c));
            adj[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        dijkstra();
        System.out.println(d[T]);
    }
}
