import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 영역 구하기
 */
public class BOJ2583 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb;
	static StringTokenizer st;
	static int M, N, K;
	static int map[][];
	static int count;
	static int area;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		
		ArrayList<Integer> arr = new ArrayList<>();
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for(int r = y1; r < y2; r++) {
				for(int c = x1; c < x2; c++) {
					map[r][c] = 1;
				}
			}
		}
		
		
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				
				if(map[r][c] == 0) {
					count++;
					area = 1;
					bfs(r, c);
					arr.add(area);
				}
			}
		}
		Collections.sort(arr);
		
		sb.append(count + " \n");
		for (int i = 0; i < arr.size(); i++) {
			sb.append(arr.get(i) + " ");
		}
		System.out.println(sb);
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static public void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		
		queue.offer(new int[] {r, c});
		map[r][c] = 1;
		
		while(!queue.isEmpty()) {
			int[] Point = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = Point[0] + dr[i];
				int nc = Point[1] + dc[i];
				
				if(nr >= 0 && nr < M && nc >= 0 && nc < N && map[nr][nc] == 0) {
					area++;
					map[nr][nc] = 1;
					queue.add(new int[] {nr, nc});
				}
			}
		}
	}
}
