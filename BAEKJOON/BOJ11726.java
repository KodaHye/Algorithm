import java.util.Scanner;
/*
 * 2⨉n 타일링
 * 자료형 범위 한계 주의하기!
 */
public class BOJ11726 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		long arr[] = new long[1000 + 1];
		
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 3;
		
		for(int i = 4; i < n + 1; i++) {
			arr[i] = (arr[i - 1] + arr[i - 2]) % 10007;
		}
		
		System.out.println(arr[n]);
	}
}
