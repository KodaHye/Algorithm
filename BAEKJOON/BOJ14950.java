import java.io.*;
import java.util.*;

/*
정복자
 */

public class BOJ14950 {
    static class Edge implements Comparable<Edge> {
        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static PriorityQueue<Edge> pq;
    static int N, M, t, p[];

    public static void main(String[] args) throws Exception {
        initInput();
        mst();
    }

    static void mst() {
        int cnt = 0;
        long result = 0;

        while(cnt < N - 1) {
            Edge current = pq.poll();

            if(find(current.s) == find(current.e)) continue;
            union(current.s, current.e);

            result += current.w + (cnt * t);
            cnt++;
        }

        System.out.println(result);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) p[b] = a;
    }
    static int find(int a) {
        return p[a] = a == p[a] ? a : find(p[a]);
    }
    static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();
        p = new int[N + 1];
        for(int i = 0; i < p.length; i++) p[i] = i;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.add(new Edge(a, b, c));
        }
    }
}
