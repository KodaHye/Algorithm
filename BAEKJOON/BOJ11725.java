import java.io.*;
import java.util.*;

/*
 * 트리의 부모 찾기
 */
public class BOJ11725 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, p[];
	static ArrayList<Integer> adj[];
	static boolean v[];
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		adj = new ArrayList[N + 1];
		p = new int[N + 1];
		v = new boolean[N + 1];
		
		for(int i = 0; i < N + 1; i++) adj[i] = new ArrayList<>();
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adj[a].add(b);
			adj[b].add(a);
		}
		
		dfs(1);
		
		for(int i = 2; i < N + 1; i++) sb.append(p[i] + "\n");
		
		System.out.println(sb);
	}

	private static void dfs(int k) {
		v[k] = true;
		
		for (Integer next: adj[k]) {
			if(!v[next]) {
				v[next] = true;
				p[next] = k;
				dfs(next);
			}
		}
	}

}
