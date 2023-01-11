package SWEA;

import java.util.Scanner;

// 홀수만 더하기
public class d1_2072 {

	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		
		for(int test_case = 1; test_case < T + 1; test_case++) {
			int sum = 0;
			for(int i = 0; i < 10; i++) {
				int num = sc.nextInt();
				if(num % 2 != 0) {
					sum = sum + num;
				}
			}
			System.out.println("#" + test_case + " " + sum);
		}
	}

}
