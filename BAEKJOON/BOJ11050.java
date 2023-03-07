import java.util.Scanner;

/*
 * 이항 계수 1
 * 자연수 N과 K가 주어졌을 떄 이항계수를 구하는 프로그램
 * D[i][j] = D[i - 1][j] + D[i - 1][j - 1]
 * D[i][0] = 1;
 * D[i][1] = i;
 * D[i][i] = 1;
 */

public class BOJ11050 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int arr[][] = new int[N + 1][N + 1];
		
		// 배열 초기화
		for(int i = 0; i < N + 1; i++) {
			arr[i][0] = 1;
			arr[i][1] = i;
			arr[i][i] = 1;
		}
		
		for (int i = 2; i < N + 1; i++) {
			for (int j = 1; j < i; j++) {
				arr[i][j] = arr[i - 1][j] + arr[i - 1][j - 1];
			}
		}
		
		System.out.println(arr[N][K]);
	}
}
