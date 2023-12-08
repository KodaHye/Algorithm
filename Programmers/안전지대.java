class 안전지대 {
    static int dr[] = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int dc[] = {-1, 0, 1, 1, 1, 0, -1, -1};
    static boolean v[][];

    public int solution(int[][] board) {

        v = new boolean[board.length][board[0].length];

        int answer = board.length * board[0].length;

        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[r].length; c++) {
                if(board[r][c] == 1) {
                    if(!v[r][c]) {
                        v[r][c] = true;
                        answer--;
                    }

                    for(int k = 0; k < dr.length; k++) {
                        int nr = r + dr[k];
                        int nc = c + dc[k];

                        if(!check(nr, nc)) continue;
                        if(!v[nr][nc]) {
                            v[nr][nc] = true;
                            answer--;
                        }
                    }
                }
            }
        }

        return answer;
    }

    public boolean check(int nr, int nc) {
        return nr >= 0 && nr < v.length && nc >= 0 && nc < v[0].length;
    }
}