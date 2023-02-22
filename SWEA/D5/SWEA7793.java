package D5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 오! 나의 여신님
 * 악마 공격: 상하좌우 부식하며 확장
 * 여신 공간: 악마 공격으로부터 자유로움
 * 수연: 여신이 있는 곳으로 가야됨
 * 	- 1초에 동, 서, 남, 북으로 이동 가능
 * 악마의 영역에 한 번도 포함하지 않으며 여신에게 가는 최소 시간
 * 입력
 * - T: 테스트 케이스
 * - N, M: map의 정보
 * - S: 현재 수연의 위치
 * - D: 여신 공간
 * - X: 돌의 위치
 * - *: 악마
 */
public class SWEA7793 {
	static class Point {
		int r;
		int c;
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb= new StringBuilder();
	static StringTokenizer st;
	static int T, N, M, time;
	static char map[][];
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static ArrayList<Point> list;
	static boolean flag;
	static Point suPoint;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case < T + 1; test_case++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char[N][M];
			list = new ArrayList<>();
			time = 0;
			
			for (int r = 0; r < map.length; r++) {
				String input = br.readLine();
				for (int c = 0; c < map[r].length; c++) {
					map[r][c] = input.charAt(c);
					// list의 악마 좌표 저장
//					if(map[r][c] == '*') list.add(new Point(r, c));
//					if(map[r][c] == 'S') suPoint = new Point(r, c);
				}
			}
			
			for (int r = 0; r < map.length; r++) {
				for (int c = 0; c < map[r].length; c++) {
					// list의 악마 좌표 저장
					if(map[r][c] == '*') func(new Point(r, c));
					if(map[r][c] == 'S') func(new Point(r, c));
					time++;
				}
			}
		}
		System.out.println(sb);
	}

	private static void func(Point suPoint) {
		Queue<Point> queue = new LinkedList<>();
		
		queue.offer(suPoint);
		
		while(!queue.isEmpty()) {
			Point point = queue.poll();

			for(int i = 0; i < 4; i++) {
				int nr = point.r + dr[i];
				int nc = point.c + dc[i];
				
				if(check(nr, nc)  && map[nr][nc] != 'X' && map[nr][nc] != 'D') {
					
					if(map[nr][nc] == 'D') {
						flag = true;
						return;
					}
					map[nr][nc] = 'S';
					queue.add(new Point(nr, nc));
					
				}
			}
			evil();
		}
		

	}

	private static void evil() {
		Queue<Point> que = new LinkedList<>();

		while(!list.isEmpty()) {
			que.offer(list.get(list.size() - 1));
			list.remove(list.size() - 1);
		}

		while(!que.isEmpty()) {
			Point point = que.poll();
			map[point.r][point.c] = '*';
			
			for(int i = 0; i < 4; i++) {

				int nr = point.r + dr[i];
				int nc = point.c + dc[i];
				
				if(check(nr, nc) && map[nr][nc] != 'X' && map[nr][nc] != 'D') {
					// 악마의 영역이 수연이를 포함하는 순간 게임 끝
					map[nr][nc] = '*';
					list.add(new Point(nr, nc));
				}
			}
		}
	}
	private static boolean findSu() {
		boolean isSu = false;
		L: for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(map[i][j] == 'S') {
					isSu = true;
					break L;
				}
			}
		}
		return isSu;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}