import java.io.*;
import java.util.*;

/*
청소년 상어
- 물고기가 먹혔을 때, 굳이 map을 0으로 변경하지 않아도 됨
 */

public class BOJ19236 {
    static class Fish {
        int r, c, dir;
        boolean isAlive = true;
        Fish(int r, int c, int dir, boolean isAlive) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }

    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
    static TreeMap<Integer, Fish> fishes;
    static int map[][];
    static int answer;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        fishes = new TreeMap<>();
        map = new int[4][4];

        for(int r = 0; r < 4; r++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int c = 0; c < 4; c++){
                int idx = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                fishes.put(idx, new Fish(r, c, dir - 1, true));
                map[r][c] = idx;
            }
        }

        dfs(0, 0, fishes.get(map[0][0]).dir, map[0][0]);

        System.out.println(answer);
    }

    public static void dfs(int r, int c, int d, int sum){
        answer = Math.max(answer, sum);
        fishes.get(map[r][c]).isAlive = false;

        for(int i: fishes.keySet()) {
            if(!fishes.get(i).isAlive) continue;
            moveFish(r, c, i, fishes, map);
        }

        for(int i = 1; i <=3; i++){
            int nr = r + dr[d] * i;
            int nc = c + dc[d] * i;

            if(!check(nr, nc) || !fishes.get(map[nr][nc]).isAlive) continue;

            TreeMap<Integer, Fish> tmpFish = new TreeMap<>();
            for(int idx: fishes.keySet()) {
                tmpFish.put(idx, new Fish(fishes.get(idx).r, fishes.get(idx).c, fishes.get(idx).dir, fishes.get(idx).isAlive));
            }
            int[][] tmpMap = new int[4][4];
            for(int t=0; t<4; t++) tmpMap[t] = map[t].clone();

            dfs(nr, nc, fishes.get(map[nr][nc]).dir, sum + map[nr][nc]);

            fishes = tmpFish;
            map = tmpMap;
        }

        fishes.get(map[r][c]).isAlive = true;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < 4 && c >= 0 && c < 4;
    }

    static void moveFish(int r, int c, int i, TreeMap<Integer, Fish> fishes, int map[][]) {
        Fish fish = fishes.get(i);

        for(int d = 0; d < 9; d++) {
            int nr = fish.r + dr[(fish.dir + d) % 8];
            int nc = fish.c + dc[(fish.dir + d) % 8];

            if(!check(nr, nc) || (nr==r && nc==c)) continue;

            fish.dir = (fish.dir + d) % 8;
            int targetIdx = map[nr][nc];
            Fish target = fishes.get(map[nr][nc]);

            map[fish.r][fish.c] = targetIdx;
            map[nr][nc] = i;

            target.r = fish.r; target.c = fish.c;
            fish.r = nr; fish.c = nc;
            break;
        }
    }
}