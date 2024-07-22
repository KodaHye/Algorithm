import java.io.*;
import java.util.*;

/*
타임머신
 */

public class BOJ11657 {
    static class Edge {
        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
    static int N, M;
    static Edge edges[];
    static boolean cycle;
    static long d[];

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    public static void solution() {
        d[1] = 0;

        for(int i = 0; i < N - 1; i++) {
            for(int j = 0; j < M; j++) {
                Edge e = edges[j];

                if(d[e.s] != Integer.MAX_VALUE && d[e.e] > d[e.s] + e.w) {
                    d[e.e] = d[e.s] + e.w;
                }
            }
        }

        for(int i = 0; i < M; i++) {
            Edge e = edges[i];

            if(d[e.s] != Integer.MAX_VALUE && d[e.e] > d[e.s] + e.w) {
                cycle = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        if(cycle) sb.append(-1).append("\n");
        else {
            for(int i = 2; i < d.length; i++) {
                if(d[i] == Integer.MAX_VALUE) sb.append("-1").append("\n");
                else sb.append(d[i]).append("\n");
            }
        }

        System.out.print(sb);
    }
    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        d = new long[N + 1];
        edges = new Edge[M + 1];

        Arrays.fill(d, Integer.MAX_VALUE);
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(A, B, C);
        }
    }
}
