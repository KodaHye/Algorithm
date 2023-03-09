import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

/*
 * N과 M(9)
 * visit 배열을 사용해서 같은 숫자가 수열에 포함되지 않도록 함
 */
public class BOJ15663 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, arr[], sel[];
	static boolean[] v;
	static LinkedHashSet<String> ans;
	
	public static void main(String[] args) throws Exception{
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		sel = new int[M];
		v = new boolean[N];
		ans = new LinkedHashSet<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < arr.length ;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		func(0);
		
		for (String string : ans) {
			System.out.println(string);
		}
	}

	private static void func(int k) {		
		if(k == M) {
			sb.setLength(0);
			for(int i = 0; i < M; i++) {
				sb.append(sel[i] + " ");
			}
			ans.add(sb.toString());
			return;
		}
		for(int i = 0; i < arr.length; i++) {
			if(v[i]) continue;
			v[i] = true;
			sel[k] = arr[i];
			func(k + 1);
			v[i] = false;
		}
	}
	
}