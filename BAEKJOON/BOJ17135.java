import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 캐슬 디펜스
 */
public class BOJ17135 {
	static class Point implements Comparable<Point> {
		int r, c, d;

		public Point(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(Point o) {
			if(this.d == o.d) return Integer.compare(this.c, o.c);
			return Integer.compare(this.d, o.d);
		}

	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, D, result, map[][];
	static int sel[];
	static ArrayList<int[]> enemy;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][M];
		enemy = new ArrayList<>();
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 1) enemy.add(new int[] {r, c});
			}
		}
		
		sel = new int[3];
		
		func(0, 0);
		System.out.println(result);
		
	}
	
	private static void func(int k, int idx) {
		if(k == 3) {
			
			enemy = new ArrayList<>();
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					if(map[r][c] == 1) enemy.add(new int[] {r, c});
				}
			}
			
			
			int copyMap[][] = new int[N + 1][M];
			for(int r = 0; r < N + 1; r++) copyMap[r] = Arrays.copyOf(map[r], M);
			
			int attack = 0;
			
			// 반복문 돌리면서 궁수 배치
			while(true) {
				if(checkMap(copyMap)) break;
				HashSet<int []> set = new HashSet<>();
				
				// 세 궁수들이 공격할 수 있는 적 위치 알아내기
				for(int i = 0; i < sel.length; i++) {
					PriorityQueue<Point> queue = new PriorityQueue<>();
					
					// 거리계산하기
					for(int j = 0; j < enemy.size(); j++) {
						int gung[] = new int[] {N, sel[i]};
						int ene[] = enemy.get(j);
						
						int d = Math.abs(gung[0] - ene[0]) + Math.abs(gung[1] - ene[1]);
						if(d <= D) queue.add(new Point(ene[0], ene[1], d));
					}
					if(!queue.isEmpty()) {
						Point firstEne = queue.poll();
						int tmp[] = new int[] {firstEne.r, firstEne.c};
						if(!set.contains(tmp)) set.add(new int[] {tmp[0], tmp[1]});
					}
				}
				

				// set에 있는 적들 공격해서 없애기 !
				for (int[] ene : set) {
					if(copyMap[ene[0]][ene[1]] == 1) {
						copyMap[ene[0]][ene[1]] = 0;
						attack++;
					}
				}
				
				enemy = new ArrayList<>();
				
				for(int r = copyMap.length - 2; r >= 0 ; r--) {
					for(int c = 0; c < copyMap[r].length; c++) {
						if(copyMap[r][c] == 1) {
							
							copyMap[r][c] = 0;
							
							int nr = r + 1;
							int nc = c;
							
							if(!check(nr, nc)) {
								copyMap[nr][nc] = 0;
								continue;
							}
							enemy.add(new int[] {nr, c});
							copyMap[nr][c] = 1;
						}
					}
				}
			}
			
			result = Math.max(attack, result);
			return; 
		}
		
		for(int i = idx; i < M; i++) {
			sel[k] = i;
			func(k + 1, i + 1);
		}
	}

	private static void print(int[][] map) {
		for (int r = 0; r < map.length - 1; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	}
	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
	private static boolean checkMap(int[][] copyMap) {
		for(int r = 0; r < copyMap.length - 1; r++) {
			for(int c = 0; c < copyMap[r].length; c++) {
				if(copyMap[r][c] == 1) return false;
			}
		}
		return true;
	}
}