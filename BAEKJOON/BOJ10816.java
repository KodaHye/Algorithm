import java.util.Scanner;

/*
 * 숫자 카드 2
 */
public class BOJ10816 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int N = sc.nextInt();
		int arr[] = new int[20000001];
		
		for(int i = 0; i < N; i++) {
			int input = sc.nextInt();
			arr[input + 10000000]++;
		}
		
		int M = sc.nextInt();
		for(int i = 0; i < M; i++) {
			int input = sc.nextInt();
			sb.append(arr[input + 10000000] + " ");
		}
		
		System.out.println(sb);
	}
}
