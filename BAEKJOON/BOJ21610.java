import java.io.*;
import java.util.*;

/*
 * 마법사 상어와 비바라기
 */

public class BOJ21610 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 	static StringTokenizer st;
 	static int N, M, map[][], order[][], result;
 	static int dr[] = {0, 0, -1, -1, -1, 0, 1, 1, 1};
 	static int dc[] = {0, -1, -1, 0, 1, 1, 1, 0, -1};
 	static ArrayList<Point> cloud, nextCloud;
 	
 	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		cloud = new ArrayList<>();
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		order = new int[M][2];
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			
			int d = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());
			
			order[m][0] = d;
			order[m][1] = i;
		}
		
		// 초기 구름 생성
		for(int r = N - 2; r < N; r++) {
			for (int c = 0; c < 2; c++) {
				cloud.add(new Point(r, c));
			}
		}
		
		int i = 0;
		
		while(M-- > 0) {
			
			move(order[i]); // 구름 이동
			rain(); // 구름이 있는 칸에 비 내리기
			rainPlus(); // 대각선 방향 물이 있는 개수 만큼 +
			updateCloud(); // 구름이 있던 칸을 제외한 나머지 구름 업데이트
			
			i++;
		}
		
		sum();
		
		System.out.println(result);
	}

	private static void sum() {
 		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				result += map[r][c];
			}
		}
	}

	private static void updateCloud() {
 		cloud = new ArrayList<>();
 		
 		boolean checkMap[][] = new boolean[N][N];
 		
 		for(int i = 0; i < nextCloud.size(); i++) {
 			checkMap[nextCloud.get(i).r][nextCloud.get(i).c] = true;
 		}
 		
 		for(int r = 0; r < N; r++) {
 			for(int c = 0; c < N; c++) {
 				if(!checkMap[r][c] && map[r][c] >= 2) {
 					cloud.add(new Point(r, c));
 					map[r][c] -= 2;
 				}
 			}
 		}
	}

	static int d[] = {2, 4, 6, 8};
 	
	private static void rainPlus() {
		
		for(int i = 0; i < nextCloud.size(); i++) {
			int count = 0;
			
			for(int nd = 0; nd < d.length; nd++) {
				int nr = nextCloud.get(i).r + dr[d[nd]];
				int nc = nextCloud.get(i).c + dc[d[nd]];
				
				if(!check(nr, nc)) continue;
				if(map[nr][nc] > 0) count++;
			}
			
			map[nextCloud.get(i).r][nextCloud.get(i).c] += count;
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

	private static void rain() {
		for(int i = 0; i < nextCloud.size(); i++) {
			Point current = nextCloud.get(i);
			
			int r = current.r;
			int c = current.c;
			
			map[r][c]++;
		}
	}

	private static void move(int[] order) {
		nextCloud = new ArrayList<>();
		
		// order[0]: d(방향), order[1]: i(거리)
		for(int i = 0; i < cloud.size(); i++) {
			Point current = cloud.get(i);
			
			int nr = (current.r + dr[order[0]] * order[1]) % N;
			int nc = (current.c + dc[order[0]] * order[1]) % N;

			if(nr < 0) nr += N;
			if(nc < 0) nc += N;
			
			nextCloud.add(new Point(nr, nc));
		}
	}
}
