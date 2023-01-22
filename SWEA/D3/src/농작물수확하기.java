import java.util.Scanner;

public class 농작물수확하기 {
	// 농장의 크기는 항상 홀수
	// 수확: 농장의 크기에 딱 맞는 정사각형 마름모 형태만 가능
	// 입력: 테스트 케이스 T
	// 농장의 크기N, 농작물의 가치
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();


		for (int test_case = 1; test_case < T + 1; test_case++) {
			int N = sc.nextInt();
			int[][] arr = new int[N][N];
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				String inStr = sc.next();
				for (int j = 0; j < arr[i].length; j++) {
					arr[i][j] = (int)inStr.charAt(j) - '0';
				}
			}

			for (int i = 0; i < arr.length / 2 + 1; i++) {
				for (int j = arr[i].length / 2 - i; j < arr[i].length / 2 + i + 1; j++) {
					sum += arr[i][j];
//					System.out.println(i + " " + j);
				}
			}
			for (int i = arr.length / 2 + 1; i < arr.length; i++) {
				for (int j = arr[i].length / 2 - (arr.length - i) + 1; j < arr[i].length /2 + (arr.length - i); j++) {
					sum += arr[i][j];
//					System.out.println(i + " " + j);
				}
			}
			System.out.println("#" + test_case + " " + sum);
		}
	}
}
