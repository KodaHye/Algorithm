import java.io.*;
import java.util.*;

/*
파티
 */

public class BOJ1238 {
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
    static PriorityQueue<Node> pq;
    static int X, result, d[];
    static ArrayList<Node> adj[];
    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }
    static void solution() {
        for(int i = 1; i < adj.length; i++) {
            result = Math.max(result, dijkstra(i, X) + dijkstra(X, i));
        }

        System.out.println(result);
    }
    static int dijkstra(int start, int end) {
        if(start == end) return 0;
        int result = Integer.MAX_VALUE;

        Arrays.fill(d, Integer.MAX_VALUE);

        pq.clear();
        pq.add(new Node(start, 0));
        d[start] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            if(current.e == end) {
                result = Math.min(result, d[current.e]);
                break;
            }

            for(Node next: adj[current.e]) {
                if(d[next.e] > d[current.e] + next.w) {
                    d[next.e] = d[current.e] + next.w;
                    pq.add(new Node(next.e, d[next.e]));
                }
            }
        }
        return result;
    }

    static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        result = Integer.MIN_VALUE;
        pq = new PriorityQueue<>();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        d = new int[N + 1];
        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, c));
        }
    }
}
