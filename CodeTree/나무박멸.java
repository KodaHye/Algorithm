import java.io.*;
import java.util.*;

public class 나무박멸 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static class Info {
		int tree, jechoje;
		boolean canGrow;

		public Info(int tree) {
			this.tree = tree;
		}
	}
	static Info map[][];
	static int N, M, K, C, totalTree, result;
	static int dr[] = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int dc[] = {-1, 0, 1, 1, 1, 0, -1, -1};
	static Queue<Point> queue = new LinkedList<Point>();
	static int dir[] = new int[8], tmp[] = new int[8];
	
	public static void main(String[] args) throws Exception {
		init();
		
		while(M-- > 0) {
			grow();
			bunsik();
			jechoje();
			reset();
		}
		
		System.out.println(result);
	}

	private static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st); // 격자의 크기
		M = stoi(st); // 년 수
		K = stoi(st); // 확산 범위
		C = stoi(st); // 제초제가 남아있는 년수
		
		map = new Info[N][N];	
		
		for(int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < map[0].length; c++) {
				map[r][c] = new Info(stoi(st));
			}
		}
	}

	private static void jechoje() {
		
		Point jechoje = new Point(-1, -1);
		
		int max = Integer.MIN_VALUE;
		
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				if(map[r][c].jechoje > 0) map[r][c].jechoje--;
				if(map[r][c].tree < 0) continue;
				
				int cnt = map[r][c].tree;
				
				if(map[r][c].tree == 0) {
					if(max < cnt) Arrays.fill(dir, 0);
				} else {
					for(int d = 0; d < 8; d += 2) {
						int len = 0;
						int nr = r;
						int nc = c;
						
						while(true) {
							nr += dr[d];
							nc += dc[d];
							len += 1;
						
							if(!check(nr, nc)) {
								len--;
								break;
							}
							if(map[nr][nc].tree == -1 || map[nr][nc].tree == 0) {
								break;
							}
							if(map[nr][nc].tree > 0) cnt += map[nr][nc].tree;
							if(len == K) break;
						}
						tmp[d] = len;
					}
				}
				
				if(max < cnt) {
					max = cnt;
					dir = Arrays.copyOf(tmp, tmp.length);
					jechoje.r = r;
					jechoje.c = c;
				}
			}
		}
	
		result += map[jechoje.r][jechoje.c].tree;
		
		map[jechoje.r][jechoje.c].tree = 0;
		map[jechoje.r][jechoje.c].jechoje = C;
		
		for(int d = 0; d < 8; d += 2) {
			int len = 0; 

			int nr = jechoje.r;
			int nc = jechoje.c;

			while(len++ < dir[d]) {
				nr += dr[d];
				nc += dc[d];
				
				if(!check(nr, nc)) continue;
				
				if(map[nr][nc].tree == -1) break;
				if(map[nr][nc].tree >= 0) {
					result += map[nr][nc].tree;
					map[nr][nc].tree = 0;
					map[nr][nc].jechoje = C;
				}
			}
		}
	}

	private static void reset() {
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				map[r][c].canGrow = false;
			}
		}
	}

	private static void bunsik() {
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				
				// 기존에 나무가 있는 곳에서 번식
				if(map[r][c].tree <= 0) continue;
				
				for(int d = 1; d < 8; d+= 2) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(!check(nr, nc)) continue;
					
					// 번식이 가능한 곳
					if(map[nr][nc].jechoje == 0 && map[nr][nc].tree == 0) {
						map[nr][nc].canGrow = true;
					}
				}
				
				queue.add(new Point(r, c));
			}
		}

		while(!queue.isEmpty()) {
			Point current = queue.poll();
			
			int cnt = 0;
			
			for(int d = 1; d < 8; d += 2) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				if(!check(nr, nc)) continue;
				if(map[nr][nc].canGrow) cnt++;
			}
			
			for(int d = 1; d < 8; d += 2) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				if(!check(nr, nc)) continue;
				if(map[nr][nc].canGrow) map[nr][nc].tree += map[current.r][current.c].tree / cnt;
			}
		}
	}

	private static void grow() {
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				// 나무가 없는 칸
				if(map[r][c].tree <= 0) continue;
				int cnt = 0;
				
				for(int d = 1; d < 8; d += 2) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(!check(nr, nc)) continue;
					// 인접한 칸 중에서 나무가 있는 칸 수만큼 성장
					if(map[nr][nc].tree > 0) cnt++;
				}
				
				map[r][c].tree += cnt;
			}
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length;
	}

	private static int stoi(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
