import java.util.*;
import java.io.*;

/*
 * 가장 긴 증가하는 부분 수열 4
 * 최솟값 설정 주의하기
 * max의 초기값은 0이 아닌 1로 설정해야 됨!
 */

public class BOJ14002 {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] dp = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		int[] prev = new int[N];
		Arrays.fill(prev, -1);
		
		int max = 1;
		int maxIdx = 0;
		for(int i = 0; i < N; i++) {
			dp[i] = 1;
			for(int j = 0; j < i; j++) {
				if(dp[i] < dp[j] + 1 && arr[j] < arr[i]) {
					dp[i] = dp[j] + 1;
					prev[i] = j;
					if(max < dp[i]) {
						max = dp[i];
						maxIdx = i;
					}
				}
			}
		}
		ArrayList<Integer> list = new ArrayList<>();
		
		while(maxIdx != -1) {
			list.add(arr[maxIdx]);
			maxIdx = prev[maxIdx];
		}
		
		Collections.sort(list);
		for(int l: list) sb.append(l + " ");
		System.out.println(max + "\n" + sb);
	}
}