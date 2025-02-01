import java.io.*;
import java.util.*;

/*
 * 가장 긴 증가하는 부분 수열 3
 */

public class BOJ12738 {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		int[] lis = new int[N];
		int len = 0;
		
		// Arrays.binarySearch: 값이 배열에 존재하지 않을 경우, 삽입될 위치의 음수 값을 반환함
		// 반환값이 음수라면 -(삽입 위치 + 1) 값이 반환됨
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			int idx = Arrays.binarySearch(lis, 0, len, num);
			
			if(idx < 0) idx = ~idx;
			
			lis[idx] = num;
			if(idx == len) len++;
		}
		
		System.out.println(len);
	}
}
