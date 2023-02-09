import java.util.Scanner;

public class BOJ15652 {
	static int[] arr;
	static boolean[] v;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		arr = new int[M];
		v = new boolean[N];
		func(0);
		
	}
	
	static int tmp = 0;
	public static void func(int k) {
		if(k == arr.length) {
			for(int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			} System.out.println();
			return;
		}
		
		for(int i = 0; i < v.length; i++) {
			if(i >= tmp && !v[i]) {
				arr[k] = i + 1;
				tmp = i;
				func(k + 1);
				tmp = 0;
			}
		}
	}
}
