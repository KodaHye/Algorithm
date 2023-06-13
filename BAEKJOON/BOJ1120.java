import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문자열
 */
public class BOJ1120 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String A = st.nextToken();
		String B = st.nextToken();
		
		int same = 0;

		for(int i = B.length() - A.length(); i >= 0; i--) {
			for(int j = 0; j <= i; j++) {
				int tmp = 0;
				for(int k = 0; k < A.length(); k++) {
					if(A.charAt(k) == B.charAt(k + j)) tmp++;
				}
				same = Math.max(same, tmp);
			}
		}
		
		System.out.println(A.length() - same);
	}
}
