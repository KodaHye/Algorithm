import java.util.Scanner;

/*
 * 1, 2, 3 더하기 3
 */
public class BOJ15988 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		long arr[] = new long[1000001];
		
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 4;
		
		for(int i = 4; i < arr.length; i++) {
			arr[i] = (arr[i - 1] + arr[i - 2] + arr[i - 3]) % 1000000009;
		}
		
		for(int i = 0; i < T; i++) {
			int a = sc.nextInt();
			System.out.println(arr[a]);
		}
	}
}
