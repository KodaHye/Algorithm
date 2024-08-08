import java.io.*;
import java.util.*;

/*
N과 M (1)
 */

public class BOJ15649 {
    static boolean sel[];
    static int N, M, arr[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sel = new boolean[N];
        arr = new int[M];

        func(0);
        System.out.print(sb);
    }

    static void func(int idx) {
        if(idx == M) {
            for(int i = 0; i < arr.length; i++) {
                sb.append((arr[i] + 1) + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0; i < N; i++) {
            if(sel[i]) continue;
            sel[i] = true;
            arr[idx] = i;
            func(idx + 1);
            sel[i] = false;
        }
    }
}
