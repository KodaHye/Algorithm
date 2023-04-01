import java.util.Scanner;

/*
 * 정수 삼각형
 */
public class BOJ1932 {
	static int arr[][], dp[][], n, result, max;
	static int dr[] = {1, 1}; // 하, 우하
	static int dc[] = {0, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		arr = new int[n][n];
		dp = new int[n][n];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < i + 1; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		dp[0][0] = arr[0][0];
		
		for(int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i + 1; j++) {
				if(j == 0) dp[i][j] = arr[i][j] + dp[i - 1][j];
				else if(j == i) dp[i][j] = arr[i][j] + dp[i - 1][j - 1];
				else {
					int tmp1 = arr[i][j] + dp[i - 1][j - 1];
					int tmp2 = arr[i][j] + dp[i - 1][j];
					
					dp[i][j] = Math.max(tmp1, tmp2);
				}
			}
		}
		
		int result = 0;
		
		for(int i = 0; i < n; i++) {
			result = Math.max(result, dp[n - 1][i]);
		}
		
		System.out.println(result);
	}
}
