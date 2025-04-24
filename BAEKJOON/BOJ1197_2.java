import java.io.*;
import java.util.*;

/*
 * 최소 스패닝 트리
 */

public class BOJ1197_2 {
	static class Edge implements Comparable<Edge> {
		int s, e;
		long w;
		
		public Edge(int s, int e, long w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.w, o.w);
		}
	}
	
	static int[] p;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수
		
		p = new int[V + 1];
		for(int i = 0; i < p.length; i++) p[i] = i;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		while(E-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			pq.add(new Edge(s, e, w));
		}
		
		int cnt = 0;
		long cost = 0;
		
		while(cnt != V - 1) {
			
			Edge current = pq.poll();
			
			if(find(current.s) == find(current.e)) continue;
			union(current.s, current.e);
			
			cost += current.w;
			
			cnt++;
		}
		
		System.out.println(cost);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) p[b] = a;
	}
	
	static int find(int a) {
		return p[a] = a == p[a] ? a : find(p[a]);
	}
}
