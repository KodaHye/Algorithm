import java.io.*;
import java.util.*;

/*
다리 만들기 2
 */

public class BOJ17472 {
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
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
    static int N, M, groupIdx, map[][], group[][], p[];
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        initInput();
        bfs();
        getDis();

        System.out.println(mst());
    }

    private static int mst() {
        int dis = 0;
        p = new int[groupIdx + 1];
        for(int i = 0; i < p.length; i++) p[i] = i;

        while(groupIdx > 1) {
            Edge current = pq.poll();

            if(current == null) return -1;
            if(find(current.s) != find(current.e)) {
                union(current.s, current.e);
                dis += current.w;
                groupIdx--;
            }
        }

        int parent = p[1];
        for(int i = 2; i < p.length; i++) {
            if(parent != find(p[i])) return -1;
        }
        return dis;
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a < b) p[b] = a;
        else p[a] = b;
    }

    private static int find(int a) {
        return a == p[a] ? p[a] : find(p[a]);
    }

    private static void getDis() {

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(group[r][c] == 0 || !checkAround(r, c)) continue;

                for(int d = 0; d < 4; d++) {
                    int nr = r;
                    int nc = c;
                    int dis = -1;

                    while(true) {
                        nr += dr[d];
                        nc += dc[d];
                        dis++;

                        if(!check(nr, nc) || group[nr][nc] == group[r][c]) break;
                        if(group[nr][nc] != group[r][c] && group[nr][nc] != 0) {
                            if(dis < 2) break;
                            pq.add(new Edge(group[nr][nc], group[r][c], dis));
                            break;
                        }
                    }
                }
            }
        }
    }

    private static boolean checkAround(int r, int c) {
        for(int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(!check(nr, nc)) continue;
            if(map[nr][nc] != map[r][c]) return true;
        }
        return false;
    }

    private static void bfs() {
        Queue<Point> queue = new LinkedList<>();

        group = new int[N][M];
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(group[r][c] != 0 || map[r][c] == 0) continue;
                groupIdx++;
                queue.add(new Point(r, c));
                group[r][c] = groupIdx;

                while(!queue.isEmpty()) {
                    Point current = queue.poll();

                    for(int d = 0; d < 4; d++) {
                        int nr = current.r + dr[d];
                        int nc = current.c + dc[d];

                        if(!check(nr, nc) || group[nr][nc] != 0 || map[nr][nc] == 0) continue;
                        queue.add(new Point(nr, nc));
                        group[nr][nc] = groupIdx;
                    }
                }
            }
        }
    }

    private static boolean check(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }

    static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int r = 0; r < N; r++) {
            String s = br.readLine();
            for(int c = 0; c < M; c++) {
                map[r][c] = s.charAt(c * 2) - '0';
            }
        }
    }
}