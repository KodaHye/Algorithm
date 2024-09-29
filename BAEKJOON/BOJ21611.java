import java.io.*;
import java.util.*;

/*
마법사 상어와 블리자드
 */

public class BOJ21611 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N, M, result;
    static int[][] map;
    static int[] bubbles, dr = {0, 1, 0, -1}, dc = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        initInput();
        setBubble();
        magic();

        System.out.println(result);
    }

    static void change() {
        int tmpIdx = 0;
        int[] tmp = new int[N * N - 1];

        for(int i = 0; i < bubbles.length; i++) {
            int current = bubbles[i];
            int length = 1;
            int nextIdx = i + 1;

            while(true) {
                if(nextIdx < tmp.length && current == bubbles[nextIdx]) {
                    nextIdx++;
                    length++;
                    continue;
                }

                if(current == 0) break;

                if(2 * tmpIdx < tmp.length) tmp[2 * tmpIdx] = length;
                else break;

                if(2 * tmpIdx + 1 < tmp.length) tmp[2 * tmpIdx + 1] = current;
                else break;

                tmpIdx++;
                i = nextIdx - 1;
                break;
            }
        }

        bubbles = tmp;
    }

    static void bumb() {
        while(true) {
            boolean flag = false;
            int length = 1;
            int startIdx = 0;

            for(int i = 0; i < bubbles.length - 1; i++) {
                if(bubbles[i] == 0) continue;
                if(bubbles[i] == bubbles[i + 1]) {
                    if(length == 1) startIdx = i;
                    length++;
                }

                if(bubbles[i] != bubbles[i + 1]) {
                    if(length >= 4) {
                        result += length * bubbles[startIdx];
                        for(int k = 0; k < length; k++) bubbles[i - k] = 0;
                        flag = true;
                    }

                    length = 1;
                    startIdx = 0;
                }
            }

            if(!flag) break;
            updateBubbleList();
        }
    }

    static void magic() throws Exception {
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = setDir(Integer.parseInt(st.nextToken()));
            int s = Integer.parseInt(st.nextToken());

            for(int k = 1; k < s + 1; k++) {
                int idx = 4 * k * k - 3 * k + 2 * k * d - 1;
                bubbles[idx] = 0;
            }
            updateBubbleList();
            bumb();
            change();
        }
    }

    static void updateBubbleList() {
        int[] tmp = new int[N * N - 1];
        int idx = 0;
        for (int bubble : bubbles) {
            if(bubble == 0) continue;
            tmp[idx++] = bubble;
        }
        bubbles = tmp;
    }

    static int setDir(int d) {
        if(d == 1) return 3;
        if(d == 2) return 1;
        if(d == 3) return 0;
        else return 2;
    }

    static void setBubble() {
        bubbles = new int[N * N - 1];

        int r = N / 2, c = N / 2;
        int dis = 1, dir = 0;

        int idx = 0;
        L: while(true) {
            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < dis; j++) {
                    int nr = r + dr[dir];
                    int nc = c + dc[dir];

                    if(!check(nr, nc) || map[nr][nc] == 0) break L;
                    bubbles[idx++] = map[nr][nc];
                    r = nr;
                    c = nc;
                }
                dir = (dir + 1) % 4;
            }
            dis++;
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static void initInput() throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
