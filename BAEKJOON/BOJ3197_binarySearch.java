import java.io.*;
import java.util.*;

/*
 * 백조의 호수
 * bfs + 이분탐색
 */

public class BOJ3197_binarySearch {
	static final int SWAN = -2, ICE = -1, WATER = 0;
	static class Point {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
		
	}
	
	static class Node {
		Point p;
		int time;
		public Node(Point p, int time) {
			this.p = p;
			this.time = time;
		}
	}
	static int[][] map;
	static int N, M, maxTime;
	static Queue<Node> q = new ArrayDeque<Node>();
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	static Point[] swans;
	
	public static void main(String[] args) throws Exception {
		
		initInput();
		System.out.println(solution());
	}
	
	// lower bound 구하기
	static int solution() {
		int s = 0, e = maxTime;
		
		while(s <= e) {
			int m = (s + e) / 2;
			
			if(isMeetSwan(m)) e = m - 1;
			else s = m + 1;
		}
		
		return s;
	}
	
	static boolean isMeetSwan(int time) {
		boolean[][] v = new boolean[N][M];
		
		Queue<Point> q = new ArrayDeque<Point>();
		q.add(swans[0]);
		
		v[swans[0].r][swans[0].c] = true;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			if(current.r == swans[1].r && current.c == swans[1].c) return true;
			for(int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				if(!isValid(nr, nc) || v[nr][nc] || map[nr][nc] > time) continue;
				q.add(new Point(nr, nc));
				v[nr][nc] = true;
			}
		}
		
		return false;
	}
	
	
	
	static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		swans = new Point[2];
		
		int swanIdx = 0;
		
		boolean[][] visit = new boolean[N][M];
		
		for(int r = 0; r < N; r++) {
			String s = br.readLine();
			for(int c = 0; c < M; c++) {
				if(s.charAt(c) == 'L') {
					map[r][c] = SWAN;
					visit[r][c] = true;

					swans[swanIdx] = new Point(r, c);
					swanIdx += 1;
				}
				
				if(s.charAt(c) == 'X') map[r][c] = ICE;
				if(s.charAt(c) == '.') map[r][c] = WATER;
			}
		}
		
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] != ICE) continue;
				
				boolean flag = false;
				
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(!isValid(nr, nc)) continue;
					if(map[nr][nc] == WATER || map[nr][nc] == SWAN) {
						flag = true;
						break;
					}
				}
				
				if(!flag) continue;
				map[r][c] = 1;
				q.add(new Node(new Point(r, c), 1));
				visit[r][c] = true;
			}
		}

		while(!q.isEmpty()) {
			
			Node current = q.poll();
			maxTime = Math.max(maxTime, current.time);
			
			for(int d = 0; d < 4; d++) {
				int nr = current.p.r + dr[d];
				int nc = current.p.c + dc[d];
				
				if(!isValid(nr, nc) || visit[nr][nc] || map[nr][nc] == WATER) continue;
				
//				if(map[nr][nc] == WATER) map[nr][nc] = current.time;
				if(map[nr][nc] == ICE) map[nr][nc] = current.time + 1;
				
				q.add(new Node(new Point(nr, nc), current.time + 1));
				visit[nr][nc] = true;
			}
		}
	}
	
	static boolean isValid(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
