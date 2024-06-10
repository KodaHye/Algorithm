import java.io.*;
import java.util.*;

/*
 * 바이러스 실험
 * remove 대신 새로운 배열을 만들어 시간을 줄이는게 중요함!
 */

public class 바이러스실험 {
	static int dr[] = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int dc[] = {-1, 0, 1, 1, 1, 0, -1, -1};
	static int N, M, K, map[][], nextMap[][], delta[][];
	static ArrayList<Integer> virus[][], nextVirus[][];
	
	public static void main(String[] args) throws Exception {
		initInput();
		solve();
		printVirus();
	}

	private static void printVirus() {
		int result = 0;
		
		for(int r = 1; r < virus.length; r++) {
			for(int c = 1; c < virus[0].length; c++) {
				result += virus[r][c].size();
			}
		}
		
		System.out.println(result);
	}

	private static void solve() {
		
		while(K-- > 0) {
			
			initMapFunc();
			for(int r = 1; r < virus.length; r++) {
				for(int c = 1; c < virus[r].length; c++) {
					Collections.sort(virus[r][c]);
					
					for(int k = 0; k < virus[r][c].size(); k++) {
						int age = virus[r][c].get(k);
						
						if(map[r][c] >= age) {
							map[r][c] -= age;
							nextVirus[r][c].add(age + 1);
						} else {
							nextMap[r][c] += age / 2;
						}
					}
					nextMap[r][c] += map[r][c];
				}
			}
			
			for(int r = 1; r < nextVirus.length; r++) {
				for(int c = 1; c < nextVirus[0].length; c++) {
					for(int k = 0; k < nextVirus[r][c].size(); k++) {
						int age = nextVirus[r][c].get(k);
					
						if(age % 5 != 0) continue;
						
						for(int d = 0; d < 8; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							
							if(!check(nr, nc)) continue;
							nextVirus[nr][nc].add(1);
						}
					}
				}
			}

			addMapFunc();
			updateMap();
			
		}
	}

	private static void initMapFunc() {
		for(int r = 1; r < virus.length; r++) {
			for(int c = 1; c < virus[r].length; c++) {
				nextVirus[r][c] = new ArrayList<Integer>();
				nextMap[r][c] = 0;
			}
		}
	}

	private static void updateMap() {
		for(int r = 1; r < virus.length; r++) {
			for(int c = 1; c < virus[0].length; c++) {
				virus[r][c] = new ArrayList<Integer>(nextVirus[r][c]);
				map[r][c] = nextMap[r][c];
			}
		}
		
	}

	private static void addMapFunc() {
		for(int r = 1; r < map.length; r++) {
			for(int c = 1; c < map[0].length; c++) {
				nextMap[r][c] += delta[r][c];
			}
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 1 && nr < map.length && nc >= 1 && nc < map[0].length;
	}

	private static void initInput() throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		virus = new ArrayList[N + 1][N + 1];
		nextVirus = new ArrayList[N + 1][N + 1];
	
		for(int r = 0; r < virus.length; r++) {
			for(int c = 0; c < virus[0].length; c++) {
				virus[r][c] = new ArrayList<Integer>();
				nextVirus[r][c] = new ArrayList<Integer>();
			}
		}
		map = new int[N + 1][N + 1];
		nextMap = new int[N + 1][N + 1];
		delta = new int[N + 1][N + 1];
		
		for(int r = 0; r < map.length; r++) Arrays.fill(map[r], 5);
		for(int r = 1; r < delta.length; r++) {
			st = new StringTokenizer(br.readLine());
			
			for(int c = 1; c < delta[0].length; c++) {
				delta[r][c] += Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int o = Integer.parseInt(st.nextToken());
			
			virus[r][c].add(o);
		}
	}

	private static void printMap() {
		for(int r = 1; r < map.length; r++) {
			for(int c = 1; c < map[0].length; c++) {
				System.out.print(map[r][c] + " ");
			}
			
			System.out.println();
		}
	}
}
