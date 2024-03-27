import java.io.*;
import java.util.*;

public class 나무타이쿤 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int map[][], yak[][];
	static int dr[] = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int dc[] = {0, 1, 1, 0, -1, -1, -1, 0, 1};
	static ArrayList<Point> list = new ArrayList<Point>(), tmp = new ArrayList<Point>();;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = stoi(st);
		int m = stoi(st); // 리브로를 키우는 총 년 수
		
		map = new int[n][n];
	
		int result = 0;
		
		for(int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			
			for(int c = 0; c < map[0].length; c++) {
				map[r][c] = stoi(st);
				
				if(r >= map.length - 2 && r < map.length && c >= 0 && c < 2) list.add(new Point(r, c));
			}
		}

		while(m-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int d = stoi(st);
			int p = stoi(st);
			
			move(d, p);
			toeap();
			last();
		}

		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) result += map[r][c];
		}
		
		System.out.println(result);
	}

	private static void last() {
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				boolean flag = true;
				
				for(int k = 0; k < list.size(); k++) {
					if(list.get(k).r == r && list.get(k).c == c) {
						flag = false;
						break;
					}
				}
				
				if(!flag) continue;
				
				if(map[r][c] >= 2) {
					map[r][c] -= 2;
					tmp.add(new Point(r, c));
				}
			}
		}
		
		list.clear();
		list.addAll(tmp);
		tmp.clear();
	}

	private static void toeap() {
		for(Point point: list) {
			// 특수 영양제를 투입한 리브로수의 대각선으로 인접한 방향에 높이가 1 이상인 리브로수가 있는 만큼 높이가 더 성장
			map[point.r][point.c]++;
		}
		
		for(Point point: list) {
			int cnt = 0;
			
			for(int d = 2; d <= 8; d += 2) {
				int nr = point.r + dr[d];
				int nc = point.c + dc[d];
				
				if(!check(nr, nc)) continue;
				if(map[nr][nc] >= 1) cnt++;
			}
			
			map[point.r][point.c] += cnt;
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length;
	}

	private static void move(int d, int p) {
		// 영양제 위치 변경
		for(Point point: list) {
			point.r += dr[d] * p;
			point.c += dc[d] * p;
			
			point.r %= map.length;
			point.c %= map.length;
			
			if(point.r < 0) point.r += map.length;
			if(point.c < 0) point.c += map.length;
		}
	}

	private static int stoi(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
