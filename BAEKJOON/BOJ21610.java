import java.io.*;
import java.util.*;

/*
마법사 상어와 비바라기
 */

public class BOJ21610 {
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static Queue<Point> q;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, map[][];
    static ArrayList<Point> clouds;
    static boolean v[][];
    static int dr[] = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int dc[] = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void solution() throws Exception {
        for(int i = 1; i < 3; i++) {
            for(int j = 0; j < 2; j++) clouds.add(new Point(N - i, j));
        }

        for(int tc = 0; tc < M; tc++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            v = new boolean[N][N];

            for(Point c: clouds) {
                c.r = (c.r + dr[d] * s) % N;
                c.c = (c.c + dc[d] * s) % N;

                c.r = c.r < 0 ? c.r + N: c.r;
                c.c = c.c < 0 ? c.c + N: c.c;

                map[c.r][c.c]++;

                q.add(new Point(c.r, c.c));
                v[c.r][c.c] = true;
            }

            while(!q.isEmpty()) {
                Point current = q.poll();
                int cnt = 0;
                for(int dir = 2; dir < dr.length; dir += 2) {
                    int nr = current.r + dr[dir];
                    int nc = current.c + dc[dir];

                    if(!check(nr, nc) || map[nr][nc] == 0) continue;
                    cnt++;
                }

                map[current.r][current.c] += cnt;
            }

            clouds.clear();

            for(int r = 0; r < N; r++) {
                for(int c = 0; c < N; c++) {
                    if(map[r][c] < 2 || v[r][c]) continue;
                    clouds.add(new Point(r, c));
                    map[r][c] -= 2;
                }
            }
        }

        int result = 0;
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(map[r][c] == 0) continue;
                result += map[r][c];
            }
        }
        System.out.println(result);
    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static void initInput() throws Exception {
        q = new LinkedList<>();
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        clouds = new ArrayList<>();
        map = new int[N][N];
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }
}