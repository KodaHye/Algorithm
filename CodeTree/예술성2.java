import java.io.*;
import java.util.*;

public class 예술성2 {
	static class Info {
		int num, cnt;
		
		public Info(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
	static int N;
	static int[][] map;
	static ArrayList<Info> groupInfo;
	static int[][] sameLine;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		initInput();
		System.out.println(solution());
	}
	
	static int solution() {
		int turn = 3;
		
		makeGroup();
		
		int result = getScore();
		
		while(turn-- > 0) {
			rotateCross(); // 십자모양으로 회전
			rotatePartition();
			makeGroup();
			
			result += getScore();
		}
		return result;
	}
	
	static void rotatePartition() {
		rotate(0, 0, N / 2);
		rotate(0, N / 2 + 1, N / 2);
		rotate(N / 2 + 1, 0, N / 2);
		rotate(N / 2 + 1, N / 2 + 1, N / 2);
	}
	
	static void rotate(int sr, int sc, int size) {
		int[][] tmp = new int[size][size];
		
		for(int r = 0; r < size; r++) {
			for(int c = 0; c < size; c++) {
				int or = r;
				int oc = c;
				
				int nr = size - oc - 1 + sr;
				int nc = or + sc;
				
				tmp[r][c] = map[nr][nc];
			}
		}
		
		for(int r = 0; r < size; r++) {
			for(int c = 0; c < size; c++) {
				map[sr + r][sc + c] = tmp[r][c];
			}
		}
	}
	
	static void rotateCross() {
		int[] tmp = new int[N];
		
		for(int c = 0; c < N; c++) tmp[c] = map[N / 2][c];
		
		// 수평 부분 먼저 바꾸기
		for(int r = 0; r < N; r++) map[N / 2][r] = map[r][N / 2];

		// 수직 부분 바꾸기
		for(int r = 0; r < N; r++) map[r][N / 2] = tmp[N - r - 1];
	}
	
	static int getScore() {
		int score = 0;
		
		for(int a = 0; a < groupInfo.size(); a++) {
			for(int b = a + 1; b < groupInfo.size(); b++) {
				if(a == b) continue;
				
				score += (groupInfo.get(a).cnt + groupInfo.get(b).cnt) 
							* groupInfo.get(a).num * groupInfo.get(b).num
							* sameLine[a][b];
				
			}
		}
		
		return score;
	}
	
	static void makeGroup() {
		
		int groupIdx = 0;
		int[][] group = new int[N][N];
		boolean[][] v = new boolean[N][N];
		
		sameLine = new int[30 * 30][30 * 30];
		groupInfo = new ArrayList<>();
		
		Queue<Integer> q = new ArrayDeque<>();
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(v[r][c]) continue;
				
				int num = map[r][c];
				int cnt = 1;
				
				q.add(r * N + c);
				group[r][c] = groupIdx;
				v[r][c] = true;
				
				while(!q.isEmpty()) {
					int current = q.poll();
					int cr = current / N, cc = current % N;
					
					for(int d = 0; d < 4; d++) {
						int nr = cr + dr[d];
						int nc = cc + dc[d];
						
						if(!check(nr, nc)) continue;
						if(v[nr][nc]) {
							int pGroupIdx = group[nr][nc];

							if(pGroupIdx != groupIdx) {
								
								sameLine[groupIdx][pGroupIdx]++;
								sameLine[pGroupIdx][groupIdx]++;
							}
							
							continue;
						}
						
						if(map[nr][nc] != num) continue;
						
						group[nr][nc] = groupIdx;
						v[nr][nc] = true;
						
						q.add(nr * N + nc);
						cnt++;
					}
				}
				
				groupInfo.add(new Info(num, cnt));
				groupIdx++;
			}
		}
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
	static void initInput() throws Exception {
		
		System.setIn(new FileInputStream("./input/예술성.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
