import java.io.*;
import java.util.*;

/*
트리의 지름
 */

public class BOJ1167 {
    static class Node {
        int n, w, dis;

        public Node(int n, int w, int dis) {
            this.n = n;
            this.w = w;
            this.dis = dis;
        }
    }
    static ArrayList<Node> adj[];
    static int end, result;
    static boolean v[];
    static LinkedList<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        initInput();

        bfs(1);
        bfs(end);

        System.out.println(result);
    }

    public static void bfs(int start) {
        Arrays.fill(v, false);
        queue.add(new Node(start, 0, 0));
        v[start] = true;

        int dis = 0;

        while(!queue.isEmpty()) {
            Node current = queue.poll();

            if(dis < current.dis) {
                dis = current.dis;
                end = current.n;
                result = dis;
            }

            for(Node next: adj[current.n]) {
                if(v[next.n]) continue;

                v[next.n] = true;
                queue.add(new Node(next.n, next.w, next.w + current.dis));
            }
        }
    }

    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int V = Integer.parseInt(br.readLine());

        adj = new ArrayList[V + 1];
        v = new boolean[V + 1];

        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());

            while(true) {
                int b = Integer.parseInt(st.nextToken());
                if(b == -1) break;
                int w = Integer.parseInt(st.nextToken());

                adj[a].add(new Node(b, w, 0));
            }
        }
    }
}
