import java.util.Arrays;
import java.util.Scanner;

public class BOJ2587 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Integer[] arr = new Integer[5];
		int total = 0;

		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
			total += arr[i];
		}
		Arrays.sort(arr);

		System.out.println(total / 5);
		System.out.println(arr[arr.length / 2]);
	}
}
