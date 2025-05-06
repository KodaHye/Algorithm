import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1495 {
	
	static int N, S, M;
	static int[] arr, answer;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 곡의 개수
		S = Integer.parseInt(st.nextToken()); // 시작 볼륨
		M = Integer.parseInt(st.nextToken()); // 한계값
		
		arr = new int[N];

		dp = new int[N][M + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < dp.length; i++) Arrays.fill(dp[i], Integer.MIN_VALUE);

		System.out.println(getDp(0, S));
	}
	
	static int getDp(int turn, int volumn) {
		if(volumn < 0 || volumn > M) return -1;
		if(turn == N) return volumn;
		
		if(dp[turn][volumn] != Integer.MIN_VALUE) return dp[turn][volumn];
		return dp[turn][volumn] = Math.max(
			    getDp(turn + 1, volumn + arr[turn]), 
			    getDp(turn + 1, volumn - arr[turn])
			);
	}
}
