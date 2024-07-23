import java.io.*;
import java.util.*;

/*
나만 안되는 연애
 */

public class BOJ14621 {
    static class Edge {
        int s, e, w;
        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
    static PriorityQueue<Edge> pq;
    static int p[];
    static char gender[];

    public static void main(String[] args) throws Exception {
        initInput();
        mst();
    }

    public static void mst() {
        int cnt = 0;
        int result = 0;

        while(!pq.isEmpty() && cnt < p.length - 2) {
            Edge e = pq.poll();

            if((find(e.s) == find(e.e)) || (gender[e.s] == gender[e.e])) continue;
            union(e.s, e.e);
            cnt++;
            result += e.w;
        }

        int parent = find(p[1]);
        for(int i = 2; i < p.length; i++) {
            if(parent != find(p[i])) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(result);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) p[b] = a;
    }
    public static int find(int a) {
        return a == p[a] ? a : find(p[a]);
    }
    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        gender = new char[N + 1];
        p = new int[N + 1];
        for(int i = 0; i < p.length; i++) p[i] = i;

        String s = br.readLine();
        for(int i = 0; i < gender.length - 1; i++) {
            gender[i + 1] = s.charAt(i * 2);
        }

        pq = new PriorityQueue<>(Comparator.comparing(o -> o.w));
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if(gender[u] == gender[v]) continue;
            pq.add(new Edge(u, v, d));
            pq.add(new Edge(v, u, d));
        }
    }
}