import java.io.*;
import java.util.*;

/*
 * 빌런 호석
 * 숫자가 작다고 해서 완탐으로 무작정 하지 말고, 백트래킹 이용하기!!
 */

public class BOJ22251 {
	static int[] digitBit = {
			1 << 0 | 1 << 1 | 1 << 2 | 1 << 4 | 1 << 5 | 1 << 6,
			1 << 2 | 1 << 5,
			1 << 0 | 1 << 2 | 1 << 3 | 1 << 4 | 1 << 6,
			1 << 0 | 1 << 2 | 1 << 3 | 1 << 5 | 1 << 6,
			1 << 1 | 1 << 2 | 1 << 3 | 1 << 5,
			1 << 0 | 1 << 1 | 1 << 3 | 1 << 5 | 1 << 6,
			1 << 0 | 1 << 1 | 1 << 3 | 1 << 4 | 1 << 5 | 1 << 6,
			1 << 0 | 1 << 2 | 1 << 5,
			1 << 0 | 1 << 1 | 1 << 2 | 1 << 3 | 1 << 4 | 1 << 5 | 1 << 6,
			1 << 0 | 1 << 1 | 1 << 2 | 1 << 3 | 1 << 5 | 1 << 6	
			};
	
	static int N, K, P, X, result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		dfs(0, 0, 0);
		
		System.out.println(result - 1); // 자기 자신이 나오는 경우 빼주기
	}
	
	static void dfs(int digit, int num, int changeCnt) {
		if(num > N || changeCnt > P) return;
		
		if(digit == K) {
			if(num != 0) result++; // 0층은 될 수 없음!
			return;
		}
		
		for(int i = 0; i < 10; i++) {
			int pos = (int) Math.pow(10, digit);
			dfs(digit + 1, 
					i * pos + num,
					changeCnt + Integer.bitCount(digitBit[X / pos % 10] ^ digitBit[i]));
		}
	}
}
