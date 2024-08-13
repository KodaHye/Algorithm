import java.io.*;
import java.util.*;

/*
지구 온난화
 */

public class BOJ5212 {
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static char map[][];
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        map = new char[R + 2][C + 2];

        for(int r = 0; r < map.length; r++) {
            String s = null;
            if(r != 0 && r != map.length - 1) s = br.readLine();
            for(int c = 0; c < map[0].length; c++) {
                if(r == 0 || c == 0 || r == map.length - 1 || c == map[0].length - 1) {
                    map[r][c] = '.';
                    continue;
                }
                map[r][c] = s.charAt(c - 1);
            }
        }

        Queue<Point> q = new LinkedList<>();
        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[0].length; c++) {
                if(map[r][c] == '.') continue;

                int cnt = 0;
                for(int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(!check(nr, nc) || map[nr][nc] == 'X') continue;
                    cnt++;
                }

                if(cnt >= 3) q.add(new Point(r, c));
            }
        }

        while(!q.isEmpty()) {
            Point current = q.poll();
            map[current.r][current.c] = '.';
        }

        Point start = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Point end = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);

        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[0].length; c++) {
                if(map[r][c] == '.') continue;
                start.r = Math.min(start.r, r);
                start.c = Math.min(start.c, c);
                end.r = Math.max(end.r, r);
                end.c = Math.max(end.c, c);
            }
        }

        for(int r = start.r; r < end.r + 1; r++) {
            for(int c = start.c; c < end.c + 1; c++) {
                sb.append(map[r][c]);
            }

            sb.append("\n");
        }
        System.out.print(sb);
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < map.length && c>= 0 && c < map[0].length;
    }
}
