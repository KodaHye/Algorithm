import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 최대공약수와 최소공배수
 */
public class BOJ2609 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int max = 0;
		for(int i = 1; i <= a && i <= b; i++) {
			if(a % i == 0 && b % i == 0) max = i;
		}
		
		int min = (a * b) / max;
		
		System.out.println(max + "\n" + min);
	}
}
