import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
보물
 */
public class BOJ1026 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, A[], B[];

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        B = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) B[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(A);
        Arrays.sort(B);

        int result = 0;

        for(int i = 0; i < N; i++) {
            result += A[i] * B[N - 1 - i];
        }

        System.out.println(result);
    }
}
