import java.io.*;
import java.util.*;

/*
 * 세 용액
 * 잘못된 풀이인 것 같으면 다른 방향으로 고민해보기
 * s, e를 변경하는 기준이 m이 아닐 수도 있음
 */

public class BOJ2473 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 용액의 수
		long[] arr = new long[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < arr.length; i++) arr[i] = Long.parseLong(st.nextToken());
		Arrays.sort(arr);
		
		long rs = 0, rm = 0, re = 0; // 결과 값으로 출력할 것들
		long diff = Long.MAX_VALUE;

		// 세 수를 더해서 0에 가까운 용액 만들어내기
		for(int i = 0; i < N - 2; i++) {
			int s = i;
			int m = i + 1;
			int e = N - 1;
			
			while(m < e) {
				long sum = arr[s] + arr[m] + arr[e];
				
				if(diff > Math.abs(sum)) {
					diff = Math.abs(sum);
					
					rs = arr[s];
					rm = arr[m];
					re = arr[e];
				}
				
				if(sum > 0) e--;
				else m++;
			}
		}
		System.out.println(rs + " " + rm + " " + re);
	}
}
