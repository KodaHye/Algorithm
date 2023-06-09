import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 용액
 */
public class BOJ2467 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb;
	static StringTokenizer st;
	static int N;
	static long min, arr[];
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		arr = new long[N];
		min = Long.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		int start = 0;
		int end = N - 1;
		
		while(start < end) {
			long tmp = Math.abs(arr[end] + arr[start]);
			
			if(tmp <= min) {
				min = tmp;
				sb = new StringBuilder();
				sb.append(arr[start] + " " + arr[end]);
			}
			
			if(arr[start] + arr[end] > 0) end--;
			else start++;
		}
		System.out.println(sb);
	}
}
