import java.io.*;
import java.util.*;

/*
캐슬 디펜스
 */

public class BOJ17135 {
    static boolean existEnemy[][];
    static int N, M, D, sel[], cnt[][];
    static int enemyCnt, result;
    static void solution() {
        func(0, 0);
        System.out.println(result);
    }


    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    static int attack(int current, int i, int d) {
        for(int c = current - d; c < current + d + 1; c++) {
            int r = i - (d - Math.abs(c - current));

            if(!check(r, c) || r >= i || !existEnemy[r][c]) continue;
            if(cnt[r][c] == 0) {
                cnt[r][c] = i;
                return 1;
            } else if(cnt[r][c] == i) return 0;
        }

        return -1;
    }
    static int attackEnemy() {
        int total = 0;

        cnt = new int[N + 1][M];
        for(int i = N; i > 0; i--) {
            for(int current: sel) {
                for(int d = 1; d < D + 1; d++) {

                    int killCnt = attack(current, i, d);
                    if(killCnt < 0) continue;

                    total += killCnt;
                    break;
                }
            }
        }

        return total;
    }

    static void func(int d, int idx) {
        if(d == 3) {
            result = Math.max(attackEnemy(), result);
            return;
        }

        for(int i = idx; i < M; i++) {
            sel[d] = i;
            func(d + 1, i + 1);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        existEnemy = new boolean[N + 1][M];
        sel = new int[3];

        for(int r = 0; r < N; r++) {
            String s = br.readLine();
            for(int c = 0; c < M; c++) {
                int n = s.charAt(c * 2) - '0';
                if(n == 0) continue;
                existEnemy[r][c] = true;
                enemyCnt++;
            }
        }

        solution();
    }
}
