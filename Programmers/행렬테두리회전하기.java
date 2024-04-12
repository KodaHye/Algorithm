class Point {
    int r, c;
    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
class 행렬테두리회전하기 {
    static int dr[] = {1, 0, -1, 0};
    static int dc[] = {0, 1, 0, -1};

    public int[] solution(int rows, int columns, int[][] queries) {
        int map[][] = new int[rows + 1][columns + 1];

        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[0].length; c++) map[r][c] = columns * (r - 1) + c;
        }

        int[] answer = new int[queries.length];

        for(int i = 0; i < queries.length; i++) {
            Point start = new Point(queries[i][0], queries[i][1]);
            Point end = new Point(queries[i][2], queries[i][3]);

            int d = 0;
            Point current = new Point(queries[i][0], queries[i][1]);

            int min = Integer.MAX_VALUE;
            int first = map[start.r][start.c];

            while(true) {
                min = Math.min(min, map[current.r][current.c]);
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];


                if((nr - start.r) > (end.r - start.r) || (nc - start.c) > (end.c - start.c) || (nr - start.r < 0)) {
                    d++;
                    continue;
                }

                map[current.r][current.c] = map[nr][nc];

                if(nr == start.r && nc == start.c) {
                    map[current.r][current.c] = first;
                    break;
                }

                current.r = nr;
                current.c = nc;
            }
            answer[i] = min;
        }
        return answer;
    }
}