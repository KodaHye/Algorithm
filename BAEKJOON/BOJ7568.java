import java.util.Iterator;
import java.util.Scanner;

public class BOJ7568 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		int[][] arr = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}
		
		for(int i = 0; i < N; i++) {
			int count = 0;
			for(int j = 0; j < N; j++) {
				if(i != j) {
					if(arr[i][0] > arr[j][0] && arr[i][1] > arr[j][1]) {
						count += 1;
					}
				}
			}
			sb.append(count + " ");
		}
//		for (int i = 0; i < arr.length; i++) {
//			for (int j = 0; j < 2; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
		System.out.println(sb);
	}

}
