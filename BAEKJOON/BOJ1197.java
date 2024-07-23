import java.io.*;
import java.util.*;

/*
최소 스패닝 트리
 */

public class BOJ1197 {
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
    static int V, E, p[];
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws Exception {
        initInput();
        mst();
    }

    public static void mst() {
        int cnt = 0;
        int result = 0;

        while(cnt < V - 1) {
            Edge e = pq.poll();

            if(find(e.s) == find(e.e)) continue;
            union(e.s, e.e);
            result += e.w;
            cnt++;
        }

        System.out.println(result);
    }
    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) p[b] = a;
    }
    public static int find(int a) {
        return a == p[a] ? p[a] : find(p[a]);
    }
    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        p = new int[V + 1];
        for(int i = 0; i < p.length; i++) p[i] = i;

        pq = new PriorityQueue<>();

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.add(new Edge(a, b, c));
        }
    }

}
