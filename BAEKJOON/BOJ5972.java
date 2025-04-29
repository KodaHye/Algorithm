import java.io.*;
import java.util.*;

/*
택배 배송
 */

public class BOJ5972 {
	
	static class Node implements Comparable<Node> {
		int e, w;
		
		public Node(int e, int w) {
			this.e = e;
			this.w = w;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] adj = new ArrayList[N + 1];
		
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[s].add(new Node(e, w));
			adj[e].add(new Node(s, w));
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		
		int[] dis = new int[N + 1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		
		dis[1] = 0;
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			if(dis[current.e] < current.w) continue;
			
			for(Node next: adj[current.e]) {
				if(dis[next.e] > dis[current.e] + next.w) {
					dis[next.e] = dis[current.e] + next.w;
					
					pq.add(new Node(next.e, dis[next.e]));
				}
			}
		}
		
		System.out.println(dis[N]);
	}
}
