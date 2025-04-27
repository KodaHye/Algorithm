import java.io.*;
import java.util.*;

/*
 * 별자리 만들기
 */

public class BOJ4386_2 {
	
	static class Point {
		double r, c;
		
		public Point(double r, double c) {
			this.r = r;
			this.c = c;
		}
		
		public double getDis(Point o) {
			double dr = (this.r - o.r) * (this.r - o.r);
			double dc = (this.c - o.c) * (this.c - o.c);
			
			return Math.sqrt(dr + dc);
		}
	}
	
	static class Node implements Comparable<Node> {
		int s, e;
		double w;
		
		public Node(int s, int e, double w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		
		@Override
		public int compareTo(Node o) {
			return Double.compare(this.w, o.w);
		}
	}
	
	static ArrayList<Point> points;
	static int[] p;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		points = new ArrayList<>();
		p = new int[n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			double r = Double.parseDouble(st.nextToken());
			double c = Double.parseDouble(st.nextToken());
			
			points.add(new Point(r, c));
			p[i] = i;
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for(int i = 0; i < points.size(); i++) {
			for(int j = i; j < points.size(); j++) {
				pq.add(new Node(i, j, points.get(i).getDis(points.get(j))));
			}
		}
		
		int cnt = 0;
		double result = 0;
		
		while(cnt != n - 1) {
			Node current = pq.poll();
			
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
