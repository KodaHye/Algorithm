import java.io.*;
import java.util.*;

/*
 * 연속합 2
 * 문제 잘 이해하고 점화식 세우기...
 */

public class BOJ13398_2 {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// L[i], R[i]: i번째 인덱스까지 포함했을 때 최대 합
		// L: i기준 왼쪽, R: i기준 오른쪽
		int[] L = new int[n];
		L[0] = arr[0];
		
		int result = L[0];
		
		for(int i = 1; i < L.length; i++) {
			L[i] = Math.max(L[i - 1] + arr[i], arr[i]);
			result = Math.max(result, L[i]); // 한 개도 제거하지 않았을 때, 최댓값
		}
		
		int[] R = new int[n];
		R[n - 1] = arr[n - 1];
		
		for(int i = n - 2; i >= 0; i--) {
			R[i] = Math.max(R[i + 1] + arr[i], arr[i]);
		}
		
		// 한 개를 제거했을 때
		for(int i = 1; i < n - 1; i++) {
			result = Math.max(result, L[i - 1] + R[i + 1]);
		}

		System.out.println(result);
	}
}
