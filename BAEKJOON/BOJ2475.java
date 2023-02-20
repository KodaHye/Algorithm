import java.util.Scanner;

/*
 * 검증수
 */
public class BOJ2475 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[5];

		for (int i = 0; i < arr.length; i++)
			arr[i] = Integer.parseInt(sc.next());
		
		int result = 0;
		for (int i = 0; i < arr.length; i++) {
			result += Math.pow(arr[i], 2);
		}
		
		System.out.println(result % 10);
	}
}
