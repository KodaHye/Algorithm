import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * N과 M(12)
 */
public class BOJ15666 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, sel[];
	static ArrayList<Integer> list;
	static boolean v[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		sel = new int[M];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(!list.contains(num)) {
				list.add(num);
			}
		}
		
		v = new boolean[list.size()];
		Collections.sort(list);
		
		func(0, 0);
	}

	private static void func(int k, int idx) {
		if(k == M) {
			for(int i = 0; i < sel.length; i++) {
				System.out.print(sel[i] + " ");
			} System.out.println();
			return;
		}
		
		for(int i = idx; i < list.size(); i++) {
			sel[k] = list.get(i);
			func(k + 1, i);
		}
	}
	
}
