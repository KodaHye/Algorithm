import java.io.*;
import java.util.*;

/*
 * 네트워크 연결
 */
public class BOJ1922 {
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
	static int N, M, p[]; // N: 컴퓨터의 수, M: 연결할 수 있는 선의 수
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		
		p = new int[N + 1];
		for (int i = 1; i < p.length; i++) {
			p[i] = i;
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(s == e) continue;
			queue.add(new Edge(s, e, c));
		}
		
		int count = 0;
		int result = 0;
		while(!queue.isEmpty()) {
			Edge current = queue.poll();
			
			if(find(current.s) == find(current.e)) continue;
			result += current.c;
			union(current.s, current.e);
			count++;
			if(count == N - 1) break;
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
		return a = p[a] == a ? a : find(p[a]);
	}
}
