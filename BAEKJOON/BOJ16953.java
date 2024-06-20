import java.io.*;
import java.util.*;

/*
A → B
 */

public class BOJ16953 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int cnt = 0;

        while(A < B) {
            if(B % 2 == 0) {
                B /= 2;
                cnt++;
            } else if(B % 10 == 1) {
                B /= 10;
                cnt++;
            } else break;
        }

        System.out.println(B == A ? cnt + 1 : -1);
    }
}
