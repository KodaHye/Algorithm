import java.io.*;
import java.util.*;

/*
 * 전깃줄
 */

public class BOJ2565 {
	static int N, minIdx, maxIdx;
	static int[][] arr;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		initInput(); 
		getLISLengthByBinarySearch();
	}

	// 이분탐색을 통해 LIS의 길이를 구하는 방법
	static void getLISLengthByBinarySearch() {
		int[] lis = new int[arr.length];
		
		lis[0] = arr[0][1];
		int cnt = 1;
		
		for(int i = 1; i < arr.length; i++) {
			if(lis[cnt - 1] < arr[i][1]) {
				lis[cnt] = arr[i][1];
				cnt += 1;
			} else {
				// 직접 LowerBound를 구해도 되지만, Arrays.binarySearch 내장함수를 사용해도 됨
				// int idx = getLowerBound(lis, arr[i][1], 0, cnt);
				// Arrays.binarySearch: key와 일치하는 요소의 인덳 ㅡ반환
				// 일치하는 요소가 여러 개 있다면 무작위 인덱스 반환
				// 검색 실패하는 경우 음수 반환
				int idx = Arrays.binarySearch(lis, 0, cnt, arr[i][1]);

				// ~ 연산을 하는 이유: https://code0xff.tistory.com/68
				if(idx < 0) idx = ~idx;
				
				lis[idx] = arr[i][1];
			}
		}
		
		System.out.println(N - cnt);
	}
	
	static int getLowerBound(int[] lis, int target, int s, int e) {
		while(s <= e) {
			int m = (s + e) / 2;
			
			if(lis[m] < target) s = m + 1;
			else e = m - 1;
		}
		
		return s;
	}
	static void solution() {
		
		int maxLength = 0;
		 
		for(int i = 0; i < N; i++) {
			dp[i] = 1; 
			for(int j = 0; j < i; j++) {
				if(dp[i] < dp[j] + 1 && arr[j][1] < arr[i][1]) {
					dp[i] = dp[j] + 1;  
				} 
			}   
			maxLength = Math.max(maxLength, dp[i]);
		}  

		System.out.println(N - maxLength);
	}
	
	static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][2];

		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			arr[i][0] = l;
			arr[i][1] = r;
		}
		
		Arrays.sort(arr, (o1, o2) -> Integer.compare(o1[0], o2[0]));
		dp = new int[N];
	}
}
