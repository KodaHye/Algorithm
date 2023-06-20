import java.util.Scanner;
import java.util.TreeSet;

/*
 * 접미사 배열
 */
public class BOJ11656 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		TreeSet<String> set = new TreeSet<>();
		
		String S = sc.next();
		
		for(int i = 0; i < S.length(); i++) {
			set.add(S.substring(i, S.length()));
		}
		
		
		for (String string : set) {
			System.out.println(string);
		}
	}
}
