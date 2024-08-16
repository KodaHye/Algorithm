import java.io.*;
import java.util.*;

/*
마법사 상어와 파이어볼
 */

public class BOJ20056 {
    static class FireBall {
        int r, c, m, s, d;

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    static int dr[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
    static HashMap<Integer, FireBall> fireballs;
    static HashSet<Integer> map[][];
    static ArrayList<Integer> removeList;
    static int fireBallNum, N, M, K;

    static void solution() {

        while(K-- > 0) {
            moveFireBalls();
            checkMoreThanOne();
        }
        System.out.println(sum());
    }

    static int sum() {
        int result = 0;
        for(FireBall f: fireballs.values()) result += f.m;
        return result;
    }

    static void checkMoreThanOne() {
        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[0].length; c++) {
                if(map[r][c].size() < 2) continue;

                int m = 0, s = 0;
                int cnt = map[r][c].size();

                int tmp = fireballs.get(map[r][c].iterator().next()).d % 2;
                boolean checkDir = true;

                removeList.clear();
                for(int idx: map[r][c]) {
                    m += fireballs.get(idx).m;
                    s += fireballs.get(idx).s;

                    if(fireballs.get(idx).d % 2 != tmp) checkDir = false;
                    fireballs.remove(idx);
                    removeList.add(idx);
                }
                map[r][c].removeAll(removeList);

                m /= 5;
                if(m == 0) continue;

                int dir = checkDir ? 0: 1;
                for(int i = dir; i < 8; i += 2) {
                    fireballs.put(fireBallNum, new FireBall(r, c, m, s / cnt, i));
                    map[r][c].add(fireBallNum);
                    fireBallNum++;
                }
            }
        }
    }

    static void moveFireBalls() {
        fireballs.entrySet().forEach(f -> {
            int idx = f.getKey();
            int r = f.getValue().r;
            int c = f.getValue().c;

            map[r][c].remove(idx);

            int d = f.getValue().d;
            int s = f.getValue().s;

            r = (r + dr[d] * s) % N;
            c = (c + dc[d] * s) % N;

            r = r < 0 ? r + N : r;
            c = c < 0 ? c + N : c;

            f.getValue().r = r;
            f.getValue().c = c;

            map[r][c].add(idx);
        });
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new HashSet[N][N];
        removeList = new ArrayList<>();
        fireballs = new HashMap<>();
        for(int r = 0; r < map.length; r++)
            for(int c = 0; c < map[0].length; c++) map[r][c] = new HashSet<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fireballs.put(fireBallNum, new FireBall(r, c, m, s, d));
            map[r][c].add(fireBallNum);
            fireBallNum++;
        }

        solution();
    }
}
