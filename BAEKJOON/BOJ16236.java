import java.io.*;
import java.util.*;

public class BOJ16236 {
    static class Point implements Comparable<Point> {
        int r, c, d;
        public Point(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Point o) {
            if(this.d == o.d) {
                if(this.r == o.r) return Integer.compare(this.c, o.c);
                return Integer.compare(this.r, o.r);
            }
            return Integer.compare(this.d, o.d);
        }
    }
    static Queue<Point> q;
    static PriorityQueue<Point> pq;
    static int map[][], eatFishCnt, time, dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, -1, 1};
    static Point shark;
    static Point movePoint;

    static void solution() {
        while(true) {
            if(!canEatFish()) break;
            moveShark();
        }
        System.out.println(time);
    }

    static void moveShark() {
        movePoint = pq.poll();

        map[shark.r][shark.c] = 0;
        map[movePoint.r][movePoint.c] = 0;
        time += movePoint.d;
        eatFishCnt++;
        if(eatFishCnt == shark.d) {
            eatFishCnt = 0;
            shark.d += 1;
        }

        shark.r = movePoint.r;
        shark.c = movePoint.c;
    }

    static boolean canEatFish() {
        pq.clear();
        movePoint.d = Integer.MAX_VALUE;

        boolean canEatFish = false;
        boolean v[][] = new boolean[map.length][map[0].length];

        q.add(new Point(shark.r, shark.c, 0));
        v[shark.r][shark.c] = true;

        while(!q.isEmpty()) {
            Point current = q.poll();

            int currentSize = map[current.r][current.c];
            if(currentSize > 0 && currentSize < shark.d) {
                canEatFish = true;
                pq.add(current);
            }
            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if(!check(nr, nc) || v[nr][nc] || biggerThanShark(nr, nc)) continue;
                q.add(new Point(nr, nc, current.d + 1));
                v[nr][nc] = true;
            }
        }
        return canEatFish;
    }

    static boolean biggerThanShark(int r, int c) {
        return map[r][c] > shark.d;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        movePoint = new Point(0, 0, Integer.MAX_VALUE);
        q = new LinkedList<>();
        pq = new PriorityQueue<>();

        for(int r = 0; r < N; r++) {
            String s = br.readLine();
            for(int c = 0; c < N; c++) {
                map[r][c] = s.charAt(c * 2) - '0';
                if(map[r][c] == 9) shark = new Point(r, c, 2);
            }
        }

        solution();
    }
}