import java.util.*;
import java.io.*;

/*
드래곤 커브
 */

public class BOJ15685 {
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static ArrayList<Point> dragonCurves;
    static boolean v[][];
    static int dx[] = {1, 0, -1, 0}, dy[] = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        v = new boolean[101][101];

        int N = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < N; tc++) {
            dragonCurves = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            Point first = new Point(x, y);
            Point end = new Point(x + dx[d], y + dy[d]);

            mark(first);
            mark(end);

            dragonCurves.add(first);
            dragonCurves.add(new Point(end.x, end.y));

            while(g-- > 0) {
                int size = dragonCurves.size();
                for(int i = size - 2; i >= 0; i--) {
                    Point current = dragonCurves.get(i);

                    int a = end.x - current.x;
                    int b = end.y - current.y;

                    Point newPoint = new Point(end.x + b, end.y - a);
                    dragonCurves.add(newPoint);
                    mark(newPoint);

                    if(i == 0) {
                        end.x += b;
                        end.y -= a;
                    }
                }
            }
        }

        int result = 0;
        for(int r = 0; r < v.length - 1; r++) {
            for(int c = 0; c < v[0].length - 1; c++) {
                if(!v[r][c] || !v[r][c + 1] || !v[r + 1][c + 1] || !v[r + 1][c]) continue;
                result++;
            }
        }
        System.out.println(result);
    }

    private static void mark(Point p) {
        v[p.x][p.y] = true;
    }
}
