import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 수열
 */

public class BOJ2559 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K, arr[], sum[], max;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1];
		sum = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum[i] = sum[i - 1] + arr[i];
		}
		
		for(int i = K; i < sum.length; i++) {
			if(sum[i] - sum[i - K] > max) {
				max = sum[i] - sum[i - K];
			}
		}
		
		System.out.println(max);
	}
}
