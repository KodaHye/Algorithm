import java.io.*;

/*
K번째 수
 */

public class BOJ1300 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int s = 1, e = K;

        int answer = Integer.MAX_VALUE;
        while(s <= e) {
            int m = (s + e) / 2;
            long cnt = 0;

            for(int i = 1; i < N + 1; i++) {
                long smallNumCnt = (m / i) > N ? N : (m / i);
                cnt += smallNumCnt;
            }

            if(cnt < K) s = m + 1;
            else {
                answer = m;
                e = m - 1;
            }
        }

        System.out.println(answer);
    }
}
