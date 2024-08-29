import java.io.*;
import java.util.*;

/*
소수 구하기
 */

public class BOJ1929 {
    static StringBuilder sb = new StringBuilder();
    static boolean isNotPrime[];

    static void getPrime() {
        isNotPrime[0] = isNotPrime[1] = true;

        for(int i = 2; i < Math.sqrt(isNotPrime.length); i++) {
            if(isNotPrime[i]) continue;
            for(int j = 2 * i; j < isNotPrime.length; j+= i) isNotPrime[j] = true;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        isNotPrime = new boolean[N + 1];
        getPrime();

        for(int i = M; i < N + 1; i++) {
            if(isNotPrime[i]) continue;
            sb.append(i + "\n");
        }

        System.out.print(sb);
    }
}
