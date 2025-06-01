import java.io.*;
import java.util.*;

/*
 * 개미
 */

public class BOJ4307 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(t-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int l = Integer.parseInt(st.nextToken()); // 막대 길이
			int n = Integer.parseInt(st.nextToken()); // 개미의 개수
			
			int[] arr = new int[n];
			
			for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(br.readLine());
			
			int shortest = Integer.MIN_VALUE;
			int longest = Integer.MIN_VALUE;
			
			for(int a: arr) {
				shortest = Math.max(shortest, Math.min(a, l - a));
				longest = Math.max(longest, Math.max(a, l - a));
			}
			
			sb.append(shortest).append(" ").append(longest).append("\n");
		}
		System.out.println(sb);
	}
}
