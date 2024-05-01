import java.util.*;

class 미로탈출 {
    class  Point {
        int r, c, d;

        public Point(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    static int dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, -1, 1};
    static LinkedList<Point> queue = new LinkedList<Point>();
    static char map[][];
    static boolean v[][];
    static Point start, lever;

    public int solution(String[] maps) {
        int answer = Integer.MAX_VALUE;

        map = new char[maps.length][maps[0].length()];
        v = new boolean[maps.length][maps[0].length()];

        for(int r = 0; r < maps.length; r++) {
            for(int c = 0; c < maps[r].length(); c++) {
                map[r][c] = maps[r].charAt(c);
                if(map[r][c] == 'S') start = new Point(r, c, 0);
                if(map[r][c] == 'L') lever = new Point(r, c, 0);
            }
        }

        int result1 = bfs(start.r, start.c, 'L');
        int result2 = bfs(lever.r, lever.c, 'E');

        return answer = result1 == -1 || result2 == -1 ? -1 : result1 + result2;
    }

    int bfs(int r, int c, char ch) {
        int result = -1;

        initV();
        queue.clear();
        queue.add(new Point(r, c, 0));
        v[r][c] = true;

        L: while(!queue.isEmpty()) {
            Point current = queue.poll();

            if(ch == 'L' && map[current.r][current.c] == 'L') {
                result = current.d;
                break L;
            }

            if(ch == 'E' && map[current.r][current.c] == 'E') {
                result = current.d;
                break L;
            }

            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if(!check(nr, nc) || map[nr][nc] == 'X' || v[nr][nc]) continue;
                v[nr][nc] = true;
                queue.add(new Point(nr, nc, current.d + 1));
            }
        }

        return result;
    }

    void initV() {
        for(int r = 0; r < v.length; r++) {
            Arrays.fill(v[r], false);
        }
    }
    boolean check(int nr, int nc) {
        return nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length;
    }
}