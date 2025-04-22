import java.io.*;

/*
 * 1로 만들기
 * 처음에는 BFS로 풀었으나 이후 DP로 수정함
 */
 
public class BOJ1463_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int X = Integer.parseInt(br.readLine());
		
		int[] dp = new int[X + 1];
		
		for(int i = 2; i < dp.length; i++) {
			dp[i] = dp[i - 1] + 1;
			
			if(i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			if(i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
		}
		
		System.out.println(dp[X]);
	}
}
