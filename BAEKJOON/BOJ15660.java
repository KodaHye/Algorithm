import java.io.*;
import java.util.*;

/*
테트로미노
 */

public class BOJ15660 {
    static int N, M, result, max, map[][];
    static int dr[] = {1, 0, 0}, dc[] = {0, -1, 1};
    static boolean v[][];

    static void dfs(int depth, int sr, int sc, int r, int c, int sum, boolean check) {
        if(sum + (7 - depth) * max <= result) return;
        if(depth == 6) {
            result = Math.max(result, sum);
            return;
        }
        if(!check && depth == 3) {

            for(int rr = sr; rr < N; rr++) {
                for(int cc = sc; cc < M; cc++) {
                    if(v[rr][cc]) continue;
                    v[rr][cc] = true;
                    dfs(depth, rr, cc, rr, cc, sum + map[rr][cc], true);
                    v[rr][cc] = false;
                }
            }
            return;
        }


        for(int d = 0; d < 3; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(!check(nr, nc) || v[nr][nc]) continue;
            if(depth == 1 || depth == 4) {
                v[nr][nc] = true;
                dfs(depth + 1, sr, sc, r, c, sum + map[nr][nc], check);
                v[nr][nc] = false;
            }

            v[nr][nc] = true;
            dfs(depth + 1, sr, sc, nr, nc, sum + map[nr][nc], check);
            v[nr][nc] = false;
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
    static void solution() {
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                v[r][c] = true;
                dfs(0, r, c, r, c, map[r][c], false);
                v[r][c] = false;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        v = new boolean[N][M];

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[r][c]);
            }
        }

        solution();
        System.out.println(result);
    }
}