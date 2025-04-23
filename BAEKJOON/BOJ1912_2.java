import java.io.*;
import java.util.*;

/*
 * 연속합
 */

public class BOJ1912_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		
		arr[0] = Integer.parseInt(st.nextToken());
		int result = arr[0];
		
		for(int i = 1; i < n; i++) {
			
			int num = Integer.parseInt(st.nextToken());
			arr[i] = Math.max(arr[i - 1] + num, num);
			
			result = Math.max(result, arr[i]);
		}
		
		System.out.println(result);
	}
}
