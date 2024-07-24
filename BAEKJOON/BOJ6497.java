import java.io.*;
import java.util.*;

/*
전력난
 */

public class BOJ6497 {
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
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int p[];

    public static void main(String[] args) throws Exception {

        while(true) {
            String s = br.readLine();
            if(s.equals("0 0")) break;

            st = new StringTokenizer(s);
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            p = new int[m];
            for(int i = 0; i < p.length; i++) p[i] = i;

            pq.clear();

            int sum = 0;

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                sum += z;
                pq.add(new Edge(x, y, z));
            }

            sb.append((sum - mst()) + "\n");
        }
        System.out.print(sb);
    }
    static int find(int a) {
        return p[a] = a == p[a] ? a : find(p[a]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) p[b] = a;
    }

    public static int mst() {

        int cnt = 0;
        int result = 0;

        while(cnt < p.length - 1) {
            Edge e = pq.poll();
            if(find(e.s) == find(e.e)) continue;
            union(e.s, e.e);
            result += e.w;
            cnt++;
        }

        return result;
    }
}
