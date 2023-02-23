import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N과 M(6)
 */
public class BOJ15655 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, arr[], sel[];
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		sel = new int[M];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		func(0, 0);
	}
	private static void func(int k, int idx) {
		if(k == M) {
			for(int i = 0; i < sel.length; i++) {
				System.out.print(sel[i] + " ");
			}System.out.println();
			return;
		}
		for(int i = idx; i < arr.length; i++) {
			sel[k] = arr[i];
			func(k + 1, i + 1);
		}
	}
}
