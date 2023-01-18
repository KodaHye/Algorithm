import java.util.Arrays;
import java.util.Scanner;

public class 수정렬하기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		Integer[] arr= new Integer[n];
		for(int i = 0; i < arr.length; i++) 
			arr[i] = sc.nextInt();
		
		Arrays.sort(arr);
		
		for(int i = 0 ; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

}
