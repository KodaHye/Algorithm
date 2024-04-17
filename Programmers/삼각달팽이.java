class 삼각달팽이 {
    static int dr[] = {1, 0, -1};
    static int dc[] = {0, 1, -1};

    public int[] solution(int n) {
        int map[][] = new int[n + 1][n + 1];
        int[] answer = new int[n * (n + 1) / 2];

        int num = 1;
        int r = 0;
        int c = 0;
        int idx = 0;
        int d = 0;

        while(n > 0) {
            for(int i = 0; i < n; i++) {
                r += dr[d];
                c += dc[d];

                map[r][c] = num++;
            }

            idx++;
            d = (d + 1) % 3;
            n--;
        }

        idx = 0;

        for(int i = 1; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j] == 0) continue;
                answer[idx++] = map[i][j];
            }
        }
        return answer;
    }
}