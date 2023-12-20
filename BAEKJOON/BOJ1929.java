import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
소수 구하기
 */
public class BOJ1929 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int M, N;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        boolean prime[] = new boolean[N + 1];

        /*
        에라토스테네스의 체
        소수의 배수를 지우면 소수가 된다.
         */

        prime[0] = prime[1] = true;

        for(int i = 2; i < N + 1; i++) {
            if(!prime[i]) {
                for(int j = 2; i * j < N + 1; j++) prime[i * j] = true;
            }
        }

        for(int i = M; i < N + 1; i++) {
            if(!prime[i]) sb.append(i).append("\n");
        }

        System.out.print(sb);
    }
}
