import java.io.*;

/*
막대기
 */

public class BOJ1094 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int init = 64;
        int result = 0;

        while(init > 0) {
            if(init > n) init >>= 1;
            else {
                n -= init;
                result++;
                if(n == 0) break;
            }
        }

        System.out.println(result);
    }
}
