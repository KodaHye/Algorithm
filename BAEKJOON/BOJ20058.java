import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 마법사 상어와 파이어스톰
 */
public class BOJ20058 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, Q, map[][], copy[][], block;
	static int dr[] = {1, 0, -1, 0};
	static int dc[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		map = new int[(int) Math.pow(2, N)][(int) Math.pow(2, N)];
		
		for(int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			
			for(int c = 0; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < Q; i++) {
			func(Integer.parseInt(st.nextToken()));
			
			// 얼음이 있는 칸 3개 또는 그 이상 인접해있지 않은 칸은 얼음의 양이 1개 줄어듦
			checkIce();
		}
		
		int result = 0;
		
		for(int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				result += map[r][c];
			}
		}

		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				if(map[r][c] != 0) bfs(r, c);
			}
		}
		
		System.out.println(result + "\n" + block);
	}

	private static void func(int k) {
		int range = (int) Math.pow(2, k);
		
		int copy[][] = new int[map.length][map[0].length];
		
		copy = copyMap(map);
		
		for(int r = 0; r < map.length; r += range) {
			for(int c = 0; c < map[r].length; c += range) {
				// 시작점: (r, c)일 때 가로, 세로가 range인 배열돌리기 실행 !
				rotate(r, c, range);
			}
		}
	}

	private static void checkIce() {

		copy = copyMap(map);
		
		for(int r = 0; r < copy.length; r++) {
			for(int c = 0; c < copy[r].length; c++) {
				int count = 0;
				if(copy[r][c] == 0) continue;
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(!check(nr, nc)) {
						count++;
						continue;
					}
					if(copy[nr][nc] == 0) count++;
				}
				
				if(count >= 2)map[r][c]--;
			}
		}
	}

	private static void bfs(int r, int c) {
		Queue<int []> queue = new LinkedList<>();
		boolean v[][] = new boolean[map.length][map[0].length];
		
		queue.add(new int[] {r, c});
		
		v[r][c] = true;
		
		int count = 1;
		
		while(!queue.isEmpty()) {
			int current[] = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				
				int nr = current[0] + dr[d];
				int nc = current[1] + dc[d];
				if(!check(nr, nc)) continue;
				if(map[nr][nc] != 0 && !v[nr][nc]) {
					count++;
					v[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
			}
		}
		
		block = Math.max(block, count);
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length;
	}

	private static void rotate(int r, int c, int range) {
		for(int i = r; i < r + range; i++) {
			for(int j = c; j < c + range; j++) {
				int nr = r - c + j;
				int nc = range + c - i + r - 1;
				
				map[nr][nc] = copy[i][j];
			}
		}
	}

	private static int[][] copyMap(int[][] map) {
		copy = new int[map.length][map[0].length];
		
		for (int r = 0; r < map.length; r++) {
			copy[r] = Arrays.copyOf(map[r], map[r].length);
		}
		
		return copy;
	}
}
