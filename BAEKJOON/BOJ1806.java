import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 부분합
 */
public class BOJ1806 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static long S, arr[], result;
	static int N;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Long.parseLong(st.nextToken());
		 
		arr = new long[N + 1];
		
		st = new StringTokenizer(br.readLine());
		result = Long.MAX_VALUE;
		
		for(int i = 0; i < arr.length - 1; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		long sum = 0;
		
		while(end <= N) {
			if(sum < S) sum += arr[end++];
			if(sum >= S) {
				result = Math.min(result, end - start);
				sum -= arr[start++];
			}
		}

		result = result == Long.MAX_VALUE ? 0 : result;
		
		System.out.println(result);
	}
}
