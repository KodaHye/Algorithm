import java.io.*;
import java.util.*;

/*
주사위 굴리기 2
 */

public class BOJ23288 {
    static class DiceInfo {
        int r, c, dir;
        public DiceInfo(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
    static boolean v[][];
    static Queue<int []> q;
    static DiceInfo p;
    static int dice[] = new int[] {0, 1, 2, 3, 4, 5, 6};
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static int N, M, K, map[][], result;
    static void solution() {
        moveDice();
        setDiceDir(dice[6], map[p.r][p.c]);

        while(K-- > 1) {
            if(check()) move();
            else {
                rotateDice();
                move();
            }
        }
    }

    static void setDiceDir(int a, int b) {
        if(a > b) p.dir = (p.dir + 1) % 4;
        if(a < b) p.dir = (p.dir - 1 + 4) % 4;
    }

    static void move() {
        moveDice();
        setDiceDir(dice[6], map[p.r][p.c]);
    }
    static void rotateDice() {
        p.dir = (p.dir + 2) % 4;
    }

    static boolean check() {
        int nr = p.r + dr[p.dir];
        int nc = p.c + dc[p.dir];

        return check(nr, nc);
    }

    static void moveDice() {
        setDice(p.dir);

        p.r += dr[p.dir];
        p.c += dc[p.dir];

        result += map[p.r][p.c] * getArea(p.r, p.c);
    }

    static int getArea(int r, int c) {
        v = new boolean[N][M];
        q.add(new int[] {r, c});
        v[r][c] = true;
        int cnt = 1;

        while(!q.isEmpty()) {
            int current[] = q.poll();

            for(int d = 0; d < 4; d++) {
                int nr = current[0] + dr[d];
                int nc = current[1] + dc[d];

                if(!check(nr, nc) || v[nr][nc] || map[nr][nc] != map[r][c]) continue;
                q.add(new int[] {nr, nc});
                v[nr][nc] = true;
                cnt++;
            }
        }
        return cnt;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    static void setDice(int d) {
        if(d == 0) setNorth();
        if(d == 1) setEast();
        if(d == 2) setSouth();
        if(d == 3) setWest();
    }

    static void setWest() {
        int tmp = dice[1];
        dice[1] = dice[3]; dice[3] = dice[6];
        dice[6] = dice[4]; dice[4] = tmp;
    }

    static void setEast() {
        int tmp = dice[1];
        dice[1] = dice[4]; dice[4] = dice[6];
        dice[6] = dice[3]; dice[3] = tmp;
    }

    static void setNorth() {
        int tmp = dice[2];
        dice[2] = dice[1]; dice[1] = dice[5];
        dice[5] = dice[6]; dice[6] = tmp;
    }

    static void setSouth() {
        int tmp = dice[2];
        dice[2] = dice[6]; dice[6] = dice[5];
        dice[5] = dice[1]; dice[1] = tmp;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        p = new DiceInfo(0, 0, 1);
        q = new LinkedList<>();

        for(int r = 0; r < map.length; r++) {
            String s = br.readLine();
            for(int c = 0; c < map[0].length; c++) {
                map[r][c] = s.charAt(c * 2) - '0';
            }
        }

        solution();
        System.out.println(result);
    }
}
