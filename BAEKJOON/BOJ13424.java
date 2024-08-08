import java.io.*;
import java.util.*;

/*
비밀 모임
 */

public class BOJ13424 {
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
    static StringBuilder sb = new StringBuilder();
    static int N, M, K, result, minDis, d[];
    static HashSet<Integer> set;
    static PriorityQueue<Node> pq;
    static ArrayList<Node> adj[];
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());

        set = new HashSet<>();
        pq = new PriorityQueue<>();

        for(int testCase = 0; testCase < T; testCase++) {
            result = Integer.MAX_VALUE;
            minDis = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            initAdj();

            K = Integer.parseInt(br.readLine());
            initSet();
            solution();
            sb.append(result + "\n");
        }

        System.out.print(sb);
    }

    static void solution() {
        for(int i = 1; i < N + 1; i++) {
            int getDis = dijkstra(i);
            if(minDis <= getDis) continue;
            minDis = getDis;
            result = i;
        }
    }

    static int dijkstra(int start) {
        int getDis = 0;

        pq.clear();
        d = new int[N + 1];
        boolean v[] = new boolean[N + 1];
        Arrays.fill(d, Integer.MAX_VALUE);

        pq.add(new Node(start, 0));
        d[start] = 0;

        int visitCnt = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(v[current.e]) continue;
            v[current.e] = true;

            if(set.contains(current.e)) {
                visitCnt++;
                getDis += d[current.e];
                if(visitCnt == set.size()) break;
            }

            for(Node next: adj[current.e]) {
                if(d[next.e] > d[current.e] + next.w) {
                    d[next.e] = d[current.e] + next.w;
                    pq.add(new Node(next.e, d[next.e]));
                }
            }
        }

        if(visitCnt != set.size()) return Integer.MAX_VALUE;
        return getDis;
    }

    static void initSet() throws Exception {
        set.clear();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }
    }
    static void initAdj() throws Exception {
        adj = new ArrayList[N + 1];
        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, c));
            adj[b].add(new Node(a, c));
        }
    }
}
