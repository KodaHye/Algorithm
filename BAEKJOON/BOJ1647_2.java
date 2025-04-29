import java.io.*;
import java.util.*;

/*
 * 도시 분할 계획
 */

public class BOJ1647_2 {
	
	static class Edge implements Comparable<Edge> {
		int s, e, w;
		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	static int[] p;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 집의 개수
		int M = Integer.parseInt(st.nextToken()); // 길의 개수
		
		p = new int[N + 1];
		
		for(int i = 0; i < p.length; i++) p[i] = i;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			pq.add(new Edge(s, e, w));
		}
		
		int cnt = 0, result = 0;
		
		while(cnt != N - 2) {
			Edge current = pq.poll();
			
			if(find(current.s) == find(current.e)) continue;
			union(current.s, current.e);
			
			result += current.w;
			cnt++;
		}
		
		System.out.println(result);
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
