import java.io.*;
import java.util.*;

/*
2048 (Easy)
 */

public class BOJ12100 {
    static int map[][], gravityDir[], result, K;
    static int dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, -1, 1};
    static ArrayList<Integer> list;

    public static void main(String[] args) throws Exception {
        initInput();
        func(0,-1);
        System.out.println(result);
    }

    static void func(int depth, int prevDir) {
        if(depth == 10) return;
        for(int d = 0; d < 4; d++) {

            int copy[][] = new int[map.length][map[0].length];
            for(int r = 0; r < map.length; r++) System.arraycopy(map[r], 0, copy[r], 0, map[r].length);

            gravity(d);
            if(K == 0 && prevDir == d) continue;

            func(depth + 1, d);
            map = copy;
        }
    }

    static void move(int d, int mapLength, boolean check, int fix) {
        for(int i = 0; i < list.size(); i++) {
            if(i == list.size() - 1) continue;

            int currentIdx = d == -1 ? i : list.size() - 1 - i;
            int nextIdx = d == -1 ? currentIdx + 1: currentIdx - 1;

            if(list.get(currentIdx).equals(list.get(nextIdx))) {
                list.set(currentIdx, list.get(currentIdx) * 2);
                list.set(nextIdx, 0);
                K++;
                i++;
            }
        }

        int idx = 0;
        for(int i = 0; i < list.size(); i++) {
            if(list.get(d == -1 ? i : list.size() - 1 - i) == 0) continue;

                int currentIdx = d == -1 ? idx: mapLength - 1 - idx;
                if(check) {
                    map[fix][currentIdx] = list.get(d == -1 ? i : list.size() - 1 - i);
                    result = Math.max(result, map[fix][currentIdx]);
                } else {
                map[currentIdx][fix] = list.get(d == -1 ? i : list.size() - 1 - i);
                result = Math.max(result, map[currentIdx][fix]);
            }
            idx++;
        }
    }
    static void gravity(int dir) {
        K = 0;

        if(dr[dir] == 0) {
            gravityDir = dc;

            for(int r = 0; r < map.length; r++) {
                list.clear();

                for(int c = 0; c < map[r].length; c++) {
                    if(map[r][c] == 0) continue;
                    list.add(map[r][c]);
                    map[r][c] = 0;
                }

                move(gravityDir[dir], map[r].length, true, r);
            }
        }

        if(dc[dir] == 0) {
            gravityDir = dr;

            for(int c = 0; c < map[0].length; c++) {
                list.clear();

                for (int r = 0; r < map.length; r++) {
                    if (map[r][c] == 0) continue;
                    list.add(map[r][c]);
                    map[r][c] = 0;
                }

                move(gravityDir[dir], map.length, false, c);
            }
        }
    }
    static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        list = new ArrayList<>();

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }
}