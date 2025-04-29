import java.io.*;
import java.util.*;

/*
 * 바이러스
 */

public class BOJ2606_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 컴퓨터의 수
		int M = Integer.parseInt(br.readLine()); // 네트워크의 수
		
		ArrayList<Integer>[] adj  = new ArrayList[N + 1];
		
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adj[a].add(b);
			adj[b].add(a);
		}
		
		Deque<Integer> q = new ArrayDeque<>();
		q.add(1);
		
		boolean[] v = new boolean[N + 1];
		v[1] = true;
		
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int next: adj[current]) {
				if(v[next]) continue;
				
				v[next] = true;
				cnt++;
				q.add(next);
			}
		}
		
		System.out.println(cnt);
	}
}
