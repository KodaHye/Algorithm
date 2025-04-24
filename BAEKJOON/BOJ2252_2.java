import java.io.*;
import java.util.*;

/*
 * 줄세우기
 */

public class BOJ2252_2 {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] adj = new ArrayList[N + 1];
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();
		
		int[] indegree = new int[N + 1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			indegree[b]++;
			adj[a].add(b);
		}
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for(int i = 1; i < N + 1; i++) {
			if(indegree[i] != 0) continue;
			q.add(i);
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(!q.isEmpty()) {
			int current = q.poll();
			sb.append(current).append(" ");
			
			for(int next: adj[current]) {
				indegree[next]--;
				if(indegree[next] == 0) {
					q.add(next);
				}
			}
		}
		
		System.out.println(sb);
	}
}
