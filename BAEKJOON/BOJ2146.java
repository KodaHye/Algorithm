import java.io.*;
import java.util.*;

/*
다리 만들기
 */

public class BOJ2146 {
    static int N, result, map[][], dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, -1, 1};
    static Queue<int[]> q;
    static boolean v[][];

    static boolean check(int r, int c) {
        return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
    }
    static void bfs(int r, int c, int idx) {
        q.add(new int[] {r, c});
        map[r][c] = idx;

        while(!q.isEmpty()) {
            int current[] = q.poll();
            for(int d = 0; d < 4; d++) {
                int nr = current[0] + dr[d];
                int nc = current[1] + dc[d];
                if(!check(nr, nc) || map[nr][nc] != 1) continue;
                map[nr][nc] = idx;
                q.add(new int[] {nr, nc});
            }
        }
    }

    static void getDis(int r, int c) {
        v = new boolean[N][N];
        v[r][c] = true;

        q.clear();
        q.add(new int[] {r, c, 0});
        while(!q.isEmpty()) {
            int current[] = q.poll();

            for(int d = 0; d < 4; d++) {
                int nr = current[0] + dr[d];
                int nc = current[1] + dc[d];

                if(!check(nr, nc) || v[nr][nc]) continue;
                if(map[nr][nc] == 0) {
                    q.add(new int[] {nr, nc, current[2] + 1});
                    v[nr][nc] = true;
                    continue;
                }
                if(map[nr][nc] != 0 && map[nr][nc] != map[r][c]) {
                    result = Math.min(result, current[2]);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        q = new LinkedList<>();
        result = Integer.MAX_VALUE;

        for(int r = 0; r < N; r++) {
            String s = br.readLine();
            for(int c = 0; c < N; c++) {
                map[r][c] = s.charAt(c * 2) - '0';
            }
        }

        int idx = 2;
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(map[r][c] != 1) continue;
                bfs(r, c, idx++);
            }
        }

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(map[r][c] == 0) continue;
                boolean flag = false;
                for(int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(!check(nr, nc)) continue;
                    if(map[nr][nc] == 0) flag = true;
                }

                if(flag) getDis(r, c);
            }
        }

        System.out.println(result);
    }
}
