import java.io.*;
import java.util.*;

/*
상어 중학교
블럭이 사라지면서 무지개도 사라질 수 있다는거 주의하기!
배열 돌리기 할 때, copy 하지 않아도 됨. 배열의 주소만 바꿔주면 됨
 */

public class BOJ21609 {
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Info {
        Point p;
        int totalCnt, rainbowCnt;
        public Info(Point p, int totalCnt, int rainbowCnt) {
            this.p = p;
            this.totalCnt = totalCnt;
            this.rainbowCnt = rainbowCnt;
        }
    }
    static final int INF = Integer.MAX_VALUE;
    static boolean[][] checkBfs;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    static Deque<Point> q;
    static Deque<Integer> gravityQueue;
    static int N, M, score, map[][], copy[][];

    static void solution() {
        Info i;

        while((i = bfs()) != null) {
            removeBlockGroup(i.p.r, i.p.c, map[i.p.r][i.p.c]);
            updateScore(i.totalCnt);
            gravity();
            rotate();
            gravity();
        }

        System.out.println(score);
    }

    static void gravity() {

        for(int c = 0; c < N; c++) {
            int row = N;

            for(int r = N - 1; r >= 0; r--) {
                int currentR = row - 1;

                if(r == N - 1 && map[r][c] == -1) row -= 1;
                if(map[r][c] != INF && map[r][c] != -1) {
                    gravityQueue.add(map[r][c]);
                    map[r][c] = INF;
                }

                if(r == 0 || map[r - 1][c] == -1) {
                    row = r - 1;

                    while(!gravityQueue.isEmpty()) {
                        map[currentR--][c] = gravityQueue.poll();
                    }
                }
            }
        }
    }

    static void rotate() {
        copy = new int[N][N];

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                copy[N - 1 - c][r] = map[r][c];
            }
        }
        map = copy;
    }

    static void removeBlockGroup(int r, int c, int k) {
        map[r][c] = INF;

        for(int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(!check(nr, nc) || (map[nr][nc] != 0 && map[nr][nc] != k)) continue;
            removeBlockGroup(nr, nc, k);
        }
    }

    static Info bfs() {
        checkBfs = new boolean[N][N];

        int pr = 0, pc = 0;
        int groupCnt = 0, rainbowCnt = 0;

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(map[r][c] < 1 || map[r][c] == INF || checkBfs[r][c]) continue;
                int cnt = 1, rainbowTmp = 0;

                setRainbow();
                checkBfs[r][c] = true;

                q.add(new Point(r, c));
                while(!q.isEmpty()) {
                    Point current = q.poll();

                    for(int d = 0; d < 4; d++) {
                        int nr = current.r + dr[d];
                        int nc = current.c + dc[d];

                        if(!check(nr, nc) || map[nr][nc] == -1 || checkBfs[nr][nc] || map[nr][nc] == INF) continue;
                        if(map[nr][nc] > 0 && map[nr][nc] <= M && map[nr][nc] != map[r][c]) continue;

                        if(map[nr][nc] == 0) rainbowTmp++;
                        q.add(new Point(nr, nc));
                        checkBfs[nr][nc] = true;
                        cnt++;
                    }
                }

                if(cnt < 2) continue;

                if(groupCnt < cnt || (groupCnt == cnt && (rainbowCnt <= rainbowTmp))) {
                    pr = r;
                    pc = c;
                    groupCnt = cnt;
                    rainbowCnt = rainbowTmp;
                }
            }
        }

        return groupCnt == 0 ? null : new Info(new Point(pr, pc), groupCnt, rainbowCnt);
    }

    static void setRainbow() {
        for(int r = 0; r < N; r++) for(int c = 0; c < N; c++)
            if(map[r][c] == 0) checkBfs[r][c] = false;
    }

    static void updateScore(int cnt) {
        score += cnt * cnt;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        q = new ArrayDeque<>();
        gravityQueue = new ArrayDeque<>();

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        solution();
    }
}
