import java.util.*;

class 지형이동_priorityQueue {
    class Point implements Comparable<Point> {
        int r, c, cost;

        public Point(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point p) {
            return Integer.compare(this.cost, p.cost);
        }
    }
    int dr[] = {-1, 1, 0, 0};
    int dc[] = {0, 0, -1, 1};

    public int solution(int[][] land, int height) {

        PriorityQueue<Point> queue = new PriorityQueue<Point>();
        boolean v[][] = new boolean[land.length][land[0].length];

        int answer = 0;

        queue.add(new Point(0, 0, 0));

        while(!queue.isEmpty()) {
            Point current = queue.poll();
            if(v[current.r][current.c]) continue;
            v[current.r][current.c] = true;

            answer += current.cost;

            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if(!check(nr, nc, land.length, land[0].length)) continue;

                int cost = 0;
                if(Math.abs(land[nr][nc] - land[current.r][current.c]) > height)
                    cost = Math.abs(land[nr][nc] - land[current.r][current.c]);

                queue.add(new Point(nr, nc, cost));
            }
        }
        return answer;
    }
    boolean check(int nr, int nc, int r, int c) {
        return nr >= 0 && nr < r && nc >= 0 && nc < c;
    }
}