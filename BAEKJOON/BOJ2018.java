import java.util.*;

/*
수들의 합 5
 */

public class BOJ2018 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int cnt = 1;
        int startIdx = 1;
        int endIdx = 1;
        int sum = 1;

        while(endIdx < N) {
            if(sum < N) {
                endIdx++;
                sum += endIdx;
            } else {
                if(sum == N) cnt++;
                sum -= startIdx;
                startIdx++;
            }
        }

        System.out.println(cnt);
    }
}
