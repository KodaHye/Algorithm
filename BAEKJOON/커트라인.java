import java.util.Arrays;
import java.util.Scanner;

public class 커트라인 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		Integer[] arr = new Integer[n];
		
		for(int i = 0; i < n; i++) 
			arr[i] = sc.nextInt();
		
		Arrays.sort(arr);
		
		System.out.println(arr[n-k]);
	}

}
