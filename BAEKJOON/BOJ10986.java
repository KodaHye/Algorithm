import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 나머지 합
 */
public class BOJ10986 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M; 
	static long S[], m[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		m = new long[1001];
		
		S = new long[N + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i < N + 1; i++) {
			S[i] = (S[i - 1] + Integer.parseInt(st.nextToken())) % M;
			m[(int) S[i]]++;
		}
		
		long result = 0;
		
		for(int i = 0; i < M + 1; i++) {
			if(m[i] != 0) result += (m[i] * (m[i] - 1)) / 2;
		}
		result += m[0];
		
		System.out.println(result);
		
	}
}
