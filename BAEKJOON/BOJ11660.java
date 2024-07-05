import java.io.*;
import java.util.*;

/*
구간 합 구하기 5
 */

public class BOJ11660 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int s[][] = new int[N + 1][N + 1];

        for(int r = 1; r < s.length; r++) {
            st = new StringTokenizer(br.readLine());

            for(int c = 1; c < s[0].length; c++) {
				s[r][c] = s[r - 1][c] + s[r][c - 1] - s[r - 1][c - 1] + Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());

            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

			sb.append(s[x2][y2] - s[x1 - 1][y2] - s[x2][y1 - 1] + s[x1 - 1][y1 - 1] + "\n");
        }

        System.out.print(sb);
    }
}
