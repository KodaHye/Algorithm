import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 드래곤 커브
 */
public class BOJ15685 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, r, c, d, g, count, map[][];
	static ArrayList<Point> list, dragonList;
	static int dr[] = {0, -1, 0, 1};
	static int dc[] = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {

		N = Integer.parseInt(br.readLine()); // 드래곤 커브의 개수
		
		map = new int[101][101];

		for (int t = 0; t < N; t++) {
			st = new StringTokenizer(br.readLine());
			
			c = Integer.parseInt(st.nextToken()); // 드래곤 커브 시작 x
			r = Integer.parseInt(st.nextToken()); // 드래곤 커브 시작 y
			d = Integer.parseInt(st.nextToken()); // 시작 방향
			g = Integer.parseInt(st.nextToken()); // 세대
			
			dragonCurve(r, c, d, g);
		}

		check();
		
		System.out.println(count);
	}

	private static void dragonCurve(int r, int c, int d, int g) {
		list = new ArrayList<>();
		
		map[r][c]++;
		
		int nr = r + dr[d];
		int nc = c + dc[d];
		
		map[nr][nc]++;
		
		// 처음 직선
		list.add(new Point(r, c));
		list.add(new Point(nr, nc));
		
		while(g-- > 0) {
			dragonList = new ArrayList<>();
			
			// 처음에 직선 그려야 됨
			// 끝점 기준으로 90도 회전
			// 일단 List에 있는거 있어야 되고, List에 있는거 돌린거 있어야 됨
			for (int i = 0; i < list.size(); i++) {
				Point tmp = list.get(i);
				dragonList.add(tmp);
			}
			
			Point point = list.get(list.size() - 1); // 회전의 기준이 되는 점
			
			for (int i = list.size() - 2; i >= 0; i--) {
				Point tmp = list.get(i);
				dragonList.add(new Point(point.r - (point.c - tmp.c), point.c + (point.r - tmp.r)));
			}

			for (int i = 0; i < dragonList.size(); i++) map[dragonList.get(i).r][dragonList.get(i).c]++;
			
			list = dragonList;
		}
	}
	private static void check() {
		for(int i = 0; i < map.length - 2 + 1; i++) {
			for(int j = 0; j < map[0].length - 2 + 1; j++) {
				if(map[i][j] > 0 && map[i + 1][j] > 0 && map[i][j + 1] > 0 && map[i + 1][j + 1] > 0) count++;
			}
		}
	}
}
