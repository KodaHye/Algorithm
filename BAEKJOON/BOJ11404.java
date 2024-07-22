import java.io.*;
import java.util.*;

/*
플로이드
 */

public class BOJ11404 {
    static int INF = 987654321;
    static int arr[][];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        initInput();
        floydWarshall();
    }

    public static void floydWarshall() {
        for(int k = 1; k < arr.length; k++) {
            for(int s = 1; s < arr.length; s++) {
                if(s == k) continue;
                for(int e = 1; e < arr.length; e++) {
                    if(e == k || s == k) continue;
                    arr[s][e] = Math.min(arr[s][e], arr[s][k] + arr[k][e]);
                }
            }
        }

        for(int r = 1; r < arr.length; r++) {
            for(int c = 1; c < arr[r].length; c++) {
                if(arr[r][c] == INF) arr[r][c] = 0;
                sb.append(arr[r][c] + " ");
            } sb.append("\n");
        }

        System.out.print(sb);
    }
    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][n + 1];

        for(int i = 0; i < arr.length; i++) {
            Arrays.fill(arr[i], INF);
            arr[i][i] = 0;
        }

        int m = Integer.parseInt(br.readLine());

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[a][b] = Math.min(arr[a][b], c);
        }
    }
}
