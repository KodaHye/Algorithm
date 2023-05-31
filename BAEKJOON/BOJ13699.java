import java.util.Scanner;

/*
 * 점화식
 */
public class BOJ13699 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		long dp[] = new long[36];
		
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		
		for(int i = 3; i < 36; i++) {
 			for(int j = 0; j < i; j++) {
				dp[i] += dp[j] * dp[i - 1 - j];
			}
		}
		
		System.out.println(dp[n]);
	}
}
