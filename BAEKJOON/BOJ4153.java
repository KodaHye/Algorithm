import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 직각삼각형
 */
public class BOJ4153 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static long[] a;
	
	public static void main(String[] args) throws Exception {
		while(true) {
			st = new StringTokenizer(br.readLine());

			a = new long[3];
			int i = 0;
			
			while(st.hasMoreTokens()) {
				a[i] = Long.parseLong(st.nextToken());
				i++;
			}
			
			if(a[0] == 0 && a[1] == 0 && a[2] == 0) break;
			
			Arrays.sort(a);
			
			if(a[2] * a[2] == a[0] * a[0] + a[1] * a[1]) System.out.println("right");
			else System.out.println("wrong");
		}
	}
}
