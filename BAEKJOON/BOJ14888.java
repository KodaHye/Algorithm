import java.io.*;
import java.util.*;

/*
연산자 끼워넣기
 */

public class BOJ14888 {
    static int N, num[], ch[], min, max;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        N = Integer.parseInt(br.readLine());

        num = new int[N];
        ch = new int[4];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) num[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) ch[i] = Integer.parseInt(st.nextToken());

        func(0, num[0]);
        System.out.println(max + "\n" + min);
    }

    static int cal(int n1, int c, int n2) {
        switch(c) {
            case 0: return n1 + n2;
            case 1: return n1 - n2;
            case 2: return n1 * n2;
            default: return n1 / n2;
        }
    }

    static void func(int k, int result) {
        if(k == N - 1) {
            max = Math.max(result, max);
            min = Math.min(result, min);
            return;
        }

        for(int i = 0; i < 4; i++) {
            if(ch[i] == 0) continue;
            ch[i]--;
            func(k + 1, cal(result, i, num[k + 1]));
            ch[i]++;
        }
    }
}
