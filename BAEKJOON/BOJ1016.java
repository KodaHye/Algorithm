import java.io.*;
import java.util.*;

/*
제곱 ㄴㄴ 수
 */

public class BOJ1016 {
    static long min, max;
    static boolean isNoPow[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        min = Long.parseLong(st.nextToken());
        max = Long.parseLong(st.nextToken());

        isNoPow = new boolean[(int) (max - min + 1)];
        int answer = (int) (max - min + 1);

        for(long i = 2; i * i <= max; i++) {
            long pow = i * i;
            long startIdx = min / pow;

            if(min % pow != 0) startIdx++;

            for(long j = startIdx; pow * j <= max; j++) {
                if(isNoPow[(int) (pow * j - min)]) continue;
                isNoPow[(int) (pow * j - min)] = true;
                answer--;
            }
        }

        System.out.println(answer);
    }
}
