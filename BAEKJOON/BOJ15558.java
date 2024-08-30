import java.io.*;
import java.util.*;

/*
점프 게임
 */

public class BOJ15558 {
    static class Point {
        int r, c;
        boolean f;

        public Point(int r, int c, boolean f) {
            this.r = r;
            this.c = c;
            this.f = f;
        }
    }
    static int N, k, dr[] = {0, 0, -1, 1}, dc[] = {1, -1, 0, 0};
    static char map[][];
    static Queue<Point> q;
    static boolean v[][];

    static boolean check(int r, int c) {
        return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
    }

    static boolean solution() {
        q = new LinkedList<>();
        q.add(new Point(0, 0, false));
        q.add(new Point(0, 0, true));
        q.add(new Point(1, 0, true));

        v[0][0] = true;
        v[1][0] = true;

        while(!q.isEmpty()) {
            Point current = q.poll();

            for(int d = 0; d < 4; d++) {
                int nr = current.r;
                int nc = current.c;

                if(!current.f && (d == 2 || d == 3)) {
                    nr = (current.r + dr[d] + 2) % 2;
                    nc = current.c + k;
                } else {
                    nr += dr[d];
                    nc += dc[d];
                }

                if(!current.f && nc >= map[0].length) return true;
                if(!check(nr, nc) || v[nr][nc] || (!current.f && map[nr][nc] == '0')) continue;
                v[nr][nc] = true;
                q.add(new Point(nr, nc, current.f));
            }
        }

        return false;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new char[2][N];
        v = new boolean[2][N];

        for(int r = 0; r < map.length; r++) {
            String s = br.readLine();
            map[r] = s.toCharArray();
        }

        System.out.println(solution() ? 1: 0);
    }
}
