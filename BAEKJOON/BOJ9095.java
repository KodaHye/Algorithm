import java.util.Scanner;
/*
 * 1, 2, 3 더하기
 */
public class BOJ9095 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		int arr[] = new int[11];
		
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 4;
		
		for(int i = 4; i < 11; i++) {
			arr[i] = arr[i - 1] + arr[i - 2] + arr[i - 3];
		}
		for (int test_case = 1; test_case < T + 1; test_case++) {
			int n = sc.nextInt();
			sb.append(arr[n] + "\n");
		}
		System.out.println(sb);
	}
}
