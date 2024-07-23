import java.io.*;
import java.util.*;

/*
별자리 만들기
 */

public class BOJ4386 {
    static class Point {
        int idx;
        double r, c;
        public Point(int idx, double r, double c) {
            this.idx = idx;
            this.r = r;
            this.c = c;
        }
    }
    static class Edge {
        Point s, e;
        double w;

        public Edge(Point s, Point e, double w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
    static int p[];
    static ArrayList<Point> points;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws Exception {
        initInput();
        mst();
    }

    public static void mst() {
        int cnt = 0;
        double result = 0;

        while(cnt < points.size() - 1) {
            Edge current = pq.poll();

            if(find(current.s.idx) == find(current.e.idx)) continue;
            union(current.s.idx, current.e.idx);
            result += current.w;
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
        return p[a] == a ? a : find(p[a]);
    }
    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        points = new ArrayList<>();
        pq = new PriorityQueue<>(Comparator.comparing(o -> o.w));
        p = new int[n];
        for(int i = 0; i < p.length; i++) p[i] = i;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            double r = Double.parseDouble(st.nextToken());
            double c = Double.parseDouble(st.nextToken());

            Point current = new Point(i, r, c);

            for(Point p: points) {
                pq.add(new Edge(current, p, getDis(current, p)));
            }

            points.add(current);
        }

    }
    public static double getDis(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.r - p2.r, 2) + Math.pow(p1.c - p2.c, 2));
    }
}
