import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * RGB거리 2
 */

public class BOJ17404 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, house[][], dp[][], result;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		house = new int[N][3];
		
		result = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				house[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		for(int k = 0; k < 3; k++) {
			dp = new int[N][3];

			dp[0][k] = house[0][k];
			
			for(int i = 0; i < 3; i++) {
				if(i != k) dp[0][i] = 987654321;
			}
			
			for(int i = 1; i < N; i++) {
				dp[i][0] = house[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
				dp[i][1] = house[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
				dp[i][2] = house[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
			}
			
			for(int i = 0; i < 3; i++) {
				if(k != i) {
					result = Math.min(dp[N - 1][i], result);
				}
			}
		}
		
		System.out.println(result);
	}
}
