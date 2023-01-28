

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2751 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Integer[] arr = new Integer[N];
		
		for(int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		
		for(int i = 0; i < arr.length; i++) sb.append(arr[i] + " \n");
		System.out.println(sb);
	}

}