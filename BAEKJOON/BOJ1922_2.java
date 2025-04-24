import java.io.*;
import java.util.*;

/*
 * 네트워크 연결
 */

public class BOJ1922_2 {
	
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
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 컴퓨터의 수
		int M = Integer.parseInt(br.readLine()); // 선의 수
		
		int[] p = new int[N + 1];
		for(int i = 0; i < p.length; i++) p[i] = i;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			pq.add(new Edge(s, e, w));
		}
		
		int cnt = 0;
		int result = 0;
		
		while(cnt != N - 1) {
			
			Edge current = pq.poll();
			
			if(find(p, current.s) == find(p, current.e)) continue;
			union(p, current.s, current.e);
			
			result += current.w;
			cnt++;
		}
		
		System.out.println(result);
	}
	
	static void union(int[] p, int a, int b) {
		a = find(p, a);
		b = find(p, b);
		
		if(a != b) p[b] = a;
	}
	
	static int find(int[] p, int a) {
		return p[a] = p[a] == a ? a : find(p, p[a]);
	}
}
