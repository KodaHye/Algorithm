import java.util.Scanner;

/*
 * 꿀 아르바이트
 */
public class BOJ12847 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		long sum[] = new long[n + 1];
		 
		for(int i = 1; i < n + 1; i++) sum[i] = sum[i - 1] + sc.nextInt();
		
		long max = 0;
		
		for(int i = m; i < n + 1; i++) {
			max = Math.max(max, sum[i] - sum[i - m]);
		}
		
		System.out.println(max);
	}
}
