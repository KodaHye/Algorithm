import java.io.*;
import java.util.*;

/*
 * 최소 스패닝 트리
 */
public class BOJ1197 {
	static class Edge implements Comparable<Edge>{
		int s, e, c;

		public Edge(int s, int e, int c) {
			super();
			this.s = s;
			this.e = e;
			this.c = c;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.c, o.c);
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int V, E, p[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		p = new int[V + 1];
		for (int i = 1; i < p.length; i++) {
			p[i] = i;
		}
		
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			queue.add(new Edge(a, b, c));
		}
		
		long result = 0;
		
		int cnt = 0;
		while(!queue.isEmpty()) {
			Edge current = queue.poll();
			
			if(find(current.s) == find(current.e)) continue;
			union(current.s, current.e);
			result += current.c;
			
			if(cnt == V - 1) break;
		}

		System.out.println(result);
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) {
			p[b] = a;
		}
	}

	private static int find(int a) {
		return p[a] = p[a] == a ? a : find(p[a]);
	}
}
