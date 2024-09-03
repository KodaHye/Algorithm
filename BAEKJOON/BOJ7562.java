import java.io.*;
import java.util.*;

/*
나이트의 이동
큐 초기화 조심하기
 */

public class BOJ7562 {
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int map[][];
    static int dr[] = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int dc[] = {-2, -1, 1, 2, 2, 1, -1, -2};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        Point s, e;

        Queue<Point> q = new LinkedList<>();

        for(int tc = 0; tc < T; tc++) {
            q.clear();
            int L =Integer.parseInt(br.readLine());
            map = new int[L][L];

            st = new StringTokenizer(br.readLine());
            s = setPoint(st);
            st = new StringTokenizer(br.readLine());
            e = setPoint(st);

            map[s.r][s.c] = 1;
            q.add(s);

            while(!q.isEmpty()) {
                Point current = q.poll();

                if(current.r == e.r && current.c == e.c) {
                    sb.append((map[current.r][current.c] - 1) + "\n");
                    break;
                }
                for(int d = 0; d < 8; d++) {
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];

                    if(!check(nr, nc) || map[nr][nc] != 0) continue;
                    map[nr][nc] = map[current.r][current.c] + 1;
                    q.add(new Point(nr, nc));
                }
            }
        }

        System.out.print(sb);
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < map.length && c >= 0 && c < map.length;
    }

    static Point setPoint(StringTokenizer st) {
        return new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }
}
