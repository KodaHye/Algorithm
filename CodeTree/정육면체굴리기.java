import java.util.*;
import java.io.*;

public class 정육면체굴리기 {
    static class Cube {
        int r, c;
        public Cube(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static StringBuilder sb = new StringBuilder();
    static Cube cube;
    static int N, M, row[], col[], K, map[][];
    static String orders;
    static int dr[] = {0, 0, 0, -1, 1};
    static int dc[] = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    public static void solution() {
        row[1] = map[cube.r][cube.c];
        map[cube.r][cube.c] = 0;


        for(int i = 0; i < K; i++) {
            int dir = orders.charAt(i * 2) - '0';
            if(rotateCube(dir)) {
                sb.append(col[3] + "\n");

                if(map[cube.r][cube.c] == 0) {
                    map[cube.r][cube.c] = col[1];
                } else {
                    col[1] = map[cube.r][cube.c];
                    row[1] = map[cube.r][cube.c];
                    map[cube.r][cube.c] = 0;
                }
            }
        }

        System.out.println(sb);
    }

    public static boolean rotateCube(int dir) {
        int nr = cube.r + dr[dir];
        int nc = cube.c + dc[dir];

        if(!check(nr, nc)) return false;
        cube.r = nr;
        cube.c = nc;

        if(dir == 1) {
            int start = row[0];

            for(int i = 1; i < 4; i++) row[i - 1] = row[i];
            row[3] = start;
            syncColAndRow();

        } else if(dir == 2) {
            int last = row[3];

            for(int i = 2; i >= 0; i--) row[i + 1] = row[i];
            row[0] = last;
            syncColAndRow();

        } else if(dir == 4) {
            int start = col[0];

            for(int i = 1; i < 4; i++) col[i - 1] = col[i];
            col[3] = start;
            syncRowAndCol();
        } else if (dir == 3) {
            int last = col[3];

            for(int i = 2; i >= 0; i--) col[i + 1] = col[i];
            col[0] = last;
            syncRowAndCol();
        }

        return true;
    }

    public static void syncRowAndCol() {
        row[1] = col[1];
        row[3] = col[3];
    }
    public static void syncColAndRow() {
        col[1] = row[1];
        col[3] = row[3];
    }
    public static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = new int[4];
        col = new int[4];

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        cube = new Cube(x, y);
        K = Integer.parseInt(st.nextToken());

        for(int r = 0; r < N; r++) {
            String s = br.readLine();
            for(int c = 0; c < M; c++) {
                map[r][c] = s.charAt(c * 2) - '0';
            }
        }

        orders = br.readLine();
    }
}