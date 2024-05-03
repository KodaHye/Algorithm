import java.util.*;

class 지형이동_MST {
    class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    class Edge implements Comparable<Edge> {
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

    int N, M, p[], key = 1;
    int dr[] = {-1, 1, 0, 0};
    int dc[] = {0, 0, -1, 1};
    int[][] map;
    int answer;

    public int solution(int[][] land, int height) {
        N = land.length;
        M = land[0].length;

        // bfs를 통해 한 번에 갈 수 있는 곳들은 한 구역으로 묶음
        // map[][] 배열에 구역 번호 표시
        bfs(land, height);

        // MST를 통해 사다리 설치
        MST(land, map);

        return answer;
    }

    void MST(int[][] land, int[][] map) {
        p = new int[key];
        for(int i = 0; i < p.length; i++) p[i] = i;

        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();

        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[0].length; c++) {
                for(int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(!check(nr, nc) || map[nr][nc] == map[r][c]) continue;
                    pq.add(new Edge(map[r][c], map[nr][nc], Math.abs(land[nr][nc] - land[r][c])));
                }
            }
        }

        key--;
        while(key > 1) {
            Edge current = pq.poll();

            if(find(current.s) != find(current.e)) {
                union(current.s, current.e);

                answer += current.w;
                key--;
            }
        }
    }

    int find(int a) {
        return a = a == p[a] ? p[a] : find(p[a]);
    }

    void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) p[b] = a;
    }
    void bfs(int[][] land, int height) {

        map = new int[N][M];

        Queue<Point> queue = new LinkedList<Point>();


        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(map[r][c] > 0) continue;

                queue.add(new Point(r, c));
                map[r][c] = key;

                while(!queue.isEmpty()) {
                    Point current = queue.poll();

                    for(int d = 0; d < 4; d++) {
                        int nr = current.r + dr[d];
                        int nc = current.c + dc[d];

                        if(!check(nr, nc) || map[nr][nc] > 0 ||
                                Math.abs(land[nr][nc] - land[current.r][current.c]) > height) continue;

                        queue.add(new Point(nr, nc));
                        map[nr][nc] = key;
                    }
                }

                key++;
            }
        }
    }

    boolean check(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }
}