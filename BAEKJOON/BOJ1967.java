import java.io.*;
import java.util.*;

/*
트리의 지름
 */

public class BOJ1967 {
    static class Node {
        int e, w;

        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
    static ArrayList<Node> adj[];
    static boolean v[];
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    public static void solution() {
        System.out.println(bfs(bfs(1).e).w);
    }

    static Node bfs(int start) {
        Node end = new Node(0, Integer.MIN_VALUE);
        queue.add(new Node(start, 0));
        v[start] = true;

        while(!queue.isEmpty()) {
            Node current = queue.poll();

            if(end.w < current.w) {
                end = current;
            }
            for(Node next: adj[current.e]) {
                if(v[next.e]) continue;
                queue.add(new Node(next.e, current.w + next.w));
                v[next.e] = true;
            }
        }
        Arrays.fill(v, false);
        return end;
    }

    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n + 1];
        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        v = new boolean[n + 1];

        for(int r = 0; r < n - 1; r++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, w));
            adj[b].add(new Node(a, w));
        }
    }
}
