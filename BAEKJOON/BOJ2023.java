import java.util.*;

/*
신기한 소수
 */

public class BOJ2023 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        dfs(0, N);

        System.out.print(sb);
    }

    static void dfs(int k, int n) {
        if(n == 0) {
            if(isPrime(k)) sb.append(k + "\n");
            return;
        }

        for(int i = 0; i < 10; i++) {
            int tmp = k * 10 + i;
            if(isPrime(tmp)) dfs(tmp, n - 1);
        }
    }
    private static boolean isPrime(int num) {
        if(num < 2) return false;
        for(int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }

        return true;
    }
}
