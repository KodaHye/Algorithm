import java.io.*;
import java.util.*;

public class 루돌프의반란 {
	static class Point {
		int key, r, c;
		public Point(int r, int c, int key) {
			this.r = r;
			this.c = c;
			this.key = key;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
	}
	
	static class Santa {
		Point p;
		int dir, score;
		int l, kizeul;
		
		boolean end;

		public Santa(Point p, int dir, int l) {
			super();
			this.p = p;
			this.dir = dir;
			this.l = l;
		}

		@Override
		public String toString() {
			return "Santa [p=" + p + ", score=" + score + ", kizeul=" + kizeul + ", end=" + end + "]";
		}
	}
	static int rd[][] = {{0, 0}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
//	static int rd[][] = {{0, 0}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}};
	static int sd[][] = {{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static TreeMap<Integer, Santa> santas = new TreeMap<Integer, Santa>(); 
	static Queue<Santa> santaMoveQueue = new LinkedList<Santa>();
	static Queue<Point> queue = new LinkedList<Point>();
	static int N, M, P, C, D, end, map[][];
	static boolean v[][];
	static Point ru;
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("../input/루돌프의반란.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st); // 게임판의 크기
		M = stoi(st); // 게임 턴 수
		P = stoi(st); // 산타의 수
		C = stoi(st); // 루돌프의 힘
		D = stoi(st); // 산타의 힘
		
		st = new StringTokenizer(br.readLine());
		ru = new Point(stoi(st), stoi(st), 0);
		
		map = new int[N + 1][N + 1];
		
		for(int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			
			int key = stoi(st);
			int r = stoi(st);
			int c = stoi(st);
			
			map[r][c] = key;
			santas.put(key, new Santa(new Point(r, c, 0), 0, 0));
		}

		while(M-- > 0) {
//			System.out.println("=====================");
//			System.out.println("루돌프 초기 위치: " + ru.toString());
			moveRu();
//			System.out.println("옮긴 후 루돌프 위치: " + ru.toString());
//			printMap();
			moveSantas();
//			printMap();

			if(end == P) break;
	
			updateScore();
			
//			System.out.println("점수=====");
//			printScore();
		}
		
		printScore();

	}

	private static void printScore() {
		for(Santa s: santas.values()) {
			System.out.print(s.score + " ");
		}		
	}

	private static void updateScore() {
		for(Santa s: santas.values()) {
			if(s.end) continue;
			s.score++;
		}
	}

	private static void moveSantas() {
		
		for(Santa s: santas.values()) {
			s.dir = 0;
			
			if(s.end) continue;
			if(s.kizeul != 0) {
				s.kizeul = ++s.kizeul == 3 ? 0 : s.kizeul;
				continue;
			}
			
			int key = map[s.p.r][s.p.c];
			
			int nDir = 0;
			int nDis = Integer.MAX_VALUE;
			boolean move = false;
			
			for(int d = 1; d < sd.length; d++) {
				int nr = s.p.r + sd[d][0];
				int nc = s.p.c + sd[d][1];
				
				int dis = getDis(nr, nc, ru);
				if(!check(nr, nc) || map[nr][nc] != 0) continue;
				if(dis < nDis) {
					nDir = d;
					nDis = dis;
				}
				
				if(nr == ru.r && nc == ru.c) {
					move = true;
					map[s.p.r][s.p.c] = 0;
					
					nDir += 2;
					nDir = nDir > 4 ? nDir % 4 : nDir;
					
					santas.get(key).score += D;
					moveSanta(key, sd, ru.r, ru.c, nDir, D);
				}
			}
			
			if(!move) {
				map[s.p.r][s.p.c] = 0;
				s.p.r += sd[nDir][0];
				s.p.c += sd[nDir][1];
				
				santas.get(key).p.r = s.p.r;
				santas.get(key).p.c = s.p.c;
				map[s.p.r][s.p.c] = key;
			}
		}
	}

	private static void printMap() {
		for(int r = 0; r < map.length; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	}

	private static void moveRu() {
		int nearSanta = findNearSantaNum();
		
		// 루돌프가 이동할 방향
		int md = 0;
		int minDis = Integer.MAX_VALUE;
		
		// 8방 중 가장 가까워지는 방향으로 
		for(int d = 1; d < rd.length; d++) {
			int nr = ru.r + rd[d][0];
			int nc = ru.c + rd[d][1];
			
			if(!check(nr, nc)) continue;
			int dis = getDis(nr, nc, santas.get(nearSanta).p);
			if(dis < minDis) {
				md = d;
				minDis = dis;
			}
		}
		
		// 루돌프 이동하자
		ru.r += rd[md][0];
		ru.c += rd[md][1];
		
		// 충돌 관련 함수 작성하기
		if(map[ru.r][ru.c] != 0) {
			int key = map[ru.r][ru.c];
			
			// 충돌 후 기절
			santas.get(key).kizeul = 1;
			santas.get(key).score += C;
			
			moveSanta(key, rd, ru.r, ru.c, md, C);
		}
	}

	// moveSanta(rd, md, C);
	private static void moveSanta(int key, int[][] dir, int r, int c, int d, int dis) {
		map[r][c] = 0;
		// 루돌프가 이동해온 방향으로 C 칸 산타 이동!! ====
		
		int nr = r + dis * dir[d][0];
		int nc = c + dis * dir[d][1];
		
		if(!check(nr, nc)) {
			santas.get(key).end = true;
			end++;
		} else {
			if(map[nr][nc] != 0) {
				// 해당 위치 기준으로 밀리는 것이 있는지 확인하고 밀기
				int nnr = nr;
				int nnc = nc;
				
				while(true) {
					if(!check(nnr, nnc) || map[nnr][nnc] == 0) break;
					queue.add(new Point(nnr, nnc, map[nnr][nnc]));
					map[nnr][nnc] = 0;
					nnr += dir[d][0];
					nnc += dir[d][1];
				}
				
				while(!queue.isEmpty()) {
					Point current = queue.poll();
					
					int currentKey = current.key;
					
					int nnnr = current.r + dir[d][0];
					int nnnc = current.c + dir[d][1];
					
					if(!check(nnnr, nnnc)) {
						end++;
						santas.get(currentKey).end = true;
						continue;
					} 
					
					map[nnnr][nnnc] = currentKey;
					santas.get(currentKey).p.r = nnnr;
					santas.get(currentKey).p.c = nnnc;
				}
			} 
			santas.get(key).kizeul = 1;
			santas.get(key).p.r = nr;
			santas.get(key).p.c = nc;
			map[nr][nc] = key;
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 1 && nr < N + 1 && nc >= 1 && nc < N + 1;
	}

	private static int findNearSantaNum() {
		int nearSantaNum = 0;
		int nearSantaDis = Integer.MAX_VALUE;
		
		for(Santa s: santas.values()) {
			if(s.end) continue;
			int dis = getDis(ru.r, ru.c, s.p);
			
			if(dis == nearSantaDis) {
				if(santas.get(nearSantaNum).p.r == s.p.r) {
					if(santas.get(nearSantaNum).p.c < s.p.c) {
						nearSantaNum = map[s.p.r][s.p.c];
					}
				} else if(santas.get(nearSantaNum).p.r < s.p.r){
					nearSantaNum = map[s.p.r][s.p.c];
				}
			} else if(dis < nearSantaDis){
				nearSantaNum = map[s.p.r][s.p.c];
				nearSantaDis = dis;
			}
		}
		return nearSantaNum;
	}

	private static int getDis(int r, int c, Point p) {
		return (int) (Math.pow(r - p.r, 2) + Math.pow(c - p.c, 2)); 
	}

	private static int stoi(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}

