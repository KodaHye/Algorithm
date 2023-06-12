import java.io.*;
import java.util.*;

/*
 * 움직이는 미로 탈출
 */
public class BOJ16954 {
	static class Point {
		int r, c;
		char ch;
		
		public Point(int r, int c, char ch) {
			this.r = r;
			this.c = c;
			this.ch = ch;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char map[][];
	static int result;
	static int dr[] = {-1, -1, -1, 0, 1, 1, 1, 0, 0};
	static int dc[] = {-1, 0, 1, 1, 1, 0, -1, -1, 0};
	static ArrayList<Point> list;
	
	public static void main(String[] args) throws Exception {
		map = new char[8][8];
		
		list = new ArrayList<>();
		
		for (int r = 0; r < map.length; r++) {
			String input = br.readLine();
			
			for (int c = 0; c < map[0].length; c++) {
				map[r][c] = input.charAt(c);
			}
		}
		
		for(int r = 7; r >= 0; r--) {
			for (int c = 0; c < 8; c++) {
				if(map[r][c] == '#') list.add(new Point(r, c, map[r][c]));
			}
		}
		// 욱제 캐릭터 생성
		map[7][0] = 'O';
		
		// 욱제의 캐릭터가 먼저 이동하고, 그 다음 벽이 이동
		bfs();
		
		System.out.println(result);
	}

	private static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		boolean v[][] = new boolean[8][8];
		
		// 욱제 상태 queue에 추가
		queue.add(new Point(7, 0, map[7][0]));
		v[7][0] = true;
		
		for (int i = 0; i < list.size(); i++) queue.add(new Point(list.get(i).r, list.get(i).c, list.get(i).ch));

		while(!queue.isEmpty()) {
			if(!checkMap()) break; // 욱제의 캐릭터가 없으면 그만
			
			Point current = queue.poll();
			if(current.ch == 'O' && current.r == 0 && current.c == 7) {
				result = 1;
				break;
			}
			// 욱제일 경우
			if(current.ch == 'O') {
				v[current.r][current.c] = false;
				
				for (int d = 0; d < 9; d++) {
					int nr = current.r + dr[d];
					int nc = current.c + dc[d];
					
					if(!check(nr, nc)) continue;
					if(map[nr][nc] != '#' && !v[nr][nc] && map[current.r][current.c] != '#') {
						map[nr][nc] = 'O';
						v[nr][nc] = true;
						queue.add(new Point(nr, nc, current.ch));
					}
				}
//				print(map);
			}
			
			// 벽일 경우 아래로 내리기
			if(current.ch == '#') {
				int nr = current.r + dr[5];
				int nc = current.c + dc[5];
				
				map[current.r][current.c] = '.';
				if(!check(nr, nc)) continue;
				queue.add(new Point(nr, nc, current.ch));
				map[nr][nc] = '#';
			}
		}
	}

	private static boolean checkMap() {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(map[r][c] == 'O') return true;
			}
		}
		return false;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < 8 && nc >= 0 && nc < 8;
	}

	private static void print(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
}
