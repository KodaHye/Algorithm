import java.io.*;
import java.util.*;

/*
행성 터널
find할 때, p[a] = p[a] == a ? a : find(p[a])
메모이제이션 잊지말자!
 */

public class BOJ2887 {

    static int N, parents[];
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    static class Point{
        int idx, x, y, z;

        public Point(int idx, int x, int y, int z) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

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

    static int find(int a) {
        return parents[a] = parents[a] == a ? a : find(parents[a]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) parents[b] = a;
    }

    public static void main(String[] args) throws Exception{
        initInput();
        mst();
    }
    static void initInput() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        Point[] points = new Point[N];
        parents = new int[N];

        for(int i=0; i<N; i++) {
            parents[i] = i;
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            points[i] = new Point(i,x,y,z);
        }

        Arrays.sort(points, Comparator.comparingInt(p -> p.x));
        for(int i=1; i<N; i++) {
            int weight = points[i].x - points[i-1].x;
            pq.add(new Edge(points[i].idx ,points[i-1].idx, weight));
        }

        Arrays.sort(points, Comparator.comparingInt(p -> p.y));
        for(int i=1; i<N; i++) {
            int weight = points[i].y - points[i-1].y;
            pq.add(new Edge(points[i].idx,points[i-1].idx, weight));
        }

        Arrays.sort(points, Comparator.comparingInt(p -> p.z));
        for(int i=1; i<N; i++) {
            int weight = points[i].z - points[i-1].z;
            pq.add(new Edge(points[i].idx,points[i-1].idx, weight));
        }
    }

    private static void mst() {
        int cnt=0, result = 0;

        while(cnt < parents.length - 1) {
            Edge edge = pq.poll();
            if(find(edge.s) == find(edge.e)) continue;
            union(edge.s, edge.e);
            result += edge.w;
            cnt++;
        }

        System.out.println(result);
    }
}
