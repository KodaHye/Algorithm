import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
최단경로
 */

public class BOJ1753 {
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
    static int V, E;
    static ArrayList<Node> adj[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adj = new ArrayList[V + 1];
        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        int start = Integer.parseInt(br.readLine());

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[s].add(new Node(e, w));
        }

        int d[] = new int[V + 1];

        Arrays.fill(d, Integer.MAX_VALUE);
        boolean v[] = new boolean[V + 1];

        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.add(new Node(start, 0));
        d[start] = 0;

        while(!queue.isEmpty()) {
            Node current = queue.poll();

            for (Node next:adj[current.e]) {
                if(d[next.e] > current.w + next.w) {
                    d[next.e] = current.w + next.w;
                    queue.add(new Node(next.e, d[next.e]));
                }
            }
        }

        for(int i = 1; i < d.length; i++) {
            if(d[i] == Integer.MAX_VALUE) sb.append("INF");
            else sb.append(d[i]);
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
