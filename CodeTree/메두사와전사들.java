import java.io.*;
import java.util.*;

/*
 * 메두사의 시선을 처리하는게 어려웠다 ..!
 * boolean 배열을 사용해서 복잡하다면, int형 배열을 써서 상태를 여러가지로 나타대보자
 */

public class 메두사와전사들 {
	static final int INF = Integer.MAX_VALUE, ROAD = 0, CANSEE = 1, SHADOW = 2;
	static int[][] map, medusaEyesMap;
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0}, dc = {-1, 0, 1, 1, 1, 0, -1, -1};
	static int[] udlr = {1, 5, 7, 3}, lrud = {7, 3, 1, 5};
	static Point park;
	static Medusa medusa;
	static HashSet<Integer>[][] nodeIdxMap;
	static ArrayList<Node> nodes;
	static int N, M, 전사가이동한거리의합, 메두사로인해돌이된전사수, 메두사를공격한전사수;
	
	public static void main(String[] args) throws Exception {
		initInput();
		
		StringBuilder sb = new StringBuilder();
		
		// 공원에 갈 수 있는지 확인
		boolean canGo = checkMedusaMinDis();
		
		while(canGo) {
			medusaMove();
			
			if(medusa.p.r == park.r && medusa.p.c == park.c) {
				sb.append("0").append("\n");
				break;
			}
			
			unRockNodes(); // 돌 해제
			initInfo();
			attackNodes();
			
			medusaEyesMap = medusaCheckMap(); // 메두사가 볼 수 있는 시야
			
			전사가이동한거리의합 = moveNodes();
			
			sb.append(전사가이동한거리의합).append(" ")
				.append(메두사로인해돌이된전사수).append(" ")
				.append(메두사를공격한전사수).append(" ").append("\n");
		}
		
		if(!canGo) sb.append("-1");
		System.out.println(sb);
	}
	
	private static void medusaMove() {
		medusa.move();
	}

	private static boolean checkMedusaMinDis() {
		
		int[][][] mark = new int[N][N][2];
		
		boolean[][] v = new boolean[N][N];
		boolean canReach = false;
		
		Queue<Point> q = new ArrayDeque<>();
		
		q.add(medusa.p);
		v[medusa.p.r][medusa.p.c] = true;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			if(current.r == park.r && current.c == park.c) {
				int cr = current.r, cc = current.c;
				canReach = true;
				
				Deque<Integer> dq = new ArrayDeque<>();
				
				while(cr != medusa.p.r || cc != medusa.p.c) {
					dq.add(mark[cr][cc][1]);
				
					int pos = mark[cr][cc][0];
					cr = pos / N;
					cc = pos % N;
				}
				
				medusa.dq = dq;
			}
			for(int d: udlr) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				if(!check(nr, nc) || map[nr][nc] != ROAD || v[nr][nc]) continue;
				
				q.add(new Point(nr, nc));
				v[nr][nc] = true;
				
				mark[nr][nc][0] = current.r * N + current.c;
				mark[nr][nc][1] = d;
			}
		}
		return canReach;
	}

	static int moveNodes() {
		
		int result = 0;

		for(int i = 0; i < nodes.size(); i++) {
			if(nodes.get(i).isOut || nodes.get(i).isRock) continue;
			
			result += (move(i, udlr) + move(i, lrud)); // 이동
		}
		
		return result;
	}
	
	static int move(int idx, int[] dir) {
		
		Node n = nodes.get(idx);
		if(n.isOut) return 0;
		
		int result = 0;
		int currentDis = getDis(n.p.r, n.p.c, medusa.p.r, medusa.p.c);
		
		int md = -1;
		
		for(int d: dir) {
			int nr = n.p.r + dr[d];
			int nc = n.p.c + dc[d];
			
			if(!check(nr, nc) || medusaEyesMap[nr][nc] == CANSEE) continue;
			int tmpDis = getDis(nr, nc, medusa.p.r, medusa.p.c);
			
			if(currentDis > tmpDis) {
				currentDis = tmpDis;
				md = d;
			}
		}
		
		// 움직일 수 있다면,,
		if(md != -1) {
			nodeIdxMap[n.p.r][n.p.c].remove(idx);
			
			n.p.move(md);
			
			nodeIdxMap[n.p.r][n.p.c].add(idx);
			
			// 메두사와 같은 칸에 도달한 전사는 메두사를 공격함.. 하지만 이기지 못하고 사라짐
			if(n.p.r == medusa.p.r && n.p.c == medusa.p.c) {
				n.isOut = true;
				
				nodeIdxMap[n.p.r][n.p.c].remove(idx);
				메두사를공격한전사수++;
			}
			result++;
		}

		return result;
	}
	
	static void unRockNodes() {
		for(Node n: nodes) {
			if(n.isOut || !n.isRock) continue;
			n.isRock = false;
		}
	}
	static void initInfo() {
		전사가이동한거리의합 = 0;
		메두사로인해돌이된전사수 = 0;
		메두사를공격한전사수 = 0;
	}
	
	// 상, 하, 좌, 우 방향 중 선택
	static int[][] medusaCheckMap() {
		int[][] result = new int[N][N];
		HashSet<Integer> rockIdxSet = new HashSet<>();
		
		for(int d: udlr) {
			int[][] tmp = new int[N][N];
			
			HashSet<Integer> tmpRockIdxSet = new HashSet<Integer>();
			
			for(int i = -1; i < 2; i++) {
				
				int sd = (d + i + 8) % 8;
				
				if(i != 0) {
					
					int sr = medusa.p.r + dr[sd];
					int sc = medusa.p.c + dc[sd];
					
					while(check(sr, sc)) {
						
						checkSide(tmp, sr, sc, d, i, tmpRockIdxSet, CANSEE);
						
						sr += dr[d];
						sc += dc[d];
					}
					
				} else {
					checkStraight(tmp, medusa.p.r + dr[sd], medusa.p.c + dc[sd], sd, tmpRockIdxSet, CANSEE);
				}
			}
			
			if(rockIdxSet.size() < tmpRockIdxSet.size()) {
				result = tmp;
				rockIdxSet = tmpRockIdxSet;
			}
		}
		
		메두사로인해돌이된전사수 = rockIdxSet.size();
		
		for(int idx: rockIdxSet) {
			nodes.get(idx).isRock = true;
		}
		
		return result;
	}
	
	static void checkSide(int[][] tmp, int r, int c, int d, int i, HashSet<Integer> rockIdxSet, int status) {
		if(!check(r, c) || tmp[r][c] == SHADOW) return;

		tmp[r][c] = status;
		
		int sd = (d + i + 8) % 8;
		
		if(nodeIdxMap[r][c].size() > 0) {
			if(status == CANSEE) rockIdxSet.addAll(nodeIdxMap[r][c]);
			status = SHADOW;
			checkStraight(tmp, r + dr[d], c + dc[d], d, rockIdxSet, status);
		}
		checkSide(tmp, r + dr[sd], c + dc[sd], d, i, rockIdxSet, status);
	}
	
	static void checkStraight(int[][] tmp, int r, int c, int d, HashSet<Integer> rockIdxSet, int status) {
		
		if(!check(r, c) || tmp[r][c] == SHADOW) return;
		
		tmp[r][c] = status;
		
		if(nodeIdxMap[r][c].size() > 0) {
			if(status == CANSEE) rockIdxSet.addAll(nodeIdxMap[r][c]);
			status = SHADOW;
		}
		checkStraight(tmp, r + dr[d], c + dc[d], d, rockIdxSet, status);
	}
	
	// 메두사가 움직이고 나서, 같은 위치에 있는 전사들 없애기
	static void attackNodes() {
		
		int r = medusa.p.r, c = medusa.p.c;
		
		if(nodeIdxMap[r][c].size() == 0) return;
		
		// 메두사와 같은 위치에 있는 노드들은 사라짐
		for(int idx: nodeIdxMap[r][c]) nodes.get(idx).isOut = true;
		
		nodeIdxMap[r][c].clear();
	}
	
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/메두사와전사들.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 마을의 크기
		M = Integer.parseInt(st.nextToken()); // 전사의 수
		
		map = new int[N][N];
		medusaEyesMap = new int[N][N];
		
		// 메두사의 집의 정보, 공원의 정보 -------------------
		st = new StringTokenizer(br.readLine());
		medusa = new Medusa(setPoint(st));
		park = setPoint(st);
		
		// M명의 전사들의 정보 -----------------------------
		nodeIdxMap = new HashSet[N][N];
		nodes = new ArrayList<>();
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				nodeIdxMap[r][c] = new HashSet<>();
			}
		}
		
		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < M; i++) {
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			nodes.add(new Node(new Point(r, c)));
			nodeIdxMap[r][c].add(i);
		}
		
		for(int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			
			for(int c = 0; c < map[0].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static class Node {
		Point p;
		boolean isRock, isOut;
		
		public Node(Point p) {
			this.p = p;
		}
	}
	
	static class Medusa {
		Point p;
		Deque<Integer> dq = new ArrayDeque<>();
		
		public Medusa(Point p) {
			this.p = p;
		}
		
		void move() {
			int d = dq.pollLast();
			
			this.p.move(d);
		}
	}
	
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		void move(int d) {
			this.r += dr[d];
			this.c += dc[d];
		}
	}
	
	static int getDis(int r1, int c1, int r2, int c2) {
		return Math.abs(r1 - r2) + Math.abs(c1 - c2);
	}
	
	static Point setPoint(StringTokenizer st) {
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		return new Point(r, c);
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
