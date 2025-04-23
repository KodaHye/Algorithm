import java.io.*;

/*
 * LCS 2
 * 1. dp 배열을 통해 공통 부분 수열의 길이와 관련된 테이블을 구함
 * 2. dp 테이블에서 역추적을 통해 LCS 문자열 구하기
 *    - 맨 끝에서부터 시작
 *    - 문자가 같으면 LCS 문자열에 추가후, [r - 1][c - 1]로 이동
 *    - 같지 않다면, [r - 1][c]와 [r][c - 1]중 큰 값으로 이동
 */

public class BOJ9252 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s1 = br.readLine();
		String s2 = br.readLine();
		
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];
		
		for(int r = 1; r < dp.length; r++) {
			for(int c = 1; c < dp[0].length; c++) {
				
				if(s1.charAt(r - 1) != s2.charAt(c - 1)) {
					dp[r][c] = Math.max(dp[r - 1][c], dp[r][c - 1]);
				} else {
					dp[r][c] = dp[r - 1][c - 1] + 1;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
			
		sb.append(dp[s1.length()][s2.length()]).append("\n");
		
		StringBuilder LCS = new StringBuilder();
		
		int r = s1.length(), c = s2.length();
		
		while(r != 0 && c != 0) {
			if(s1.charAt(r - 1) == s2.charAt(c - 1)) {
				LCS.insert(0, s1.charAt(r - 1));

				r -= 1;
				c -= 1;
			} else {
				if(dp[r - 1][c] > dp[r][c - 1]) {
					r -= 1;
				} else {
					c -= 1;
				}
			}
		}
		
		System.out.println(sb.append(LCS));
	}
}