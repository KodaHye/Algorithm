import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 상범 빌딩
 * 동, 서, 남, 북, 상, 하
 * 입력
 * - L: 층 수, R: 행, C: 열
 */
public class BOJ6593 {
	static class Point {
		int l, r, c, d;
		public Point(int l, int r, int c, int d) {
			super();
			this.l = l;
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int L, R, C;
	static char map[][][];
	static boolean v[][][];
	static int time;
	
	public static void main(String[] args) throws Exception {
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			time = 0;
			
			if(L == 0 && R == 0 && C == 0) break;
			
			map = new char[L][R][C];
			v = new boolean[L][R][C];
			
			for (int l = 0; l < map.length; l++) {
				for (int r = 0; r < map[l].length; r++) {
					String input = br.readLine();
					for (int c = 0; c < map[l][r].length; c++) {
						map[l][r][c] = input.charAt(c);
					}
				}
				String input = br.readLine();
			}
			
			
			for(int l = 0; l < map.length; l++) {
				for (int r = 0; r < map[l].length; r++) {
					for (int c = 0; c < map[l][r].length; c++) {
						if(map[l][r][c] == 'S') {
							bfs(new Point(l, r, c, 0));
						}

					}
				}
			}
			
			String output = "";
			if(time != 0) {
				output = output + "Escaped in " + time + " minute(s).";
				sb.append(output + "\n");
			} else sb.append("Trapped!" + "\n");
		}
		System.out.println(sb);
	}

	// 동, 서, 남, 북, 상, 하
	static int dl[] = {0, 0, 0, 0, -1, 1};
	static int dr[] = {1, -1, 0, 0, 0, 0};
	static int dc[] = {0, 0, -1, 1, 0, 0};
	private static boolean bfs(Point point) {
		Queue<Point> queue = new LinkedList<>();
		
		queue.offer(point);
		v[point.l][point.r][point.c] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i = 0; i < 6; i++) {
				int nl = p.l + dl[i];
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(!check(nl, nr, nc)) continue;
				if(!v[nl][nr][nc] && map[nl][nr][nc] != '#') {
					if(map[nl][nr][nc] == 'E') {
						time = p.d + 1;
						return true;
					}

					v[nl][nr][nc] = true;
					queue.add(new Point(nl, nr, nc, p.d + 1));
				}
			}
		}
		return false;
	}

	private static boolean check(int nl, int nr, int nc) {
		return nl >= 0 && nl < L && nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	private static void print(char[][][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.println(Arrays.toString(map[i][j]));
			}System.out.println();
		}
	}
}
