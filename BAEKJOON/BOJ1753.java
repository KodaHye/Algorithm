import java.io.*;
import java.util.*;

/*
최단경로
pq 다익스트라로 구현할 때, 방문 처리하는 위치에 주의하기
 */

public class BOJ1753 {
    static class Node {
        int e, w;

        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }

    static boolean v[];
    static ArrayList<Node> adj[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        adj = new ArrayList[V + 1];
        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();
        int K = Integer.parseInt(br.readLine());

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[u].add(new Node(v, w));
        }

        int dis[] = new int[V + 1];
        v = new boolean[V + 1];

        Arrays.fill(dis, Integer.MAX_VALUE);

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.w));
        queue.add(new Node(K, 0));
        dis[K] = 0;

        while(!queue.isEmpty()) {
            Node current = queue.poll();

            if(v[current.e]) continue;
            v[current.e] = true;

            for(Node next: adj[current.e]) {

                if(dis[next.e] > dis[current.e] + next.w) {
                    dis[next.e] = dis[current.e] + next.w;
                    queue.add(new Node(next.e, dis[next.e]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i < dis.length; i++) {
            sb.append((dis[i] == Integer.MAX_VALUE ? "INF" : dis[i]) + "\n");
        }

        System.out.print(sb);
    }
}