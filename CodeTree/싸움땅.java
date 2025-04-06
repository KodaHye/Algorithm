import java.io.*;
import java.util.*;

/*
 * 꼼꼼하게 코드짜기..
 */

public class 싸움땅 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
	}
	static class Person {
		Point p;
		int d, s, point, g; // d: 바라보는 방항, s: 플레이어의 초기 능력치
		
		public Person(Point p, int d, int s) {
			this.p = p;
			this.d = d;
			this.s = s;
		}
		
		public int getFightScore() {
			return this.s + this.g;
		}
	
		void move() {
			this.p.r += dr[this.d];
			this.p.c += dc[this.d];
		}
		
		void flipDir() {
			this.d = (this.d + 2) % 4;
		}
		
		// 총을 놓을 때, 현재 가지고 있는 총의 정보도 0으로 초기화해줘야 됨!!
		public void placeGun() {
			if(this.g == 0) return;
			gun[this.p.r][this.p.c].add(this.g);
			this.g = 0;
		}
		
		public void getGun() {
			if(gun[this.p.r][this.p.c].isEmpty()) return;
			g = gun[this.p.r][this.p.c].poll();
		}

		@Override
		public String toString() {
			return "Person [p=" + p + ", d=" + d + ", s=" + s + ", point=" + point + ", g=" + g + "]";
		}
		
	}
	static Person[] players;
	static int N, M, K;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static PriorityQueue<Integer>[][] gun;
	
	public static void main(String[] args) throws Exception {
		initInput();
		System.out.println(solution());
	}
	
	static String solution() {
		
		while(K-- > 0) {
			
			for(int i = 1; i < players.length; i++) {
				Person p = players[i];
				
				// 본인이 향하고 있는 방향대로 한 칸 이동
				int r = p.p.r, c = p.p.c;
				
				map[r][c] = 0; // 현재 map에 아무도 없다고 표시해주기
				
				// 격자를 벗어나는 경우에는 정반대 방향으로 방향 바꾸어서 한 칸 이동
				if(!check(r + dr[p.d], c + dc[p.d])) p.flipDir();
				
				p.move(); // 일단 움직임
				
				// 이동하는 방향(nr, nc)에 플레이어가 없다면
				if(map[p.p.r][p.p.c] == 0) {
					p.placeGun();
					p.getGun(); // 총 놓고, 가장 센 총 획득하기
					map[p.p.r][p.p.c] = i;
				} 
				
				// 이동하려는 방향에 플레이어가 있다면
				else {
					int loser = 0, winner = 0;
					
					int oPlayer = map[p.p.r][p.p.c];
					int oScore = players[oPlayer].getFightScore(); // 초기 능력치와 공격력의 합
					int cScore = p.getFightScore();
					
					if(oScore > cScore) {
						winner = oPlayer;
						loser = i;
					} else if(oScore < cScore) {
						winner = i;
						loser = oPlayer;
					} else { // 같을 경우
						
						if(players[oPlayer].s > p.s) {
							winner = oPlayer;
							loser = i;
						} else {
							winner = i;
							loser = oPlayer;
						}
					}
					
					// 둘 다 총 내려놓기
					players[loser].placeGun();
					players[winner].placeGun();
					
					// 이긴 플레이어는 각 플레이어의 능력치와 가지고 있는 총의 공격력의 합의 차이 만큼 포인트를 획증하게 됨
					players[winner].point += Math.abs(oScore - cScore);
					players[winner].getGun();
					
					// 진 플레이어는 본인이 가지고 있는 총을 해당 격자에 내려놓고 (O)
					// 해당 플레이어가 원래 가지고 있던 방향으로 이동
					// 만약, 이동하려는 칸에 다른 플레이어가 있거나 격자를 벗어난다면 90도씩 회전하면서 빈 칸을 찾을 때까지 반복
					// 그리고 거기에 총이 있다면 총 줍기
					int pr = players[loser].p.r;
					int pc = players[loser].p.c;
					
					// 이런 곳에서 실수하지 않도록
					for(int d = 0; d < 4; d++) {
						int pd = (players[loser].d + d) % 4; // ㅠㅠ,,, 여기에서 왜 loser가 아닌 p의 방향으로 했니,,,
						
						int pnr = pr + dr[pd];
						int pnc = pc + dc[pd];
						
						if(!check(pnr, pnc) || map[pnr][pnc] != 0) continue;
							
						// 빈 칸이 있는 순간 움직이기!
						players[loser].d = pd;
						players[loser].move();
						players[loser].getGun();

						break;
					}
						
					// 이동한 이후, map update해줘야 됨
					map[players[loser].p.r][players[loser].p.c] = loser;
					map[players[winner].p.r][players[winner].p.c] = winner;
				}
			}
			
		}
		return getResult();
	}
	
	static void printMap() {
		for(int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
		
		System.out.println();
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
	
	static String getResult() {
		StringBuilder sb = new StringBuilder();
		
		for(Person p: players) {
			if(p == null) continue;
			sb.append(p.point).append(" ");
		}
		
		return sb.toString();
	}
	
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/싸움땅.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 격자의 크기
		M = Integer.parseInt(st.nextToken()); // 플레이어의 수
		K = Integer.parseInt(st.nextToken()); // 라운드의 수
		
		map = new int[N][N]; // 어떤 사람이 있는지
		
		gun = new PriorityQueue[N][N];

		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			
			for(int c = 0; c < N; c++) {
				gun[r][c] = new PriorityQueue<Integer>(Collections.reverseOrder()); // 내림차순
				int num = Integer.parseInt(st.nextToken());
				
				if(num == 0) continue;
				gun[r][c].add(num);
			}
		}
		
		players = new Person[M + 1];
		
		for(int i = 1; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			players[i] = new Person(new Point(r, c), d, s);
			map[r][c] = i;
		}
	}
}
