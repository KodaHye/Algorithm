import java.io.*;
import java.util.*;

/*
 * 도시 분할 계획
 */
public class BOJ1647 {
	static class Node implements Comparable<Node> {
		int e, c;

		public Node(int e, int c) {
			super();
			this.e = e;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.c, o.c);
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static ArrayList<Node> adj[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N + 1];
		
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();
		
		int tmp = Integer.MAX_VALUE;
		int start = 0;
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Node(b, c));
			adj[b].add(new Node(a, c));
		}
		
		boolean v[] = new boolean[N + 1];

		PriorityQueue<Node> queue = new PriorityQueue<>();
		
		
		queue.addAll(adj[1]);
		v[1] = true;
		
		long d = 0;
		int count = 0;
		
		int max_value = 0;
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();

			if(v[current.e]) continue;
			v[current.e] = true;
			count++;
			d += current.c;
			max_value = Integer.max(current.c, max_value);
			
			queue.addAll(adj[current.e]);
			
			if(count == N - 1) break;
		}

		System.out.println(d - max_value);
	}
}
