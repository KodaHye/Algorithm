import java.io.*;
import java.util.*;

/*
 * 바이러스
 */
public class BOJ2606 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static ArrayList<Integer> adj[];
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[N + 1];
		
		for(int i = 0; i < N + 1; i++) adj[i] = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adj[a].add(b);
			adj[b].add(a);
		}
		
		Queue<Integer> queue = new LinkedList<>();
		boolean v[] = new boolean[N + 1];
		
		queue.add(1);
		v[1] = true;
		
		int count = 0;
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			for (Integer next : adj[current]) {
				if(!v[next]) {
					v[next] = true;
					count++;
					queue.add(next);
				}
			}
		}
		
		System.out.println(count);
	}
}
