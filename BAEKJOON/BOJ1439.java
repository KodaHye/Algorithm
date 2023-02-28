import java.util.Scanner;

/*
 * 뒤집기
 */
public class BOJ1439 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		
		int one = 0;
		int zero = 0;
		
		char tmp = input.charAt(0);
		
		if(tmp == '1') one++;
		else zero++;
		
		for(int i = 1; i < input.length() ;i++) {
			if(input.charAt(i) == '0' && input.charAt(i) != tmp) zero++;
			if(input.charAt(i) == '1' && input.charAt(i) != tmp) one++;
			tmp = input.charAt(i);
		}
		
		System.out.println(Math.min(zero, one));
	}
}
