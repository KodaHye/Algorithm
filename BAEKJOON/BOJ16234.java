import java.io.*;
import java.util.*;

/*
인구 이동
 */

public class BOJ16234 {
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, L, R, map[][];
    static boolean v[][];
    static int dir[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static Queue<Point> q, tmp;

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void solution() {
        int result = 0;
        while(true) {
            if(!initV()) break;
            result++;
        }

        System.out.print(result);
    }

    static boolean initV() {
        v = new boolean[N][N];

        boolean flag = false;

        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[0].length; c++) {
                if(v[r][c]) continue;

                q.add(new Point(r, c));
                tmp.add(new Point(r, c));
                v[r][c] = true;

                int sum = map[r][c];

                while(!q.isEmpty()) {
                    Point current = q.poll();

                    for(int d = 0; d < 4; d++) {
                        int nr = current.r + dir[d][0];
                        int nc = current.c + dir[d][1];

                        if(!check(nr, nc) || !isInRange(current.r, current.c, nr, nc) || v[nr][nc]) continue;
                        flag = true;
                        q.add(new Point(nr, nc));
                        tmp.add(new Point(nr, nc));
                        v[nr][nc] = true;
                        sum += map[nr][nc];
                    }
                }

                sum /= tmp.size();
                while(!tmp.isEmpty()) {
                    Point current = tmp.poll();
                    map[current.r][current.c] = sum;
                }
            }
        }

        return flag;
    }
    static boolean isInRange(int r, int c, int nr, int nc) {
        return Math.abs(map[nr][nc] - map[r][c]) >= L && Math.abs(map[nr][nc] - map[r][c]) <= R;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
    static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        q = new LinkedList<>();
        tmp = new LinkedList<>();
        map = new int[N][N];
        for(int r = 0; r < map.length; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < map[0].length; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
