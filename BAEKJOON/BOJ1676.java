import java.math.BigInteger;
import java.util.Scanner;

/*
 * 팩토리얼 0의 개수
 * 0! = 1
 */
public class BOJ1676 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		BigInteger arr[] = new BigInteger[501];
		
		arr[0] = new BigInteger("1");
		arr[1] = new BigInteger("1");
		
		for(int i = 2; i < N + 1; i++) {
			arr[i] = arr[i - 1].multiply(new BigInteger(String.valueOf(i)));
		}
		
		int count = 0;
		for(int i = 0; i < String.valueOf(arr[N]).length(); i++) {
			if(String.valueOf(arr[N]).charAt(String.valueOf(arr[N]).length() - 1 - i) == '0') count++;
			else break;
		}
		System.out.println(count);
	}
}
