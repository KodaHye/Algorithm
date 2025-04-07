import java.io.*;
import java.util.*;

public class 포탑부수기2 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		int sumPoint() {
			return this.r + this.c;
		}
	}
	
	static class Top implements Comparable<Top> {
		Point p;
		int power, time;
		
		public Top(Point p, int power) {
			this.p = p;
			this.power = power;
		}
		
		@Override
		public int compareTo(Top o) {
			
			int rcSum = this.p.sumPoint();
			int orcSum = o.p.sumPoint();
			
			if(this.power != o.power) return Integer.compare(this.power, o.power); // 공격력이 작은
			if(this.time != o.time) return -1 * Integer.compare(this.time, o.time); // 최근에 공격한 포탑
			if(rcSum != orcSum) return -1 * Integer.compare(rcSum, orcSum); // 큰 순서
			return -1 * Integer.compare(this.p.c, o.p.c); // 열 값이 큰 순서
		}
		
		@Override
		public String toString() {
			return "[power=" + power + ", time=" + time + "]";
		}

		// 공격력이 N + M만큼 증가
		public void handicap() {
			this.power += N + M;
		}
	}

	static ArrayList<Top> tops = new ArrayList<>();
	static int N, M, K;
	static Top[][] map;
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0}, dc = {-1, 0, 1, 1, 1, 0, -1, -1};
	static int[] rdlu = {3, 5, 7, 1};
	
	public static void main(String[] args) throws Exception {
		initInput();
		System.out.println(solution());
	}
	
	static int solution() {
		
		// 탑의 개수가 2개 이상이어야 됨
		int time = 1;
		while(time < K + 1) {
			
			// 포탑 정비를 위한 배열
			boolean[][] markAttack = new boolean[N][M];
			
			Collections.sort(tops); // 정렬해주기
			
			Top weaker = tops.get(0); // 가장 약한 탑

			Top stronger = tops.get(tops.size() - 1); // 가장 강한 탑
			
			// 자신을 제외한 가장 강한 포탑이어야 됨
			// 조건을 만족하지 않는다면 탈출하기
			if(weaker == stronger) break;
			
			weaker.time = time;
			weaker.handicap(); // 핸디캡 적용 (가장 강한 탑을 찾은 뒤, 적용해야 됨)

			if(!laser(weaker, stronger, markAttack)) bomb(weaker, stronger, markAttack);
 			
 			reset(markAttack);
 			
			ArrayList<Top> tmp = new ArrayList<>();
			
			// 공격력이 0이하가 된 포탑은 부서짐
			for(Top t: tops) {
				if(t.power <= 0) continue;
				tmp.add(t);
			}
			
			tops = tmp;
			
			time++;
		}
		
		return getMaxPower();
	}
	
	static int getMaxPower() {
		int result = 0;
		
		for(Top t: tops) result = Math.max(result, t.power);
		
		return result;
	}
	static void reset(boolean[][] markAttack) {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c].power <= 0 || markAttack[r][c]) continue;
				
				map[r][c].power++;
			}
		}
	}
	
	// 공격 대상에 포탄 던지기
	static void bomb(Top w, Top s, boolean[][] markAttack) {
		markAttack[w.p.r][w.p.c] = true;
		markAttack[s.p.r][s.p.c] = true;

		s.power -= w.power;
		
		for(int d = 0; d < 8; d++) {
			int nr = (s.p.r + dr[d] + N) % N;
			int nc = (s.p.c + dc[d] + M) % M;
			
			// 이미 부서진 탑이거나, 공격자 탑이라면 continue;
			if(map[nr][nc].power <= 0 || (w.p.r == nr && w.p.c == nc)) continue; 
			
			markAttack[nr][nc] = true;
			map[nr][nc].power -= (w.power / 2);
		}
	}
	
	// 우, 하, 좌, 상 4개의 방향으로 움직이기 (O)
	// 부서진 포탑이 있는 위치는 지날 수 없음 (map[r][c] <= 0) (O)
	// 가장자리에서 막인 방향으로 진행하고자 한다면, 반대편으로 나옴 (O)
	// 최단경로 -> 공격대상
	// removeSet에는 map[r][c]가 0이 되어서 없어져야 되는 탑 정보 저장하기 (O)
	static boolean laser(Top w, Top s, boolean[][] markAttack) {
		
		int[][] mark = new int[N][M];
		boolean[][] v = new boolean[N][M];
		
		Queue<Point> q = new ArrayDeque<>();
		q.add(w.p);
		
		v[w.p.r][w.p.c] = true; // 공격자부로부터 시작
		markAttack[w.p.r][w.p.c] = true;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			if(current.r == s.p.r && current.c == s.p.c) {
				
				int cr = current.r, cc = current.c;
				
				while(cr != w.p.r || cc != w.p.c) {
					
					int cp = (cr == s.p.r && cc == s.p.c) ? w.power : (w.power / 2);
					
					markAttack[cr][cc] = true;
					map[cr][cc].power -= cp;
					
					int pos = mark[cr][cc];
					
					cr = pos / M;
					cc = pos % M;
				}
				
				return true;
			}
			
			for(int d: rdlu) {
				// 가장자리에서 막힌 방향으로 진행 처리
				int nr = (current.r + dr[d] + N) % N;
				int nc = (current.c + dc[d] + M) % M;
				
				if(v[nr][nc] || map[nr][nc].power <= 0) continue;
				
				v[nr][nc] = true;
				mark[nr][nc] = current.r * M + current.c;
				q.add(new Point(nr, nc));
			}
		}
		
		return false;
	}
	static void printTopInfo() {
		for(Top top: tops) {
			System.out.println(top.toString());
		}
	}
	
	static void initInput() throws Exception {
		
		System.setIn(new FileInputStream("./input/포탑부수기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); // 턴의 수
		
		map = new Top[N][M]; // 포탑의 공격력을 저장
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			
			for(int c = 0; c < M; c++) {
				int power = Integer.parseInt(st.nextToken());
				
				map[r][c] = new Top(new Point(r, c), power);
				
				if(power == 0) continue;
				tops.add(map[r][c]);
			}
		}
	}
}
