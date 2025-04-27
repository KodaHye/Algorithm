import java.io.*;
import java.util.*;

/*
 * 책 나눠주기
 */

public class BOJ9576 {
	
	static class Node implements Comparable<Node> {
		int s, e;
		public Node(int s, int e) {
			this.s = s;
			this.e = e;
		}
		
		@Override
		public int compareTo(Node o) {
			if(this.e != o.e) return Integer.compare(this.e, o.e); // 끝점 내림차순
			return Integer.compare(this.s, o.s); // 시작점 오름차순
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(tc-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); // 책의 개수
			int M = Integer.parseInt(st.nextToken()); // 학부생 수
			
			PriorityQueue<Node> pq = new PriorityQueue();
			boolean[] v = new boolean[N + 1];
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				pq.add(new Node(a, b));
			}
			
			int cnt = 0;
			
			while(!pq.isEmpty()) {
				Node current = pq.poll();
				
				for(int i = current.s; i < current.e + 1; i++) {
					if(v[i]) continue;
					
					v[i] = true;
					cnt++;
					
					break;
				}
			}
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int solution(int n, int m) {
		int answer = 0;
		
		
		return answer;
	}
}
