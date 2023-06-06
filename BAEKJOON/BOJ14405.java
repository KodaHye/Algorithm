import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 피카츄
 */
public class BOJ14405 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		String input = br.readLine();
		
		input = input.replace("pi", " ");
		input = input.replace("ka", " ");
		input = input.replace("chu", " ");
		
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) != ' ') {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");
		return;
	}
}
