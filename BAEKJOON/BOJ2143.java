import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 두 배열의 합
 */
public class BOJ2143 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, m;
	static long T, A[], B[], result;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		
		n = Integer.parseInt(br.readLine());
		A = new long[n + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < n + 1; i++) {
			A[i] = Integer.parseInt(st.nextToken()) + A[i - 1];
		}
	
		m = Integer.parseInt(br.readLine());
		B = new long[m + 1];
	
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i < m + 1; i++) {
			B[i] = Integer.parseInt(st.nextToken()) + B[i - 1];
		}
		
		long buA[] = new long[((int) (Math.pow(n, 2) + n)) / 2];
		
		int tmp = 0;
		
		for(int i = 1; i < A.length; i++) {
			for(int j = 0; j < i; j++) {
				buA[tmp] = (int) (A[i] - A[j]);
				tmp++;
			}
		}
		
		tmp = 0;
		
		long buB[] = new long[((int) (Math.pow(m, 2) + m)) / 2];
		for(int i = 1; i < B.length; i++) {
			for(int j = 0; j < i; j++) {
				buB[tmp] = (int) (B[i] - B[j]);
				tmp++;
			}
		}
		
		Arrays.sort(buA);
		Arrays.sort(buB);
		
		int pa = 0;
		int pb = buB.length - 1;
		
		while(pa < buA.length && pb >= 0) {
			long sum = buA[pa] + buB[pb];
			
			if(sum == T) {
				long a = buA[pa];
				long b = buB[pb];
				
				long cntA = 0;
				long cntB = 0;
				
				while(pa < buA.length && buA[pa] == a) {
					cntA++;
					pa++;
				}
				
				while(pb >= 0 && buB[pb] == b) {
					cntB++;
					pb--;
				}
				
				result += cntA * cntB;
			} else if(sum < T) {
				pa++;
			} else if(sum > T) {
				pb--;
			}
		}
		System.out.println(result);
	}
}
