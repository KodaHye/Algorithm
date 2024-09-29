import java.io.*;
import java.util.*;

/*
경사로
 */

public class BOJ14890 {
    static int[][] map;
    static boolean[] checkVisit;
    static int N, L, result;

    static void solution() {
        countRoute();
        rotate();
        countRoute();
        System.out.println(result);
    }

    static void countRoute() {
        for(int r = 0; r < N; r++) {
            boolean check = false;
            checkVisit = new boolean[N];

            for(int c = 0; c < N; c++) {
                if(c == N - 1) {
                    check = true;
                    break;
                }
                if(map[r][c] == map[r][c + 1]) continue;
                if(map[r][c] - 1 == map[r][c + 1]) {
                    if(setSlide(r, c, 1)) {
                        c += L - 1;
                        continue;
                    }
                    else break;
                }
                if(map[r][c] + 1 == map[r][c + 1]) {
                    if(setSlide(r, c + 1, -1)) continue;
                    else break;
                }
                break;
            }

            if(check) result++;
        }
    }

    static boolean setSlide(int r, int c, int dir) {
        int current = map[r][c];
        for(int i = 1; i < L + 1; i++) {
            int next = c + i * dir;
            if(next < 0 || next >= N) return false;
            if(dir == -1) {
                if(map[r][next] != current + dir) return false;
                if(i == L && next + dir > 0 && map[r][c] + dir < map[r][next + dir]) return false;
            } else {
                if(map[r][next] + dir != current) return false;
                if(i == L && next + dir < N && map[r][c] < map[r][next + dir] + dir) return false;
            }

            if(checkVisit[next]) return false;
            checkVisit[next] = true;
        }
        return true;
    }

    static void rotate() {
        int[][] tmp = new int[N][N];
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                tmp[c][r] = map[r][c];
            }
        }

        map = tmp;
    }

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }
}