
/*
 * 2시 25분부터 시작
 */

import java.io.*;
import java.util.*;

public class 예술성 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static class Info {
		int num, size;
		public Info(int num, int size) {
			this.num = num;
			this.size = size;
		}
	}
	static int n, m, result, dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
	static int group[][], copy[], copyRec[][];
	static boolean visit[][];
	static int map[][], nearGroupInfo[][];
	static HashMap<Integer, Info> hashMap = new HashMap<Integer, Info>();
	static Queue<Point> queue = new LinkedList<Point>();
	public static void main(String[] args) throws Exception {
		initInput();
		
		int cnt = 4;
		while(cnt-- > 0) {
			step1();
			step2();
			step3();
			clear();
		}
		
		System.out.println(result);
	}

	private static void clear() {
		hashMap.clear();
		
		for(int r = 0; r < n * n + 1; r++) {
			if(r < n) {
				Arrays.fill(visit[r], false);
				Arrays.fill(group[r], 0);
			}
			Arrays.fill(nearGroupInfo[r], 0);
		}
	}

	private static void step3() {
		
		copy = Arrays.copyOf(map[m], map[m].length);
		
		for(int i = 0; i < n; i++) {
			map[m][i] = map[i][m];
		}
		
		for(int i = 0; i < n; i++) {
			map[i][m] = copy[n - i - 1];
		}
		
		for(int r = 0; r < map.length; r += (m + 1)) {
			for(int c = 0; c < map[0].length; c += (m + 1)) {
				roate(r, c, m);
			}
		}
	}
	
	// 시작점 r, c에서 길이가 m 만큼인 정사각형에서 시계방향 90도 회전
	private static void roate(int r, int c, int m) {
		
		for(int rr = 0; rr < m; rr++) {
			for(int cc = 0; cc < m; cc++) {
				copyRec[cc][m - rr - 1] = map[r + rr][c + cc];
			}
		}
		
		for(int rr = 0; rr < m; rr++) {
			for(int cc = 0; cc < m; cc++) {
				map[r + rr][c + cc] = copyRec[rr][cc];
			}
		}
	}

	// 모든 그룹 쌍의 조화로움의 합 (조합)
	private static void step2() {
		for(int i = 1; i < hashMap.size() + 1; i++) {
			for(int j = i + 1; j < hashMap.size() + 1; j++) {
				result += (
						(hashMap.get(i).size + hashMap.get(j).size) * 
						hashMap.get(i).num * hashMap.get(j).num * 
						nearGroupInfo[i][j]);
			}
		}
	}

	private static void step1() {
		int key = 1;

		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				if(visit[r][c]) continue;
				
				hashMap.put(key, new Info(map[r][c], bfs(key, r, c)));
				key++;
			}
		}
	}

	private static int bfs(int key, int r, int c) {
		
		int cnt = 1;
		
		queue.add(new Point(r, c));
		group[r][c] = key;
		visit[r][c] = true;
		
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				if(!isInRange(nr, nc)) continue;
				if(map[nr][nc] != map[r][c]) {
					nearGroupInfo[group[nr][nc]][group[r][c]]++;
					nearGroupInfo[group[r][c]][group[nr][nc]]++;
					continue;
				}
				
				if(visit[nr][nc]) continue;
				queue.add(new Point(nr, nc));
				group[nr][nc] = key;
				visit[nr][nc] = true;
				cnt++;
			}
		}
		
		return cnt;
	}

	private static boolean isInRange(int nr, int nc) {
		return nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length;
	}

	private static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		for(int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		m = n / 2;
		copy = new int[m];
		copyRec = new int[m][m];
		visit = new boolean[n][n];
		group = new int[n][n];
		nearGroupInfo = new int[n * n + 1][n * n + 1];
	}
}
