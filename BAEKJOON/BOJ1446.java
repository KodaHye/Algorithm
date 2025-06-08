import java.io.*;
import java.util.*;

/*
 * 지름길
 */

public class BOJ1446 {
	
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
		
		int N = Integer.parseInt(st.nextToken()); // 지름길의 개수
		int D = Integer.parseInt(st.nextToken()); // 고속도로의 길이
		
		ArrayList<Node>[] adj = new ArrayList[D + 1];
		for(int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
			
			if(i == adj.length - 1) continue;
			adj[i].add(new Node(i + 1, 1));
		}
		
		PriorityQueue<Node> q = new PriorityQueue<>();
		

		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			if(e > D) continue; // 길의 범위를 벗어나는 구간
			adj[s].add(new Node(e, w));
		}

		int[] dis = new int[D + 1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		
		dis[0] = 0;
		q.add(new Node(0, 0));
		
		while(!q.isEmpty()) {
			Node current = q.poll();
			
			if(current.w > dis[current.e]) continue;
			
			for(Node next: adj[current.e]) {
				if(dis[current.e] + next.w >= dis[next.e]) continue;
				
				dis[next.e] = dis[current.e] + next.w;
				q.add(new Node(next.e, dis[next.e]));
			}
		}
		
		System.out.println(dis[D]);
	}
}
