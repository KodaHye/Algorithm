import java.io.*;
import java.util.*;

/*
녹색 옷 입은 애가 젤다지?
 */

public class BOJ4485 {
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static class Node implements Comparable<Node> {
        Point p;
        int w;
        public Node(Point p, int w) {
            this.p = p;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }
    static int N, map[][], dis[][];
    static PriorityQueue<Node> pq;
    static int dr[] = {0, 1, 0, -1};
    static int dc[] = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int test_case = 1;

        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            map = new int[N][N];
            dis = new int[N][N];

            for(int r = 0; r < dis.length; r++) Arrays.fill(dis[r], Integer.MAX_VALUE);

            for(int r = 0; r < map.length; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < map[0].length; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("Problem " + test_case + ": " + bfs(0, 0) + "\n");
            test_case++;
        }
        System.out.print(sb);
    }

    static int bfs(int r, int c) {
        pq = new PriorityQueue<>();
        pq.add(new Node(new Point(0, 0), 0));
        dis[0][0] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            if(current.p.r == N - 1 && current.p.c == N - 1) break;
            for(int d = 0; d < 4; d++) {
                int nr = current.p.r + dr[d];
                int nc = current.p.c + dc[d];

                if(!check(nr, nc)) continue;
                if(dis[nr][nc] > map[nr][nc] + dis[current.p.r][current.p.c]) {
                    dis[nr][nc] = map[nr][nc] + dis[current.p.r][current.p.c];
                    pq.add(new Node(new Point(nr, nc), dis[nr][nc]));
                }
            }
        }
        return dis[N - 1][N - 1] + map[0][0];
    }
    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
