import java.util.*;

/*
소수&팰린드롬
 */

public class BOJ1747 {
    static int N;
    static boolean isNotPrime[] = new boolean[1_500_001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        getPrime();
        solution();
    }
    static void solution() {
        for(int i = N; i < 1_500_001; i++) {
            if(isNotPrime[i]) continue;
            if(isPalindrome(i)) {
                System.out.println(i);
                return;
            }
        }
    }

    private static boolean isPalindrome(int num) {
        String numToStr = String.valueOf(num);
        int digit = numToStr.length();

        for(int i = 0; i < digit / 2; i++) {
            if(numToStr.charAt(i) != numToStr.charAt(digit - 1 - i)) return false;
        }

        return true;
    }

    static void getPrime() {

        isNotPrime[0] = isNotPrime[1] = true;

        for(int i = 2; i < Math.sqrt(1_500_001); i++) {
            if(isNotPrime[i]) continue;
            for(int j = 2 * i; j < 1_500_001; j += i) isNotPrime[j] = true;
        }
    }
}
