import java.io.*;
import java.util.*;

/*
체스판 다시 칠하기 2
 */

public class BOJ25682 {
    static char map[][];
    static int N, M, K, diffB[][], diffW[][];

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }
    private static void solution() {
        int result = Integer.MAX_VALUE;

        for(int r = 1; r < N + 1; r++) {
             for(int c = 1; c < M + 1; c++) {
                 diffB[r][c] = diffB[r - 1][c] + diffB[r][c - 1] - diffB[r - 1][c - 1];
                 diffW[r][c] = diffW[r - 1][c] + diffW[r][c - 1] - diffW[r - 1][c - 1];

                 if((r + c) % 2 == 0) {
                     if(map[r][c] == 'W') diffB[r][c]++;
                     if(map[r][c] != 'W') diffW[r][c]++;
                 }
                 if((r + c) % 2 != 0){
                     if(map[r][c] == 'W') diffW[r][c]++;
                     if(map[r][c] != 'W') diffB[r][c]++;
                 }
             }
        }

        for(int r = K; r < N + 1; r++) {
            for(int c = K; c < M + 1; c++) {
                int calDiffW = diffW[r][c] - diffW[r - K][c] - diffW[r][c - K] + diffW[r - K][c - K];
                int calDiffB = diffB[r][c] - diffB[r - K][c] - diffB[r][c - K] + diffB[r - K][c - K];
                result = Math.min(result, Math.min(calDiffB, calDiffW));
            }
        }
        System.out.println(result);
    }

    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N + 1][M + 1];
        for(int r = 1; r < map.length; r++) {
            String s = br.readLine();
            for(int c = 1; c < map[0].length; c++) {
                map[r][c] = s.charAt(c - 1);
            }
        }

        diffB = new int[N + 1][M + 1];
        diffW = new int[N + 1][M + 1];
    }
}
