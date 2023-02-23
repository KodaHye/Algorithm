import java.util.Arrays;
import java.util.Scanner;

/*
 * N과 M(8)
 */
public class BOJ15657 {
	static int N;
	static int M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		int[] arr = new int[N];
		int[] sel = new int[M];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		func(0, 0, arr, sel);
	}

	private static void func(int k, int idx, int[] arr, int[] sel) {
		if(k == M) {
			for(int i = 0; i < M; i++) {
				System.out.print(sel[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = idx; i < N; i++) {
			sel[k] = arr[i];
			func(k + 1, i, arr, sel);
		}
	}
}
