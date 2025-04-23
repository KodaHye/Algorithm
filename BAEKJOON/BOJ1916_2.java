import java.io.*;
import java.util.*;

/*
 * 최소비용 구하기
 * '노드의 개수 × 간선의 개수' 값이 크다면 우선순위 큐 사용 + 방문처리 
 */

public class BOJ1916_2 {
	
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
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 도시의 개수
		int M = Integer.parseInt(br.readLine()); // 버스의 개수
		
		ArrayList<Node>[] adj = new ArrayList[N + 1];
		
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[s].add(new Node(e, w));
		}
		
		st = new StringTokenizer(br.readLine());
		
		int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
		
		int[] dis = new int[N + 1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(s, 0));
		dis[s] = 0;
		
		while(!q.isEmpty()) {
			Node current = q.poll();
			
			if(dis[current.e] < current.w) continue;
			
			for(Node next: adj[current.e]) {
				if(dis[next.e] > dis[current.e] + next.w) {
					dis[next.e] = dis[current.e] + next.w;
					
					q.add(new Node(next.e, dis[next.e]));
				}
			}
		}
		
		System.out.println(dis[e]);
	}
}
