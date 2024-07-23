import java.io.*;
import java.util.*;

/*
불우이웃돕기
 */

public class BOJ1414 {
    static class Edge {
        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "s=" + s +
                    ", e=" + e +
                    ", w=" + w +
                    '}';
        }
    }
    static int total, p[];
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws Exception {
        initInput();
        mst();
    }

    public static void mst() {
        int result = 0;
        int cnt = 0;

        while(!pq.isEmpty() && cnt < p.length - 1) {
            Edge e = pq.poll();

            if(find(e.s) == find(e.e)) continue;
            union(e.s, e.e);
            result += e.w;
            cnt++;
        }

        int parent = find(p[0]);
        for(int i = 0; i < p.length; i++) {
            if(find(p[i]) != parent) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(total - result);
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

        pq = new PriorityQueue<>(Comparator.comparing(o -> o.w));
        int N = Integer.parseInt(br.readLine());

        p = new int[N];
        for(int i = 0; i < p.length; i++) p[i] = i;

        for(int r = 0; r < N; r++) {
            String str = br.readLine();

            for(int c = 0; c < N; c++) {
                int w = 0;

                if(str.charAt(c) == '0') continue;
                if(str.charAt(c) >= 'A' && str.charAt(c) <= 'Z') w = getBigAlpha(str.charAt(c));
                if(str.charAt(c) >= 'a' && str.charAt(c) <= 'z') w = getSmallAlpha(str.charAt(c));
                total += w;
                pq.add(new Edge(r, c, w));
            }
        }
    }

    private static int getSmallAlpha(char c) {
        return c - 'a' + 1;
    }

    private static int getBigAlpha(char c) {
        return c - 'A' + 27;
    }

}
