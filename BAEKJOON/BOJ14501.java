import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14501 {
    /*
    퇴사
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, T[], P[], D[];

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        D = new int[N + 2]; // 벌 수 있는 최대 수입
        T = new int[N + 1];
        P = new int[N + 1];

        for(int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = N ; i > 0; i--) {
            if(i + T[i] > N + 1) {
                D[i] = D[i + 1];
            } else {
                D[i] = Math.max(D[i + 1], D[i + T[i]] + P[i]);
            }
        }

        System.out.println(D[1]);
    }
}
