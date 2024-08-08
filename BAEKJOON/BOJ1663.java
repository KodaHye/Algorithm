import java.io.*;

/*
N-Queen
 */

public class BOJ1663 {
    static int N, result;
    static boolean v[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new boolean[N][N];

        func(0);
        System.out.println(result);
    }

    static void func(int r) {
        if(r == N) {
            result++;
            return;
        }

        for(int c = 0; c < N; c++) {
            if(!check(r, c)) continue;
            v[r][c] = true;
            func(r + 1);
            v[r][c] = false;
        }
    }

    static boolean check(int r, int c) {
        for(int i = r - 1; i >= 0; i--) {
            if(v[i][c]) return false;
        }

        for(int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
            if(v[i][j]) return false;
        }

        for(int i = r - 1, j = c + 1; i >= 0 && j < N; i--, j++) {
            if(v[i][j]) return false;
        }

        return true;
    }
}
