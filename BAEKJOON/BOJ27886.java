import java.util.Scanner;

/*
 * 문자와 문자열
 */
public class BOJ27886 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String input = sc.next();
		int i = sc.nextInt();
		
		System.out.println(input.charAt(i - 1));
	}
}
