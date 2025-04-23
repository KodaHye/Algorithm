import java.io.*;
import java.util.*;

/*
 * 최단경로
 * - 간선의 수: E, 정점 개수: V일 때
 * 	 * 우선순위 큐 사용 X: O(EV) → 시간초과 
 *   * 우선순위 큐 사용 O: O(ElogV)
 */

public class BOJ1753_2 {
	static final int INF = Integer.MAX_VALUE;
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
		
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수
		
		ArrayList<Node>[] adj = new ArrayList[V + 1];
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();
		
		int start = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[u].add(new Node(v, w));
		}
		
		int[] dis = new int[V + 1];
		Arrays.fill(dis, INF);
		
		dis[start] = 0;
		
		PriorityQueue<Node> q = new PriorityQueue<>();

		q.add(new Node(start, 0));
		
		while(!q.isEmpty()) {
			Node current = q.poll();
			
			for(Node next: adj[current.e]) {
				if(dis[current.e] + next.w < dis[next.e]) {
					dis[next.e] = dis[current.e] + next.w;
					
					q.add(new Node(next.e, dis[next.e]));
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < V + 1; i++) {
			sb.append(dis[i] == INF ? "INF" : dis[i]).append("\n");
		}
		
		System.out.println(sb);
	}
}
