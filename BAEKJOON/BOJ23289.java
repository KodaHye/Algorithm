import java.io.*;
import java.util.*;

/*
온풍기 안녕!
 */

public class BOJ23289 {
    static class Fan {
        int r, c, d;

        public Fan(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    static int R, C, K, map[][], cnt;
    static boolean area[][], v[][];
    static ArrayList<int[]> checkList;
    static ArrayList<Fan> fans;
    static int dr[] = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int dc[] = {-1, 0, 1, 1, 1, 0, -1, -1};
    static LinkedList<int[]> queue = new LinkedList<>();

    static void solution() {
        while(true) {
            step1();
            step2();
            step3();
            cnt++;
            if(!check() || cnt > 100) break;
        }

        System.out.println(cnt > 100 ? 101: cnt);
    }

    static void step3() {
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                if(r > 0 && r < R - 1 && c > 0 && c < C - 1) continue;
                if(map[r][c] == 0) continue;
                map[r][c]--;
            }
        }
    }
    static void step2() {
        queue.clear();

        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                for(int d = 1; d < 8; d += 2) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    int idx1 = r * C + c;
                    int idx2 = nr * C + nc;

                    if(!check(nr, nc) || map[nr][nc] >= map[r][c] || area[idx1][idx2]) continue;
                    int diff = map[r][c] - map[nr][nc];
                    queue.add(new int[] {nr, nc, diff / 4});
                    queue.add(new int[] {r, c, -1 * (diff / 4)});
                }
            }
        }

        while(!queue.isEmpty()) {
            int current[] = queue.poll();
            map[current[0]][current[1]] += current[2];
        }
    }
    static void step1() {

        for(Fan fan: fans) {
            int startR = fan.r + dr[fan.d];
            int startC = fan.c + dc[fan.d];

            v = new boolean[R][C];

            if(!check(startR, startC)) continue;
            queue.add(new int[] {startR, startC, 5});

            while(!queue.isEmpty()) {
                int current[] = queue.poll();

                if(v[current[0]][current[1]]) continue;
                map[current[0]][current[1]] += current[2];
                v[current[0]][current[1]] = true;

                if(current[2] == 1) continue;
                int nr = current[0] + dr[fan.d];
                int nc = current[1] + dc[fan.d];

                if(!check(nr, nc)) continue;
                next1(current[0], current[1], current[2], fan.d);
                next2(current[0], current[1], current[2], fan.d);
                next3(current[0], current[1], current[2], fan.d);
            }
        }
    }
    static void next2(int r, int c, int value, int d) {
        int nr = r + dr[d];
        int nc = c + dc[d];

        int currentIdx = r * C + c;
        int nextIdx = nr * C + nc;

        if(area[currentIdx][nextIdx] || v[nr][nc]) return;
        queue.add(new int[] {nr, nc, value - 1});
    }

    static void next3(int r, int c, int value, int d) {
        int nDir = (d + 8 + 1) % 8;

        int nr = r + dr[nDir];
        int nc = c + dc[nDir];

        int currentIdx = r * C + c;
        int nextIdx = nr * C + nc;

        int tmpDir = (nDir + 1 + 8) % 8;
        int tmpR = r + dr[tmpDir];
        int tmpC = c + dc[tmpDir];
        int tmpIdx = tmpR * C + tmpC;

        if(!check(nr, nc) || area[currentIdx][tmpIdx] || v[nr][nc]) return;
        if(check(tmpR, tmpC) && area[tmpIdx][nextIdx]) return;

        queue.add(new int [] {nr, nc, value - 1});
    }
    static void next1(int r, int c, int value, int d) {
        int nDir = (d + 8 - 1) % 8;

        int nr = r + dr[nDir];
        int nc = c + dc[nDir];

        int currentIdx = r * C + c;
        int nextIdx = nr * C + nc;

        int tmpDir = (nDir - 1 + 8) % 8;
        int tmpR = r + dr[tmpDir];
        int tmpC = c + dc[tmpDir];
        int tmpIdx = tmpR * C + tmpC;

        if(!check(nr, nc) || area[currentIdx][tmpIdx] || v[nr][nc]) return;
        if(check(tmpR, tmpC) && area[tmpIdx][nextIdx]) return;

        queue.add(new int[] {nr, nc, value - 1});
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    static boolean check() {
        for(int point[]: checkList) {
            if(map[point[0]][point[1]] < K) return true;
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fans = new ArrayList<>();
        checkList = new ArrayList<>();

        area = new boolean[R * C][R * C];
        map = new int[R][C];

        for(int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());

            for(int c = 0; c < C; c++) {
                int num = Integer.parseInt(st.nextToken());

                if(num == 5) checkList.add(new int[] {r, c});
                if(num == 0 || num == 5) continue;

                int dir = 0;
                if(num == 1) dir = 3;
                if(num == 2) dir = 7;
                if(num == 3) dir = 1;
                if(num == 4) dir = 5;

                fans.add(new Fan(r, c, dir));
            }
        }

        int W = Integer.parseInt(br.readLine());

        for(int w = 0; w < W; w++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            int nr = 0, nc = 0;
            if(t == 0) {
                nr = r - 1;
                nc = c;
            }
            if(t == 1) {

                nr = r;
                nc = c + 1;
            }

            if(!check(nr, nc)) continue;

            int idx1 = r * C + c;
            int idx2 = nr * C + nc;

            area[idx1][idx2] = true;
            area[idx2][idx1] = true;
        }
    }
}
