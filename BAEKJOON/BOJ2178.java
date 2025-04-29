import java.io.*;
import java.util.*;

/*
 * 미로 탐색
 */

public class BOJ2178 {
	
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		int[][] dis = new int[N][M];
		
		for(int r = 0; r < N; r++) map[r] = br.readLine().toCharArray();
		
		Deque<Integer> q = new ArrayDeque<>();
		q.add(0);
		
		dis[0][0] = 1;
		
		while(!q.isEmpty()) {
			int pos = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = pos / M + dr[d];
				int nc = pos % M + dc[d];
				
				if(!check(nr, nc, N, M) || dis[nr][nc] != 0 || map[nr][nc] == '0') continue;
				
				q.add(nr * M + nc);
				dis[nr][nc] = dis[pos / M][pos % M] + 1;
			}
		}

		System.out.println(dis[N - 1][M - 1]);
	}
	
	static boolean check(int r, int c, int n, int m) {
		return r >= 0 && r < n && c >= 0 && c < m;
	}
}
