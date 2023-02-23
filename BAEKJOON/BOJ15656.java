import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * N과 M(7)
 */
public class BOJ15656 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, sel[];
	static ArrayList<Integer> list;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(!list.contains(num)) list.add(num);
		}
		
		sel = new int[M];
		
		Collections.sort(list);

		func(0);
		
		System.out.println(sb);
	}

	private static void func(int k) {		
		if(k == M) {
			for (int i = 0; i < sel.length; i++) {
				sb.append(sel[i] + " ");
			}sb.append("\n");
			return;
		}
		
		for(int i = 0; i < list.size(); i++) {
			sel[k] = list.get(i);
			func(k + 1);
		}
	}
}
