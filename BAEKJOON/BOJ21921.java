import java.io.*;
import java.util.*;

/*
 * 블로그
 */

public class BOJ21921 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, X, arr[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		int start = 0;
		int end = X;
		
		int sum = 0;
		
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i = start; i < end; i++) {
			sum += arr[i];
		}
		
		int count = 1;
		
		int tmp = sum;
		
		for(int i = end; i < arr.length; i++) {	
			tmp += arr[i];
			tmp -= arr[i - X];
			
			if(tmp == sum) count++;
			if(tmp > sum) {
				sum = tmp;
				count = 1;
			}
		}
		
		if(sum == 0) System.out.println("SAD");
		else System.out.println(sum + "\n" + count);
	}
}
