import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * Puyo Puyo
 */
public class BOJ11559 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Queue<Point> store = new LinkedList<>();
	static char map[][] = new char[12][6];
	static int count;
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		for (int r = 0; r < map.length; r++) {
			String input = br.readLine();
			
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = input.charAt(c);
			}
		}
		
//		4개 이상 붙어 있는지 확인
//		4개 이상 붙어 있으면, 터쳐버리기
		while(true) {
			boolean flag = false;;
			
			for(int r = 11; r > 0; r--) {
				for(int c = 0; c < 6; c++) {
					if(map[r][c] != '.' && bfs(r, c) >= 4) {
						flag = true;
						
						// store에 있는 것들 없애기
						while(!store.isEmpty()) {
							Point point = store.poll();
							
							map[point.r][point.c] = '.';
						}
					}
				}
			}
			
			if(!flag) break;
			
			count++;
			
			rebuild();
		}
		
		System.out.println(count);
	}

	private static void rebuild() {
		
		for(int c = 0; c < 6; c++) {
			Stack<Character> stack = new Stack<>();
			
			for(int r = 0; r < 12; r++) {
				if(map[r][c] != '.') {
					stack.add(map[r][c]);
					map[r][c] = '.';
				}
			}
			
			int size = stack.size();
			
			for(int r = 11; r > 11 - size; r--) {
				map[r][c] = stack.pop();
			}
		}
	}

	private static int bfs(int r, int c) {
		// 주변에 자기랑 같은 색인지 확인하고 4개 이상이면 터치고 아래로 옮기기
		
		Queue<Point> queue = new LinkedList<>();
		store = new LinkedList<>();
		
		boolean v[][] = new boolean[12][6];
		
		queue.add(new Point(r, c));
		store.add(new Point(r, c));
		
		v[r][c] = true;
		
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			for(int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				if(!check(nr, nc)) continue;
				if(map[nr][nc] == map[r][c] && !v[nr][nc]) {
					v[nr][nc] = true;
					queue.add(new Point(nr, nc));
					store.add(new Point(nr, nc));
				}
			}
		}
		
		return store.size();
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < 12 && nc >= 0 && nc < 6;
	}
}
