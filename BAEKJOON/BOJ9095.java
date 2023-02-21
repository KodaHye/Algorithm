

import java.util.Scanner;

/*
 * 1, 2, 3 더하기
 * n을 1, 2, 3의 합으로 나타내는 방법
 */
public class BOJ9095 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		for(int test_case = 1; test_case < T + 1; test_case++) {
			int n = sc.nextInt();
			
			func(n);
		}
	}
}
