import java.io.*;
import java.util.*;

/*
K번째 최단경로 찾기
 */

public class BOJ1854 {
    static class Node {
        int e, w;

        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
    static int N, M, K;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Node> adj[];
    static PriorityQueue<Node> pq;
    static PriorityQueue<Integer> shortestDist[];

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    public static void solution() {
        pq = new PriorityQueue<>(Comparator.comparing(o -> o.w));
        pq.add(new Node(1, 0));
        shortestDist[1].add(0);

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            for(Node next: adj[current.e]) {
                int nextDis = current.w + next.w;
                if(shortestDist[next.e].size() < K) shortestDist[next.e].add(nextDis);
                else {
                    if(shortestDist[next.e].peek() <= nextDis) continue;
                    else {
                        shortestDist[next.e].poll();
                        shortestDist[next.e].add(nextDis);
                    }
                }
                pq.add(new Node(next.e, nextDis));
            }
        }

        for(int i = 1; i < shortestDist.length; i++) {
            if(shortestDist[i].size() < K) {
                sb.append("-1\n");
                continue;
            }

            sb.append(shortestDist[i].peek() + "\n");
        }

        System.out.print(sb);
    }
    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        shortestDist = new PriorityQueue[N + 1];

        for(int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
            shortestDist[i] = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, c));
        }
    }
}
