import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
별자리 만들기
 */
public class BOJ4386 {
    static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int s, e;
        double weight;

        public Edge(int s, int e, double weight) {
            this.s = s;
            this.e = e;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, p[];
    static ArrayList<Point> points;
    static PriorityQueue<Edge> queue = new PriorityQueue<>();
    static boolean v[];
    static double result;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        v = new boolean[N + 1];
        p = new  int[N + 1];
        points = new ArrayList<>();

        for(int i = 0; i < p.length; i++) p[i] = i;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            points.add(new Point(x, y));
        }

        for(int i = 0; i < points.size(); i++) {
            for(int j = 0; j < points.size(); j++) {
                if(i == j) continue;
                queue.add(new Edge(i, j, dist(points.get(i), points.get(j))));
            }
        }

        while(N-- > 0) {
            Edge current = queue.poll();

            if(find(current.s) != find(current.e)) {
                union(current.s, current.e);
                result += current.weight;
                System.out.println(">>>>>>>>>>>" + current.s + " " + current.e);
            }
        }

        System.out.printf("%.2f", result);
    }

    private static int find(int a) {
        return p[a] = p[a] == a ? a : find(p[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) p[b] = a;
    }

    private static double dist(Point point1, Point point2) {

        return Math.sqrt(Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y - point2.y, 2));
    }
}
