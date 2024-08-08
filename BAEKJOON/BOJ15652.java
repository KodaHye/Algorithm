import java.io.*;
import java.util.*;

/*
N과 M (4)
 */

public class BOJ15652 {
    static int N, M, arr[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];

        func(0, 0);
        System.out.print(sb);
    }

    static void func(int idx, int num) {
        if(idx == M) {
            for(int i = 0; i < arr.length; i++) {
                sb.append((arr[i] + 1) + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i = num; i < N; i++) {
            arr[idx] = i;
            func(idx + 1, i);
        }
    }
}
