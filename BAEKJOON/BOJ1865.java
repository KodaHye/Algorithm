import java.io.*;
import java.util.*;

/*
웜홀
 */

public class BOJ1865 {
    static class Edge {
        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
    static int N, M, W, d[];
    static Edge[] edges;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        initInput();
        System.out.print(sb);
    }

    public static void solution() {
        for(int i = 0; i < edges.length; i++) {
            Edge e = edges[i];
            d[e.e] = e.w;
        }

        for(int i = 0; i < N - 2; i++) {
            for(int j = 0; j < edges.length; j++) {
                Edge e = edges[j];

                if(d[e.s] != Integer.MAX_VALUE && d[e.e] > d[e.s] + e.w) {
                    d[e.e] = d[e.s] + e.w;
                }
            }
        }

        boolean cycle = false;
        for(int i = 0; i < edges.length; i++) {
            Edge e = edges[i];
            if(d[e.s] != Integer.MAX_VALUE && d[e.e] > d[e.s] + e.w) {
                cycle = true;
                break;
            }
        }

        if(cycle) sb.append("YES\n");
        else sb.append("NO\n");
    }

    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < TC; testCase++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            edges = new Edge[2 * M + W];
            d = new int[N + 1];

            Arrays.fill(d, Integer.MAX_VALUE);

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                edges[2 * i] = new Edge(a, b, w);
                edges[2 * i + 1] = new Edge(b, a, w);
            }

            for(int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                edges[2 * M + i] = new Edge(a, b, -1 * w);
            }

            solution();
        }
    }
}
