import java.io.*;
import java.util.*;

public class 꼬리잡기놀이 {
	
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c= c;
		}
		@Override
		public String toString() {
			return "(r: " + r + ", " + c + ")";
		}
		
		public void update(Point p) {
			this.r = p.r;
			this.c = p.c;
		}
	}
	
	static class Team {
		ArrayList<Person> list;
		boolean forwardTail;
		
		public Team(ArrayList<Person> list) {
			this.list = list;
		}
	}
	static class Person {
		Point p;
		int number;
		
		public Person(Point p, int number) {
			this.p = p;
			this.number = number;
		}
	}
	static int N, M, K;
	static int[][] map, group;
	static Team[] teams;
	
	public static void main(String[] args) throws Exception {
		initInput();
		System.out.println(solution());
	}
	
	static int solution() {
		int result = 0;
		int turn = 0;
		
		while(turn < K) {
			moveTeams();
			result += throwBall(turn);
			
			turn++;
		}
		
		return result;
	}
	
	// 사람이 있으면 최초에 만나게 되는 사람의 제곱만큼 접수 얻기 (머리 사람을 기준으로 k번째 - forwardTail 확인해야됨)
	// 획득한 팀의 경우는 머리 사람과 꼬리 사람이 바뀜
	static int throwBall(int turn) {
		turn %= (4 * N);
		
		int r = 0, c = 0;
		int groupIdx = 0;
		
		if(turn < N) {
			
			turn %= N;
			
			r = turn; c = 0; // 왼 -> 오
			
			for(int i = 0; i < N; i++) {
				int tc = c + i;
				
				if(map[r][tc] > 0 && map[r][tc] < 4) {
					groupIdx = group[r][tc];
					c = tc;
					break;
				}
			}
			
		} else if(turn < 2 * N) {
			
			turn %= N;
			
			r = N - 1; c = turn; // 하 -> 상
			
			for(int i = 0; i < N; i++) {
				int tr = r - i;
				
				if(map[tr][c] > 0 && map[tr][c] < 4) {
					groupIdx = group[tr][c];
					r = tr;
					break;
				}
			}
			
		} else if(turn < 3 * N) {
			
			turn %= N;
			
			r = N - turn - 1; c = N - 1; // 오 -> 왼
			
			for(int i = 0; i < N; i++) {
				int tc = c - i;
				
				if(map[r][tc] > 0 && map[r][tc] < 4) {
					groupIdx = group[r][tc];
					c = tc;
					break;
				}
			}
		} else {
			
			turn %= N;
			
			r = 0; c = N - turn - 1; // 상 -> 하
			
			for(int i = 0; i < N; i++) {
				int tr = r + i;
				
				if(map[tr][c] > 0 && map[tr][c] < 4) {
					groupIdx = group[tr][c];
					r = tr;
					break;
				}
			}
		}

		if(groupIdx == 0) return 0;
		
		int result = 0;
		for(int i = 0; i < teams[groupIdx].list.size(); i++) {
			Person p = teams[groupIdx].list.get(i);
			
			if(p.p.r != r || p.p.c != c) continue;
			
			int order = !teams[groupIdx].forwardTail ? i + 1 : (teams[groupIdx].list.size() - i);
			result = order * order;
			teams[groupIdx].forwardTail = !teams[groupIdx].forwardTail;
			
			break;
		}
		
		return result;
	}
	
	static void moveTeams() {
		for(Team t: teams) {
			if(t == null) continue;
			move(t);
		}
	}
	
	static void move(Team t) {
		// 꼬리사람이 맨 앞에서 가고 있을 때
		if(t.forwardTail) {
			// 맨 앞 사람부터 뒤 사람이 있는 쪽으로 가기
			for(int i = 0; i < t.list.size() - 1; i++) {
				if(i == 0) map[t.list.get(i).p.r][t.list.get(i).p.c] = 4;
				
				t.list.get(i).p.update(t.list.get(i + 1).p);
				updateMap(t.list.get(i).p, t.list.get(i).number);
			}
		} 
		
		// 머리사람이 맨 앞에서 가고 있을 때
		else {
			
			// 맨 뒤에사람부터 앞 사람이 있는 쪽으로 가기
			for(int i = t.list.size() - 1; i > 0; i--) {
				if(i == t.list.size() - 1) map[t.list.get(i).p.r][t.list.get(i).p.c] = 4;
				
				t.list.get(i).p.update(t.list.get(i - 1).p);
				updateMap(t.list.get(i).p, t.list.get(i).number);
			}
		}
		
		// 시작 사람 옮기기
		int startIdx = t.forwardTail ? t.list.size() - 1  : 0;
		Person start  = t.list.get(startIdx);
		
		for(int d = 0; d < 4; d++) {
			int nr = start.p.r + dr[d];
			int nc = start.p.c + dc[d];
			
			if(!check(nr, nc) || map[nr][nc] != 4 || group[nr][nc] != group[start.p.r][start.p.c]) continue;
			
			start.p.update(new Point(nr, nc));
			updateMap(start.p, start.number);
			break;
		}
	}
	
	static void updateMap(Point p, int i) {
		map[p.r][p.c] = i;
	}
	
	static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 격자의 크기
		M = Integer.parseInt(st.nextToken()); // 팀의 개수
		K = Integer.parseInt(st.nextToken()); // 라운드 수
		
		map = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		teams = new Team[M + 1];
		setGroup();
	}
	
	static void setGroup() {
		
		int groupIdx = 1;
		group = new int[N][N];
		
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(group[r][c] != 0 || map[r][c] != 1) continue;
				
				group[r][c] = groupIdx;
				
				ArrayList<Person> team = new ArrayList<>(); // 사람으로 이루어진...
				
				
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(check(nr, nc) && map[nr][nc] == 2) {
						dfs(r, c, r, c, d, groupIdx, team);
					}
				}
				
				teams[groupIdx] = new Team(team);
				groupIdx++;
			}
		}
	}
	
	static void dfs(int sr, int sc, int r, int c, int d, int groupIdx, ArrayList<Person> team) {
		
		if(map[r][c] != 4) team.add(new Person(new Point(r, c), map[r][c]));
		group[r][c] = groupIdx;
		
		int nr = r + dr[d];
		int nc = c + dc[d];

		if(!check(nr, nc) || map[nr][nc] == 0 || group[nr][nc] != 0) {
			
			for(int dd = 0; dd < 4; dd++) {
				
				nr = r + dr[dd];
				nc = c + dc[dd];
				
				if(!check(nr, nc) || map[nr][nc] == 0 || group[nr][nc] != 0) continue;
				dfs(sr, sc, nr, nc, dd, groupIdx, team);
			}
			
		} else {
			dfs(sr, sc, nr, nc, d, groupIdx, team);
		}
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
	
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
}
