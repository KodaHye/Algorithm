import java.util.Scanner;

/*
 * 피보나치 함수
 */
public class BOJ1003 {
	static int zero, one;
	static int arr[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		int arr[][] = new int[41][2];
		
		arr[0][0] = 1;
		arr[0][1] = 0;
		arr[1][0] = 0;
		arr[1][1] = 1;
		arr[2][0] = 1;
		arr[2][1] = 1;
		arr[3][0] = 2;
		arr[3][1] = 1;
		arr[4][0] = 2;
		arr[4][1] = 3;
		
		for(int i = 5; i < 41; i++) {
			arr[i][0] = arr[i - 1][1];
			arr[i][1] = arr[i - 1][0] + arr[i - 1][1];
		}
		
		for (int i = 0; i < T; i++) {
			int n = sc.nextInt();
			System.out.println(arr[n][0] + " " + arr[n][1]);
		}
	}
}
