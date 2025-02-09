import java.util.Scanner;

/*
 * A + B - C
 */

public class BOJ31403 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
		
		System.out.println(a + b - c);
		System.out.println(Integer.parseInt(String.valueOf(a) + String.valueOf(b)) - c);
	}
}
