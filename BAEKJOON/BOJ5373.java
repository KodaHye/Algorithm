import java.io.*;
import java.util.*;

/*
큐빙
 */

public class BOJ5373 {
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static HashMap<Character, Point> center;
    static char cube[][], surface[][], line[];
    static StringBuilder sb = new StringBuilder();
    static void rotateCube(char ch, char angle) {
        updateCubeSurface(ch, angle);
        updateCubeLine(ch, angle);
    }

    static void updateCubeLine(char ch, char angle) {
        int idx;

        if(ch == 'U') {
            idx = 0;
            for(int c = 3; c < 6; c++) line[idx++] = cube[11][c];
            for(int c = 8; c >= 0; c--) line[idx++] = cube[3][c];

            idx = 0;
            if(angle == '+') {
                for(int c = 8; c >= 0; c--) cube[3][c] = line[idx++];
                for(int c = 3; c < 6; c++) cube[11][c] = line[idx++];
            } else {
                for(int c = 2; c >= 0; c--) cube[3][c] = line[idx++];
                for(int c = 3; c < 6; c++) cube[11][c] = line[idx++];
                for(int c = 8; c > 2; c--) cube[3][c] = line[idx++];
            }
        }
        if(ch == 'D') {
            idx = 0;
            for(int c = 5; c > 2; c--) line[idx++] = cube[9][c];
            for(int c = 0; c < 9; c++) line[idx++] = cube[5][c];

            idx = 0;
            if(angle == '+') {
                for(int c = 0; c < 9; c++) cube[5][c] = line[idx++];
                for(int c = 5; c > 2; c--) cube[9][c] = line[idx++];
            } else {
                for(int c = 6; c < 9; c++) cube[5][c] = line[idx++];
                for(int c = 5; c > 2; c--) cube[9][c] = line[idx++];
                for(int c = 0; c < 6; c++) cube[5][c] = line[idx++];
            }
        }
        if(ch == 'F') {
            idx = 0;
            for(int c = 3; c < 6; c++) line[idx++] = cube[2][c];
            for(int r = 3; r < 6; r++) line[idx++] = cube[r][6];
            for(int c = 5; c > 2; c--) line[idx++] = cube[6][c];
            for(int r = 5; r > 2; r--) line[idx++] = cube[r][2];

            idx = 0;
            if(angle == '+') {
                for(int r = 3; r < 6; r++) cube[r][6] = line[idx++];
                for(int c = 5; c > 2; c--) cube[6][c] = line[idx++];
                for(int r = 5; r > 2; r--) cube[r][2] = line[idx++];
                for(int c = 3; c < 6; c++) cube[2][c] = line[idx++];
            } else {
                for(int r = 5; r > 2; r--) cube[r][2] = line[idx++];
                for(int c = 3; c < 6; c++) cube[2][c] = line[idx++];
                for(int r = 3; r < 6; r++) cube[r][6] = line[idx++];
                for(int c = 5; c > 2; c--) cube[6][c] = line[idx++];
            }
        }
        if(ch == 'B') {
            idx = 0;
            for(int c = 3; c < 6; c++) line[idx++] = cube[8][c];
            for(int r = 5; r > 2; r--) line[idx++] = cube[r][8];
            for(int c = 5; c > 2; c--) line[idx++] = cube[0][c];
            for(int r = 3; r < 6; r++) line[idx++] = cube[r][0];

            idx = 0;
            if(angle == '+') {
                for(int r = 5; r > 2; r--) cube[r][8] = line[idx++];
                for(int c = 5; c > 2; c--) cube[0][c] = line[idx++];
                for(int r = 3; r < 6; r++) cube[r][0] = line[idx++];
                for(int c = 3; c < 6; c++) cube[8][c] = line[idx++];
            } else {
                for(int r = 3; r < 6; r++) cube[r][0] = line[idx++];
                for(int c = 3; c < 6; c++) cube[8][c] = line[idx++];
                for(int r = 5; r > 2; r--) cube[r][8] = line[idx++];
                for(int c = 5; c > 2; c--) cube[0][c] = line[idx++];
            }
        }
        if(ch == 'L') {
            idx = 0;
            for(int r = 0; r < 12; r++) line[idx++] = cube[r][3];

            idx = 0;
            if(angle == '+') {
                for(int r = 3; r < 12; r++) cube[r][3] = line[idx++];
                for(int r = 0; r < 3; r++) cube[r][3] = line[idx++];
            } else {
                for(int r = 9; r < 12; r++) cube[r][3] = line[idx++];
                for(int r = 0; r < 9; r++) cube[r][3] = line[idx++];
            }
        }

        if(ch == 'R') {
            idx = 0;
            for(int r = 11; r >= 0; r--) line[idx++] = cube[r][5];
            idx = 0;
            if(angle == '+') {
                for(int r = 8; r >= 0; r--) cube[r][5] = line[idx++];
                for(int r = 11; r > 8; r--) cube[r][5] = line[idx++];
            } else {
                for(int r = 2; r >= 0; r--) cube[r][5] = line[idx++];
                for(int r = 11; r > 2; r--) cube[r][5] = line[idx++];
            }
        }

    }

    static void updateCubeSurface(char ch, char angle) {
        int sr = center.get(ch).r;
        int sc = center.get(ch).c;

        for(int r = sr - 1; r < sr + 2; r++) {
            for(int c = sc - 1; c < sc + 2; c++) {
                int vr1 = sr - r, vc1 = sc - c;
                int vr2 = vc1, vc2 = vr1;

                if(angle == '+') vc2 *= -1;
                else vr2 *= -1;
                surface[1 - vr2][1 - vc2] = cube[r][c];
            }
        }

        for(int r = sr - 1; r < sr + 2; r++) {
            for(int c = sc - 1; c < sc + 2; c++) {
                cube[r][c] = surface[r - (sr - 1)][c - (sc - 1)];
            }
        }
    }

    static void setCube() {
        cube = new char[12][9];
        fillColor(0, 3, 3, 6, 'w');
        fillColor(6, 9, 3, 6, 'y');
        fillColor(3, 6, 3, 6, 'r');
        fillColor(9, 12, 3, 6, 'o');
        fillColor(3 ,6, 6, 9, 'b');
        fillColor(3, 6, 0, 3, 'g');
    }

    static void fillColor(int r1, int r2, int c1, int c2, char ch) {
        for(int r = r1; r < r2; r++) {
            for(int c = c1; c < c2; c++) {
                cube[r][c] = ch;
            }
        }
    }

    static void setCenter() {
        center.put('U', new Point(1, 4));
        center.put('D', new Point(7, 4));
        center.put('F', new Point(4, 4));
        center.put('B', new Point(10, 4));
        center.put('L', new Point(4, 1));
        center.put('R', new Point(4, 7));
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        surface = new char[3][3];
        line = new char[12];

        center = new HashMap<>();

        setCenter();
        for(int testCase = 0; testCase < tc; testCase++) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            setCube();

            for(int i = 0; i < n; i++) {
                String order = st.nextToken();
                rotateCube(order.charAt(0), order.charAt(1));
            }

            for(int r = 0; r < 3; r++) {
                for(int c = 3; c < 6; c++) sb.append(cube[r][c]);
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}
