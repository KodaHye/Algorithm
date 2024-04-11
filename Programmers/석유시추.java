import java.util.*;

class 석유시추 {
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static Queue<Point> queue = new LinkedList<Point>();
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};
    static HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

    public int solution(int[][] land) {
        bfs(land);

        return getMax(land);
    }

    static int getMax(int[][] land) {
        boolean check[] = new boolean[hashMap.size() + 1];
        int max = 0;

        for(int c = 0; c < land[0].length; c++) {
            Arrays.fill(check, false);

            int tmp = 0;

            for(int r = 0; r < land.length; r++) {
                if(land[r][c] == 0 || check[land[r][c]]) continue;

                check[land[r][c]] = true;
                tmp += hashMap.get(land[r][c]);
            }

            if(tmp > max) max = tmp;
        }

        return max;
    }
    static void bfs(int[][] land) {
        boolean v[][] = new boolean[land.length][land[0].length];

        int key = 1;

        for(int r = 0; r < land.length; r++) {
            for(int c = 0; c < land[0].length; c++) {
                if(land[r][c] == 0 || v[r][c]) continue;

                queue.add(new Point(r, c));
                land[r][c] = key;
                v[r][c] = true;

                int cnt = 0;

                while(!queue.isEmpty()) {
                    Point current = queue.poll();
                    cnt++;

                    for(int d = 0; d < 4; d++) {
                        int nr = current.r + dr[d];
                        int nc = current.c + dc[d];

                        if(nr < 0 || nr >= land.length || nc < 0 || nc >= land[0].length) continue;
                        if(v[nr][nc] || land[nr][nc] == 0) continue;

                        land[nr][nc] = key;
                        queue.add(new Point(nr, nc));
                        v[nr][nc] = true;
                    }
                }
                hashMap.put(key, cnt);
                key++;
            }
        }
    }
}