import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 연구소 2
 * 벽: -, 바이러스를 놓은 위치: 0
 */
public class BOJ17141 {
	static class Point {
		int r;
		int c;
		int w;
		public Point(int r, int c, int w) {
			super();
			this.r = r;
			this.c = c;
			this.w = w;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, time, result, map[][], tmp[][];
	static Point[] sel;
	static ArrayList<Point> virus;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = Integer.MAX_VALUE;
		sel = new Point[M];
		map = new int[N][N];
		virus = new ArrayList<>();
		
		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 2) {
					virus.add(new Point(r, c, 0));
					map[r][c] = 0;
				}
			}
		}
		
		selVirus(0, 0);
		
		result = result == Integer.MAX_VALUE ? -1 : result;
		System.out.println(result);
	}

	static int count;
	private static void selVirus(int k, int idx) {
		if(k == M) {
			count++;
			tmp = new int[N][N];
			tmp = copyArr();
			
			for(int i = 0; i < sel.length; i++) {
				tmp[sel[i].r][sel[i].c] = 2;
			}
			bfs(sel);

			if(checkVirus()) {
				result = Math.min(time, result);
			}
			return;
		}
		for(int i = idx; i < virus.size(); i++) {
			sel[k] = virus.get(i);
			selVirus(k + 1, i + 1);
		}
	}

	private static int[][] copyArr() {
		int tmp[][] = new int[N][N];
		for (int r = 0; r < tmp.length; r++) {
			for (int c = 0; c < tmp[r].length; c++) {
				tmp[r][c] = map[r][c];
			}
		}
		return tmp;
	}

	private static boolean checkVirus() {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(tmp[r][c] == 0) return false;
			}
		}
		return true;
	}

	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	private static void bfs(Point[] sel) {
		Queue<Point> queue = new LinkedList<>();

		boolean v[][] = new boolean[N][N];
		
		for(int i = 0; i < sel.length; i++) {
			queue.add(sel[i]);
		}
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			v[p.r][p.c] = true;
			
			time = p.w;
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(!check(nr, nc)) continue;
				if(tmp[nr][nc] == 0 && !v[nr][nc]) {
					v[nr][nc] = true;
					tmp[nr][nc] = 2;
					queue.add(new Point(nr, nc, p.w + 1));
				}
			}
		}
	}
	private static boolean check(int nr, int nc) {
		// TODO Auto-generated method stub
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
