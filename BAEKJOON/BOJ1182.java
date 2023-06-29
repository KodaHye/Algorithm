import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 부분수열의 합
 */
public class BOJ1182 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, S, arr[], count;
	static boolean sel[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		sel = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		func(0);
		
		System.out.println(count);
	}

	private static void func(int k) {
		if(k == N) {
			int sum = 0;
			boolean flag = false;
			
			for (int i = 0; i < sel.length; i++) {
				if(sel[i]) {
					flag = true;
					sum += arr[i];
				}
			}
			
			if(!flag) return;
			if(sum == S) count++;
			return;
		}
		
		sel[k] = true;
		func(k + 1);
		
		sel[k] = false;
		func(k + 1);
	}
}
