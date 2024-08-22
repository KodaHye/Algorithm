import java.io.*;
import java.util.*;

/*
음식물 피하기
 */

public class BOJ1743 {
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean map[][] = new boolean[N][M];

        for(int t = 0; t < K; t++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            map[r][c] = true;
        }

        Queue<int []> q = new LinkedList<>();

        int result = 0;
        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[0].length; c++) {
                if(!map[r][c]) continue;
                int cnt = 1;
                q.add(new int[] {r, c});
                map[r][c] = false;

                while(!q.isEmpty()) {
                    int current[] = q.poll();

                    for(int d = 0; d < 4; d++) {
                        int nr = current[0] + dr[d];
                        int nc = current[1] + dc[d];

                        if(nr < 0 || nr >= N || nc < 0 || nc >= M || !map[nr][nc]) continue;
                        q.add(new int[] {nr, nc});
                        map[nr][nc] = false;
                        cnt++;
                    }
                }
                result = Math.max(result, cnt);
            }
        }

        System.out.println(result);
    }
}
