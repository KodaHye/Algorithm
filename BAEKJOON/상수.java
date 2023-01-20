package src;
import java.util.Scanner;

public class 상수 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
		String b = sc.next();
		
		char[] tmpA = new char[3];
		char[] tmpB = new char[3];
		
		for(int i = 0; i < 3; i++) {
			tmpA[i] = a.charAt(3 - i - 1);
			tmpB[i] = b.charAt(3 - i - 1);
		}
		System.out.println(Math.max(Integer.parseInt(String.valueOf(tmpA)), Integer.parseInt(String.valueOf(tmpB))));
	}
}
