import java.io.*;
import java.util.*;

/*
 * 가장 긴 증가하는 부분 수열 2
 * binarySearch할 때, lowerbound 반환하기!
 */

public class BOJ12015 {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] lis = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		int length = 1;
		lis[0] = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(lis[length - 1] < num) {
				lis[length++] = num;
			} else {
				int idx = binarySearch(lis, num, 0, length);
				lis[idx] = num; 
			}
		}
		
		System.out.println(length);
	}
	
	// lowerbound 반환하기!
	static int binarySearch(int[] lis, int num, int s, int e) {
		while(s <= e) {
			int m = (s + e) / 2;
			
			if(lis[m] < num) s = m + 1;
			else e = m - 1;
		}
		
		return s;
	}
}
