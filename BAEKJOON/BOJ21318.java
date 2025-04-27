1import java.io.*;
import java.util.*;

public class BOJ21318 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		int[] mistake = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i < N; i++) {
			mistake[i] = mistake[i - 1];
			
			// i번 악보가 i + 1보다 난이도가 높다면 실수를 함
			if(arr[i - 1] > arr[i]) mistake[i] += 1;	
		}
		
		int Q = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			
			sb.append(mistake[y] - mistake[x]).append("\n");
		}
		
		System.out.println(sb);
	}
}
