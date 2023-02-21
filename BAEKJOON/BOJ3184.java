import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 양
 */
public class BOJ3184 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C;
	static int W, L;
	static char[][] map;
	static int totalW, totalL;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for(int i = 0; i < map.length; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < map[i].length; j++) {
				map[i][j] = tmp.charAt(j);
			}
		}
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				W = 0;
				L = 0;
				if(map[i][j] == '.' || map[i][j] == 'o' || map[i][j] =='v') {
					queue(i, j);
					
//					System.out.println(W + " " + L);
					if(W < L) W = 0;
					else L = 0;
					totalW += W;
					totalL += L;
				}
			}
		}
		
		System.out.println(totalL + " " + totalW);
	}	
	
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static public void queue(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		
		queue.offer(new int[] {r, c});
		if(map[r][c] == 'o') L++;
		if(map[r][c] == 'v') W++;
		map[r][c] = '#';
		
		while(!queue.isEmpty()) {
			int[] point = queue.poll();
			for(int i = 0; i < 4; i++) {
				int nr = point[0] + dr[i];
				int nc = point[1] + dc[i];
				
				if(nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] != '#') {
					if(map[nr][nc] == 'o') L++;
					if(map[nr][nc] == 'v') W++;
					map[nr][nc] = '#';
					
					queue.add(new int[] {nr, nc});
				}
			}
		}
	}
}
