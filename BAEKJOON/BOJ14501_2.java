import java.io.*;
import java.util.*;

/*
 * 퇴사
 * dp[i]: i번째 날부터 퇴사 날까지 벌 수 있는 최대 수입
 */

public class BOJ14501_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] T = new int[N + 1];
		int[] P = new int[N + 1];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			
			T[i] = t;
			P[i] = p;
		}
		
		int[] dp = new int[N + 1];
		
		for(int i = N - 1; i >= 0; i--) {
			
			// 상담이 퇴사일까지 끝나지 않는 경우
			if(i + T[i] > N) dp[i] = dp[i + 1];
			
			// 오늘 시작되는 상담을 했을 때, 퇴사일 안에 끝나는 경우
			else dp[i] = Math.max(dp[i + 1], dp[i + T[i]] + P[i]);
		}
		
		System.out.println(dp[0]);
	}
}
