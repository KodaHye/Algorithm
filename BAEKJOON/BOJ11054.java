import java.io.*;
import java.util.*;

/*
 * 가장 긴 바이노틱 부분 수열
 */

public class BOJ11054 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		for(int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		int[] dp1 = new int[N];
		int[] dp2 = new int[N];
		
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < i; j++) {
				if(arr[j] < arr[i]) dp1[i] = Math.max(dp1[j] + 1, dp1[i]);
			}
		}
		
		for(int i = N - 1; i >= 0; i--) {
			for(int j = N - 1; j > i; j--) {
				if(arr[j] < arr[i]) dp2[i] = Math.max(dp2[j] + 1, dp2[i]);
			}
		}
		
		int result = 0;
		for(int i = 0; i < N; i++) {
			result = Math.max(dp1[i] + dp2[i] + 1, result);
		}
		System.out.println(result);
	}
}
