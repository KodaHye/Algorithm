import java.io.*;
import java.util.*;

/*
 * 조건 파악 잘 하기!
 * 문제에서 술래의 시야는 항상 3칸으로 고정되어 있음
 */

public class 술래잡기 {
	static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT= 3;
	static final int IN = 0, OUT= 1;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public void move(int dir) { // dir 방향으로 움직이기
			this.r += dr[dir];
			this.c += dc[dir];
		}
	}
	
	static class King {
		Point p;
		int dir = UP; // 초기에는 위를 바라보고 있음
		int status = OUT; // 0: 안으로, 1: 밖으로
		
		public void updateDir(int dir) {
			this.dir = dir;
		}
		
		public King(Point p) {
			this.p = p;
		}
	}
	
	static class Runner {
		Point p;
		int dir;
		boolean isGone;
		
		public void flipDir() {
			this.dir = (this.dir + 2) % 4;
		}
		
		public Runner(Point p, int dir) {
			this.p = p;
			this.dir = dir;
			
		}
	}
	static King king;
	static Runner[] runners;
	static HashSet<Integer>[][] runnerIdxMap;
	static int[][][] snailMap;
	static int N, M, H, K;
	static boolean[][] isTree;
	
	public static void main(String[] args) throws Exception {
		initInput();
		System.out.println(solution());
	}
	
	static int solution() {
		int result = 0;
		
		int turn = 1;
		while(turn < K + 1) {
			moveRunners();
			moveKing();
			result += catchRunners(turn);
			turn++;
		}
		return result;
	}
	
	static int catchRunners(int turn) {
		
		int r = king.p.r, c = king.p.c;
		
		int catchedRunnerCnt = 0;
		
		for(int i = 0; i < 3 && check(r, c); i++) {
			// 뒤에 항상 실행되어야 한는게 있기 때문에
			// 함부로 continue 하지 말 것!
			if(!isTree[r][c]) {
				if(runnerIdxMap[r][c].size() > 0) {
					catchedRunnerCnt += runnerIdxMap[r][c].size();
					
					for(int idx: runnerIdxMap[r][c]) {
						runners[idx].isGone = true;
					}
					
					runnerIdxMap[r][c].clear();
				}
			}
			
			r += dr[king.dir];
			c += dc[king.dir];
			
		}
		return catchedRunnerCnt * turn;
	}
	
	static void moveKing() {
		int d = snailMap[king.status][king.p.r][king.p.c];
		king.p.move(d);
		
		if(king.p.r == 0 && king.p.c == 0) king.status = IN; // 안으로
		if(king.p.r == N / 2 && king.p.c == N / 2) king.status = OUT; // 밖으로
		
		king.updateDir(snailMap[king.status][king.p.r][king.p.c]); // 바라보는 방향 바꿔주기
	}

	// 도망자 움직이기
	// 술래와의 거리가 3 이하인 도망자만 움직일 수 있음
	// 잡힌 도망자는 예외 처리
	static void moveRunners() {
		for(int i = 0; i < runners.length; i++) {
			Runner r = runners[i];
			
			if(r.isGone) continue;
			
			int dis = getDis(r.p, king.p);
			if(dis >= 4) continue; // 거리가 4 이상이면 못움직임
			
			// 현재 바라보고 있는 방향으로 한 칸 이동할 수 있으면
			int nr = r.p.r + dr[r.dir];
			int nc = r.p.c + dc[r.dir];
			
			boolean isMove = false;
			if(check(nr, nc)) { // 격자를 벗어나지 않는 경우
				if(king.p.r == nr && king.p.c == nc) continue;
				else isMove = true;
			} else { // 격자를 벗어나는 경우
				
				r.flipDir(); // 방향 뒤집기
				nr = r.p.r + dr[r.dir];
				nc = r.p.c + dc[r.dir];

				if(king.p.r == nr && king.p.c == nc) continue;
				else isMove = true;
			}
			
			if(!isMove) continue;
			
			runnerIdxMap[r.p.r][r.p.c].remove(i);
			r.p.move(r.dir);
			runnerIdxMap[r.p.r][r.p.c].add(i);
		}
	}
	
	static int getDis(Point p1, Point p2) {
		return Math.abs(p1.r - p2.r) + Math.abs(p1.c - p2.c);
	}
	static void initInput() throws Exception {
		
		System.setIn(new FileInputStream("./input/술래잡기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 격자의 크기
		M = Integer.parseInt(st.nextToken()); // 도망자의 수
		H = Integer.parseInt(st.nextToken()); // 나무의 위치
		K = Integer.parseInt(st.nextToken()); // 턴의 수
		
		king = new King(new Point(N >> 1, N >> 1)); // 술래의 좌표를 중심으로 설정
		setSnailMap();
		
		// 도망자 정보 입력
		runnerIdxMap = new HashSet[N][N];
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				runnerIdxMap[r][c] = new HashSet<>();
			}
		}
		
		runners = new Runner[M];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) == 1 ? RIGHT : DOWN;
			
			// d가 1인 경우에는 좌, 우 - R
			// d가 2인 경우에는 상, 하 - D
			runners[i] = new Runner(new Point(r, c), d);
			runnerIdxMap[r][c].add(i);
		}
		
		
		isTree = new boolean[N][N];
		
		// 나무의 위치
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			
			isTree[r][c] = true;
		}
		
	}
	
	static void printRunnersMap() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				System.out.print(Arrays.toString(runnerIdxMap[r][c].toArray()));
			}
			
			System.out.println();
		}
	}
	
	static void printTreeInfo() {
		for(int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(isTree[r]));
		}
	}
	
	static void printSnailMap() {
		
		// 0: 밖으로 나가고 있는거
		System.out.println("OUT ----------------");
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				System.out.print(snailMap[OUT][r][c] == 0 ? "U" : snailMap[OUT][r][c] == 1 ? "R" : snailMap[OUT][r][c] == 2 ? "D" : "L");
			}
			System.out.println();
		}
		
		System.out.println();
		
		// 1: 안으로 들어가고 있는거
		System.out.println("IN ----------------");
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				System.out.print(snailMap[IN][r][c] == 0 ? "U" : snailMap[IN][r][c] == 1 ? "R" : snailMap[IN][r][c] == 2 ? "D" : "L");
			}
			
			System.out.println();
		}
	}
	
	static void setSnailMap() {
		snailMap = new int[2][N][N];
		
		for(int r = 0; r < N; r++) Arrays.fill(snailMap[0][r], -1);

		int r = 0, c = 0, d = DOWN;
		
		while(true) {
			snailMap[0][r][c] = d;
			snailMap[1][r][c] = (d + 2) % 4;
			
			if(r == N / 2 && c == N / 2) break;
			
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(!check(nr, nc) || snailMap[0][nr][nc] != -1) {
				d = (d - 1 + 4) % 4;
				snailMap[0][r][c] = d;
			}

			nr = r + dr[d];
			nc = c + dc[d];
			
			r = nr;
			c = nc;
		}
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
