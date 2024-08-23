import java.io.*;
import java.util.*;

/*
테트로미노
 */

public class BOJ14500 {
    static int N, M, result, map[][];
    static int dr[] = {1, 0, 0}, dc[] = {0, -1, 1};
    static boolean v[][];

    static void dfs(int depth, int r, int c, int sum) {
        if(depth == 3) {
            result = Math.max(result, sum);
            return;
        }

        for(int d = 0; d < 3; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(!check(nr, nc) || v[nr][nc]) continue;
            if(depth == 1) {
                v[nr][nc] = true;
                dfs(depth + 1, r, c, sum + map[nr][nc]);
                v[nr][nc] = false;
            }

            v[nr][nc] = true;
            dfs(depth + 1, nr, nc, sum + map[nr][nc]);
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
                dfs(0, r, c, map[r][c]);
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
            }
        }

        solution();
        System.out.println(result);
    }
}
