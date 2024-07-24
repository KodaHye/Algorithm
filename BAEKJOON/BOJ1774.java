import java.io.*;
import java.util.*;

/*
우주신과의 교감
 */

public class BOJ1774 {
    static class Point {
        int idx;
        long x, y;
        public Point(int idx, long x, long y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }
    static class Edge {
        int s, e;
        double w;

        public Edge(int s, int e, double w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
    static int N, M;
    static PriorityQueue<Edge> pq;
    static ArrayList<Point> points;
    static int p[];
    public static void main(String[] args) throws Exception {
        initInput();
        mst();
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) p[b] = a;
    }

    public static int find(int a) {
        return p[a] = p[a] == a ? a : find(p[a]);
    }
    public static void mst() {
        int cnt = 0;
        double result = 0;

        while(!pq.isEmpty() && cnt < p.length - 2) {
            Edge e = pq.poll();
            if(find(e.s) == find(e.e)) continue;
            union(e.s, e.e);
            result += e.w;
            cnt++;
        }

        System.out.println(String.format("%.2f", Math.round(result * 100) / 100.0));
    }
    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        pq = new PriorityQueue<>(Comparator.comparing(o -> o.w));
        points = new ArrayList<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        p = new int[N + 1];

        for(int i = 0; i < p.length; i++) p[i] = i;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long X = Integer.parseInt(st.nextToken());
            long Y = Integer.parseInt(st.nextToken());

            Point current = new Point(i + 1, X, Y);

            for(Point p: points) {
                double dis = getDis(current, p);
                pq.add(new Edge(current.idx, p.idx, dis));
            }

            points.add(current);
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }
    }

    public static double getDis(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}
