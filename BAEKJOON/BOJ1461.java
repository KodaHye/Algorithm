import java.util.*;
import java.io.*;

/*
 * 도서관
 * 제일 긴 경로를 제외하고는 2배씩 이동
 * → 모두 다 두 배씩 해주고, 제일 긴 경로 빼주기모
 */

public class BOJ1461 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 책의 개수
		int M = Integer.parseInt(st.nextToken()); // 한 번에 들 수 있는 책의 개수
		
		TreeSet<Integer> plus = new TreeSet<Integer>();
		TreeSet<Integer> minus = new TreeSet<Integer>();
		
		st = new StringTokenizer(br.readLine());
		
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(num > 0) plus.add(num);
			else minus.add(num);
			
			max = Math.max(max, Math.abs(num));
		}
		
		int value = 0;
		
		while(!plus.isEmpty()) {
			
			for(int i = 0; i < M && !plus.isEmpty(); i++) {
				if(i == 0) value += Math.abs(plus.pollLast()) * 2;
				else plus.pollLast();
			}
		}
		
		while(!minus.isEmpty()) {
			for(int i = 0; i < M && !minus.isEmpty(); i++) {
				if(i == 0) value += Math.abs(minus.pollFirst()) * 2;
				else minus.pollFirst();
			}
		}
		
		value -= max;
		System.out.println(value);
	}
}
