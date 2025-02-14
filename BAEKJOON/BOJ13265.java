import java.io.*;
import java.util.*;

/*
 * 색칠하기
 * 이분그래프 → 인접한 노드끼리 같은 집합이 될 수 없음!→
 */

public class BOJ13265 {
	
	static ArrayList<Integer> adj[];
	static boolean[] v, check;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
			v = new boolean[n + 1];
			check = new boolean[n + 1];

			adj = new ArrayList[n + 1];
			for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Integer>();
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				adj[a].add(b);
				adj[b].add(a);
			}
			
			boolean isEven = true;
			for(int i = 1; i < n + 1; i++) {
				if(v[i]) continue;
				if(!(isEven = bfs(i))) break;
			}
			
			sb.append(isEven ? "possible" : "impossible").append("\n");
		}
		
		System.out.println(sb);
	}
	
	static boolean bfs(int node) {
		boolean isEven = true;
		
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		
		q.add(node);
		v[node] = true;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int next: adj[current]) {
				if(v[next]) {
					
					if(!(check[next] ^ check[current])) return false;
					continue;
				}
				
				v[next] = true;
				q.add(next);
				check[next] = !check[current];
			}
		}
		
		return isEven;
	}
}
