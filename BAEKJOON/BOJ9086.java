import java.util.Scanner;

/*
 * 문자열
 */
public class BOJ9086 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		for(int i = 0; i < T; i++) {
			String input = sc.next();
			
			sb.append(input.charAt(0) + "" + input.charAt(input.length() - 1) + "\n");
		}
		
		System.out.println(sb);
	}
}
