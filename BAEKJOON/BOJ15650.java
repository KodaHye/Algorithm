import java.util.Scanner;

public class BOJ15650 {
	static int N;
	static int M;
	static int[] arr;
	static boolean isused[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[M + 1];
		isused = new boolean[N + 1];
		
		func(0);
	}
	
	static public void func(int k) {
		if(k == M) {
			for(int i = 0; i < M; i++) {
				System.out.print(arr[i] + " ");
			}System.out.println();
			return;
		}
		for(int i = 1; i < N + 1; i++) {
			if(!isused[i]) {
				arr[k] = i;
				isused[i] = true;
				func(k + 1);
				isused[i] = false;
			}
		}
	}
}
