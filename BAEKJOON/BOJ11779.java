import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11779 {
    /*
    최소비용 구하기 2
     */
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

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static ArrayList<Node> adj[];
    static ArrayList<Integer> path[];

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        adj = new ArrayList[n + 1];
        path = new ArrayList[n + 1];

        int d[] = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);

        for(int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
            path[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[s].add(new Node(e, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.add(new Node(start, 0));
        path[start].add(start);

        d[start] = 0;

        while(!queue.isEmpty()) {
            Node current = queue.poll();

            if(d[current.e] < current.w) continue;
            for(Node next: adj[current.e]) {
                if(d[next.e] > current.w + next.w) {
                    d[next.e] = current.w + next.w;
                    queue.add(new Node(next.e, d[next.e]));

                    path[next.e].clear();

                    for(int i = 0; i < path[current.e].size(); i++) {
                        path[next.e].add(path[current.e].get(i));
                    }

                    path[next.e].add(next.e);

                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(d[end]).append("\n");
        sb.append(path[end].size()).append("\n");

        for(int i = 0; i < path[end].size(); i++) {
            sb.append(path[end].get(i) + " ");
        }

        System.out.println(sb);
    }
}
