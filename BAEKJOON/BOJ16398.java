

import java.io.*;
import java.util.*;

public class BOJ16398 {
	static class Node implements Comparable<Node> {
		int e, c;

		public Node(int e, int c) {
			super();
			this.e = e;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.c, o.c);
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, map[][]; // 행성의 수
	static ArrayList<Node> adj[];
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		adj = new ArrayList[N + 1];
		
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int r = 1; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for (int r = 1; r < N + 1; r++) {
			for (int c = r + 1; c < N + 1; c++) {
				adj[r].add(new Node(c, map[r][c]));
				adj[c].add(new Node(r, map[r][c]));
			}
		}
		
		long min = Long.MAX_VALUE;

		PriorityQueue<Node> queue = new PriorityQueue<>();
		boolean v[] = new boolean[N + 1];
		
		long result = 0;
		
		v[1] = true;
		
		// 첫번째 정점과 연결된 정점들을 모두 queue에 넣음
		queue.addAll(adj[1]);

		int cnt = 0;
		
		// while 조건 true로 하지 말고 isEmpty로 하기
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			if(v[current.e]) continue;
			v[current.e] = true;
			
			result += current.c;
			
			queue.addAll(adj[current.e]);
			cnt++;
			if(cnt == N - 1) break;
		}

		System.out.println(result);
	}
}
