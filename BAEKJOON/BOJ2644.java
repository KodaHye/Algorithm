import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
촌수 계산
 */
public class BOJ2644 {
    static class Point {
        int point, depth;

        public Point(int point, int depth) {
            this.point = point;
            this.depth = depth;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, result;
    static ArrayList<Integer> adj[];
    static boolean v[];

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());

        adj = new ArrayList[n + 1];
        v = new boolean[n + 1];

        result = -1;

        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            adj[s].add(e);
            adj[e].add(s);
        }


        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(start, 0));
        v[start] = true;

        while(!queue.isEmpty()) {
            Point current = queue.poll();

            if(current.point == end) result = current.depth;

            for(Integer next: adj[current.point]) {
                if(v[next]) continue;
                queue.add(new Point(next, current.depth + 1));
                v[next] = true;
            }
        }

        System.out.println(result);
    }
}
