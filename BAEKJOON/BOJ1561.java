import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
놀이 공원
 */
public class BOJ1561 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, arr[];

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if(N <= M) {
            System.out.println(N);
            return;
        }

        long start = 0;
        long end = 2000000000L * 30;
        long time = 0;

        long sum = 0;

        while(start <= end) {
            sum = M;

            long middle = (start + end) / 2;
            for(int i = 0; i < M; i++) sum += (middle / arr[i]);

            if(sum >= N) {
                end = middle - 1;
                time = middle;
            }
            else start = middle + 1;
        }

        sum = M;

        for(int i = 0; i < M; i++) sum += (time - 1) / arr[i];

        for(int i = 0; i < M; i++) {
            if(time % arr[i] == 0) {
                sum += 1;
            }
            if(sum == N) {
                System.out.println(i + 1);
                break;
            }
        }
    }
}
