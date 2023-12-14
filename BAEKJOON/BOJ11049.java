import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11049 {
    /*
    행렬 곱셈 순서
     */
    static class Matrix {
        int r, c;

        public Matrix(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, dp[][];
    static Matrix matrix[];

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        matrix = new Matrix[N + 1];
        dp = new int[N + 1][N + 1];

        for(int i = 0; i < dp.length; i++) Arrays.fill(dp[i], -1);

        for(int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            matrix[i] = new Matrix(r, c);
        }

        System.out.println(func(1, N));
    }

    private static int func(int start, int end) {
        int result = Integer.MAX_VALUE;
        if(dp[start][end] != -1) return dp[start][end];
        if(start == end) return 0;
        if(start + 1 == end) {
            int tmp = matrix[start].r * matrix[end].r * matrix[end].c;
            return tmp;
        }

        for(int i = start; i< end; i++) {
            result = Math.min(result, func(start, i) + func(i + 1, end) + (matrix[start].r * matrix[i].c * matrix[end].c));
        }

        return dp[start][end] = result;
    }
}
