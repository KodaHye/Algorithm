import java.io.*;
import java.util.*;

/*
오민식의 고민
 */

public class BOJ1219 {
    static long INF = Long.MIN_VALUE;
    static class Edge {
        int s, e, w;
        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
    static Edge edges[];
    static boolean cycle[];
    static int N, S, E, M, money[];
    static long d[];
    static LinkedList<Integer> queue;

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    public static void solution() {
        d[S] = money[S];

        for(int i = 0; i < N - 1; i++) {
            for(Edge e: edges) {
                if(d[e.s] != INF && d[e.e] < d[e.s] + e.w + money[e.e]) {
                    d[e.e] = d[e.s] + e.w + money[e.e];
                }
            }
        }

        if(d[E] == INF) {
            System.out.println("gg");
            return;
        }

        for(Edge e: edges) {
            if(d[e.s] != INF &&
                    d[e.e] < d[e.s] + e.w + money[e.e]) {
                checkCycle(e.s);
            }
        }

        if(cycle[E]) System.out.println("Gee");
        else System.out.println(d[E]);
    }

    private static void checkCycle(int s) {
        queue.add(s);
        cycle[s] = true;

        while(!queue.isEmpty()) {
            int current = queue.poll();

            for(Edge edge: edges) {
                if(edge.s == current && !cycle[edge.e]) {
                    queue.add(edge.e);
                    cycle[edge.e] = true;
                }
            }
        }
    }

    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        d = new long[N];
        Arrays.fill(d, INF);

        cycle = new boolean[N];
        edges = new Edge[M];
        queue = new LinkedList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(s, e, -1 * w);
        }
        money = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) money[i] = Integer.parseInt(st.nextToken());
    }
}
