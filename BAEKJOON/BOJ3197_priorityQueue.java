import java.io.*;
import java.util.*;

/*
 * 백조의 호수
 * 
 * 동시에 없애는거 할 때, 큐 사이즈 고려해서 잘 없애기 ㅠ ㅠ
 * 1. 얼음 녹이는 시간 구하기 → 2. 첫 번째 백조에서 출발해서 두 번째 백조로 도달할 때, 시간 구하기 
 */
public class BOJ3197_priorityQueue {
	
	static class Node implements Comparable<Node> {
		int pos, time;
		public Node(int pos, int time) {
			this.pos = pos;
			this.time = time;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.time, o.time);
		}
	}
	
	static char[][] map;
	static int[][] time;
	static boolean[][] v;
	static int N, M;
	static int[] swans;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		initInput();
		
		System.out.println(solution());
	}
	
	static int solution() {
		v = new boolean[N][M];
		
		int sr = swans[0] / M, sc = swans[0] % M;
		v[sr][sc] = true;
		
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.add(new Node(swans[0], 0));
		
		while(!q.isEmpty()) {
			Node current = q.poll();
			
			int cr = current.pos / M, cc = current.pos % M;
			
			if(cr == swans[1] / M && cc == swans[1] % M) {
				return current.time;
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				
				if(!check(nr, nc) || v[nr][nc]) continue;
				
				q.add(new Node(nr * M + nc, Math.max(current.time, time[nr][nc])));
				v[nr][nc] = true;
			}
		}
		
		return -1;
	}
	static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		v = new boolean[N][M];
		time = new int[N][M];
		
		int swanIdx = 0;
		swans = new int[2];
		
		Queue<Integer> q = new ArrayDeque<>();
		
		for(int r = 0; r < N; r++) {
			String s = br.readLine();
			map[r] = s.toCharArray();
			
			for(int c = 0; c < M; c++) {
				
				if(s.charAt(c) == 'X') continue;

				int pos = r * M + c;

				if(s.charAt(c) == 'L') {
					swans[swanIdx] = pos;
					swanIdx += 1;
				}
				
				q.add(pos);
				v[r][c] = true;
			}
		}
		
		int day = 0;
		
		// 이 부분 구현하는거 잘 기억하기!!
		while(!q.isEmpty()) {
			int size = q.size();

			for(int i = 0; i < size; i++) {
				int current = q.poll();
				
				int r = current / M;
				int c = current % M;
				
				time[r][c] = day;
				
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(!check(nr, nc) || v[nr][nc]) continue;
					q.add(nr * M + nc);
					v[nr][nc] = true;
				}
			}
			
			day++;
		}
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}