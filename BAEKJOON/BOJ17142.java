import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 연구소 3
 */

public class BOJ17142 {
	static class Point {
		int r, c, cnt;
		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, result, map[][];
	static ArrayList<Point> adj;
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		adj = new ArrayList<>();
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 2) adj.add(new Point(r, c, 0));
			}
		}
		
		result = Integer.MAX_VALUE;
		
		func(0, 0, new boolean[adj.size()]);
		
		result = result == Integer.MAX_VALUE ? -1 : result;
		
		System.out.println(result);
	}

	private static void func(int k, int idx, boolean sel[]) {
		if(k == M) {
			int copy[][] = new int[N][N];
			
			copy = copyArr();
			
			int tmp = virus(copy, sel);
			
			if(tmp == -1) {
				if(result == Integer.MIN_VALUE) result = tmp;
			} else {
				if(result == Integer.MIN_VALUE) result = tmp;
				else result = Math.min(result, tmp);
			}

			return;
		}
		
		for(int i = idx; i < adj.size(); i++) {
			sel[i] = true;
			func(k + 1, i + 1, sel);
			sel[i] = false;
		}
	}

	private static int virus(int[][] copy, boolean sel[]) {
		int time = 0;
		
		Queue<Point> queue = new LinkedList<>();
		boolean v[][] = new boolean[N][N];
		
		for(int i = 0; i < sel.length; i++) {
			if(sel[i]) {
				queue.add(new Point(adj.get(i).r, adj.get(i).c, adj.get(i).cnt));
				v[adj.get(i).r][adj.get(i).c] = true;
			}
		}
		
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			
			if(checkMap(copy)) {
				result = Math.min(result, time);
				return time;
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				if(!check(nr, nc)) continue;
				if(copy[nr][nc] != 1 && !v[nr][nc]) {
					v[nr][nc] = true;
					copy[nr][nc] = 2;
					queue.add(new Point(nr, nc, current.cnt + 1));
					
					time = current.cnt + 1;
				}
			}
		}

		return -1;
	}

	private static boolean checkMap(int[][] copy) {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(copy[r][c] == 0) return false;
			}
		}
		
		return true;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

	private static int[][] copyArr() {
		int tmp[][] = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			tmp[r] = Arrays.copyOf(map[r], N);
		}

		return tmp;
	}
}
