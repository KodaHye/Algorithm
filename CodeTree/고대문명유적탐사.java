import java.io.*;
import java.util.*;

/*
 * 배열 참조 & 복사 정신 차리고 똑바로 하기!
 * 우선 순위 잘 설정하기
 * 단계별로 구현 잘 하기!!
 */

public class 고대문명유적탐사 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int[][] map;
	static int[] arr;
	static int K, M, idx;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	static StringBuilder sb = new StringBuilder();
	
	static void solution() {
		while(K-- > 0) {
			int[][] copy = new int[5][5];
			int result = 0;
			int maxScore = 0;
	
			boolean rotateFlag = false;
			
			for(int d = 1; d < 4; d++) {
				for(int c = 1; c < 4; c++) {
					for(int r = 1; r < 4; r++) {
						int rotateMap[][] = rotateMap(r, c, d);
						
						int score = getScore(rotateMap);
						if(maxScore < score) {
							
							maxScore = score;
							copy = rotateMap;
							rotateFlag = true;
						}
					}
				}
			}
			
			if(!rotateFlag) break;
			map = copy;
			
			result = getScore();
			for(int r = 0; r < map.length; r++) {
				System.out.println(Arrays.toString(map[r]));
			}
			
			while(true) {
				fillMap();
//				System.out.println("fillMap 하고 나서");
				int newScore = getScore();
				
//				System.out.println("getScore 이후");
				if(newScore == 0) break;
				result += newScore;
				
				System.out.println();
			}
			
			sb.append(result + " "); 
		}
		
		System.out.println(sb);
	}
	
	static int getScore() {
		int result = 0;
		
		boolean[][] v = new boolean[5][5];
		
		for(int r = 0; r < 5; r++) {
			for(int c = 0; c < 5; c++) {
				
				if(v[r][c]) continue;
				Deque<Point> q = new ArrayDeque<Point>();
				Deque<Point> tmp = new ArrayDeque<Point>();
				
				int cnt = 1;
				q.add(new Point(r, c));
				tmp.add(new Point(r, c));
				
				v[r][c] = true;
				
				while(!q.isEmpty()) {
					Point current = q.poll();
		
					for(int d = 0; d < 4; d++) {
						int nr = current.r + dr[d];
						int nc = current.c + dc[d];
						
						if(!check(nr, nc) || v[nr][nc] || map[nr][nc] != map[r][c]) continue;
						cnt++;
						q.add(new Point(nr, nc));
						tmp.add(new Point(nr, nc));
						
						v[nr][nc] = true;
					}
				}
				
				if(cnt >= 3) {
					while(!tmp.isEmpty()) {
						Point current = tmp.poll();
						map[current.r][current.c] = 0;
					}
					
					result += cnt;
				}
			}
		}
		
		return result;
	}
	
	static void fillMap() {
		for(int c = 0; c < 5; c++) {
			for(int r = 4; r >= 0; r--) {
				if(map[r][c] != 0) continue;
				map[r][c] = arr[idx];
				idx = (idx + 1) % arr.length;
			}
		}
	}
	
	static int getScore(int[][] rotateMap) {
		int score = 0;
		boolean[][] v = new boolean[5][5];
		Deque<Point> q = new ArrayDeque<Point>();
		
		for(int r = 0; r < 5; r++) {
			for(int c = 0; c < 5; c++) {
				if(v[r][c]) continue;
				
				int tmp = 1;
				
				q.add(new Point(r, c));
				v[r][c] = true;
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					for(int d = 0; d < 4; d++) {
						int nr = current.r + dr[d];
						int nc = current.c + dc[d];
						
						if(!check(nr, nc) || v[nr][nc] || rotateMap[nr][nc] != rotateMap[r][c]) continue;
						v[nr][nc] = true;
						q.add(new Point(nr, nc));
						tmp++;
					}
				}
				
				if(tmp > 2) score += tmp;
			}
		}
		return score;
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < 5 && c >= 0 && c < 5;
	}
	
	static int[][] rotateMap(int r, int c, int d) {
		int[][] rotateMap = new int[5][5];
		for(int i = 0; i < 5; i++) rotateMap[i] = Arrays.copyOf(map[i], 5);
		
		for(int dr = -1; dr < 2; dr++) {
			for(int dc = -1; dc < 2; dc++) {
				if(d == 1) rotateMap[r + dc][c - dr] = map[r + dr][c + dc];
				if(d == 2) rotateMap[r - dr][c - dc] = map[r + dr][c + dc];
				if(d == 3) rotateMap[r - dc][c + dr] = map[r + dr][c + dc];
			}
		}
		
		return rotateMap;
	}
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
	}
	
	static void initInput() throws Exception {
//		System.setIn(new FileInputStream("./input/고대문명유적탐사.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
			
		map = new int[5][5];

		K = Integer.parseInt(st.nextToken()); // 탐사 반복 횟수
		M = Integer.parseInt(st.nextToken()); // 벽면에 적힌 유물 조각의 개수
		
		arr = new int[M];
		
		for(int r = 0; r < 5; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < 5; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
	}
}
