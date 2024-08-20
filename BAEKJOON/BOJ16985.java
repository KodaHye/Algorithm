import java.io.*;
import java.util.*;

/*
Maaaaaaaaaze
 */

public class BOJ16985 {
    static class Node {
        int r, c, depth, dis;

        public Node(int r, int c, int depth, int dis) {
            this.r = r;
            this.c = c;
            this.depth = depth;
            this.dis = dis;
        }
    }
    static boolean sel[], map[][][], copyMap[][][];
    static int order[], rotate[], result;
    static int dk[] = {-1, 0, 0, 0, 0, 1};
    static int dr[] = {0, -1, 0, 0, 1, 0};
    static int dc[] = {0, 0, -1, 1, 0, 0};

    static void setMap(int depth, int rotate) {
        if(rotate == 0) {
            for(int r = 0; r < 5; r++) {
                copyMap[depth][r] = Arrays.copyOf(map[depth][r], map[depth][r].length);
            }
        }
        if(rotate == 1) {
            for(int c = 0; c < 5; c++) {
                for(int r = 4; r >= 0; r--) {
                    copyMap[depth][c][4 - r] = map[depth][r][c];
                }
            }
        }
        if(rotate == 2) {
            for(int r = 4; r >= 0; r--) {
                for(int c = 4; c >= 0; c--) {
                    copyMap[depth][4 - r][4 - c] = map[depth][r][c];
                }
            }
        }

        if(rotate == 3) {
            for(int c = 4; c >= 0; c--) {
                for(int r = 0; r < 5; r++) {
                    copyMap[depth][4 - c][r] = map[depth][r][c];
                }
            }
        }
    }

    static int bfs(int r, int c) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(r, c, 0, 0));
        copyMap[order[0]][r][c] = false;

        int result = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            Node current = q.poll();

            if(current.depth == 4 && current.r != r && current.c != c && isCorner(current.r, current.c)) {
                result = Math.min(result, current.dis);
            }

            for(int d = 0; d < dk.length; d++) {
                int nk = current.depth + dk[d];
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if(!check(nk, nr, nc) || !copyMap[order[nk]][nr][nc]) continue;
                copyMap[order[nk]][nr][nc] = false;
                q.add(new Node(nr, nc, nk, current.dis + 1));
            }
        }

        return result;
    }
    static boolean isCorner(int r, int c) {
        for(int a = 0; a < 5; a += 4) {
            for(int b = 0; b < 5; b += 4) {
                if(a == r && b == c) return true;
            }
        }

        return false;
    }
    static void func(int r, int c, int depth) {
        if(depth == 5) {
            for(int k = 0; k < depth; k++) setMap(order[k], rotate[k]);
            result = Math.min(result, bfs(r, c));
            return;
        }

        for(int ro = 0; ro < 4; ro++) {
            rotate[depth] = ro;
            func(r, c, depth + 1);
        }
    }

    static boolean check(int k, int r, int c) {
        return k >= 0 && k < 5 && r >= 0 && r < 5 && c >= 0 && c < 5;
    }

    static void getOrder(int depth) {
        if(depth == 5) {
            for(int r = 0; r < 5; r += 4) {
                for(int c = 0; c < 5; c += 4) {
                    if(!map[order[0]][r][c]) continue;
                    func(r, c, 1);
                }
            }
            return;
        }

        for(int i = 0; i < 5; i++) {
            if(sel[i]) continue;
            sel[i] = true;
            order[depth] = i;
            getOrder(depth + 1);
            sel[i] = false;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sel = new boolean[5];
        rotate = new int[5];
        result = Integer.MAX_VALUE;

        order = new int[5];
        map = new boolean[5][5][5];
        copyMap = new boolean[5][5][5];

        for(int k = 0; k < 5; k++) {
            for(int r = 0; r < 5; r++) {
                String s = br.readLine();
                for(int c = 0; c < 5; c++) {
                    int num = s.charAt(c * 2) - '0';
                    if(num == 1) map[k][r][c] = true;
                }
            }
        }

        getOrder(0);
        System.out.println(result == Integer.MAX_VALUE ? -1: result);
    }
}
