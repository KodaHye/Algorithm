import java.io.*;
import java.util.*;

/*
최소비용 구하기
 */

public class BOJ1916 {
    static class Node {
        int e, w;

        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
    static int N, M, start, end, d[];
    static ArrayList<Node> adj[];
    static PriorityQueue<Node> queue;
    static boolean v[];

    public static void main(String[] args) throws Exception {
        initInput();
        dijkstra();
    }

    public static void dijkstra() {
        queue.add(new Node(start, 0));
        d[start] = 0;

        while(!queue.isEmpty()) {
            Node current = queue.poll();

            if(v[current.e] && current.e == end) break;
            if(v[current.e]) continue;
            v[current.e] = true;

            for(Node next: adj[current.e]) {
                if(d[next.e] > d[current.e] + next.w) {
                    d[next.e] = d[current.e] + next.w;
                    queue.add(new Node(next.e, d[next.e]));
                }
            }
        }

        System.out.println(d[end]);
    }
    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];
        for(int i = 1; i < adj.length; i++) adj[i] = new ArrayList<>();

        v = new boolean[N + 1];
        d = new int[N + 1];
        Arrays.fill(d, Integer.MAX_VALUE);

        queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.w));

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[s].add(new Node(e, w));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }
}
