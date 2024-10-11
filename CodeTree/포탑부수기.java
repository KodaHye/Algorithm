import java.io.*;
import java.util.*;

/*
 * Comparable 설정 잘 되었는지 확인하기
 * BFS 경로 역추적
 *    처음 DFS 했을 때, 시간 초과 발생했었음
 * 	  DFS 할 때, 시간 복잡도 고려하기
 * 조건 잘 확인하고 구현하기
 */

public class 포탑부수기 {
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
	static class Top implements Comparable<Top> {
		Point p;
		int power, time;
		public Top(Point p, int power) {
			this.p = p;
			this.power = power;
		}
		
		@Override
		public int compareTo(Top o) {
			if(this.power == o.power) {
				if(this.time == o.time) {
					int thisSum = this.p.r + this.p.c;
					int oSum = o.p.r + o.p.c;
					if(thisSum == oSum) return -1 * Integer.compare(this.p.c, o.p.c); // 내림차순
					return -1 * Integer.compare(thisSum, oSum); // 내림차순
				}
				return -1 * Integer.compare(this.time, o.time); // 가장 최신순 (내림차순)
			}
			return Integer.compare(this.power, o.power); // 공격력이 가장 낮은 포탑 순서대로
		}

		@Override
		public String toString() {
			return "Top [p=" + p + ", power=" + power + ", time=" + time + "]";
		}
	}
	static int N, M, K;
	static HashMap<Integer, Top> tops;
	static int[][] map, topMap;
	static Top current, target;
	static boolean[][] relateAttack;
	static int[] dr = {0, 1, 0, -1, -1, -1, 1, 1}, dc = {1, 0, -1, 0, -1, 1, 1, -1};
	static Point[][] step;
	
	static void solution() {
		int turn = 1;
		while(turn < K + 1) {
			if(tops.size() == 1) break;
			
//			System.out.println(turn + "번쨰 턴 입니다 =======================");
//			(1) 공격자 선정 =========================
			selectCurrentAndTarget(turn);
			
//			(2) 공격자의 공격
			attackTarget();
			
//			(3) 포탑 부서짐
			breakTops();
			
//			(4) 포탑 정비하기
			updateTops();
			turn++;
		}
		
		int maxPower = 0;
		for(Top t: tops.values()) {
			maxPower = Math.max(maxPower, t.power);
		}
		
		System.out.println(maxPower);
	}
	
	static void printRelateAttackMap() {
		System.out.print("------------------------");
		for(int r = 0; r < relateAttack.length; r++) {
			System.out.println(Arrays.toString(relateAttack[r]));
		}
	}
	
	static void updateTops() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				int id = topMap[r][c];
				if(relateAttack[r][c] || id == 0) continue;
				updateTopPower(r, c, 1);
			}
		}
	}
	static void breakTops() {
		
		for(int r = 0; r < topMap.length; r++) {
			for(int c = 0; c < topMap[0].length; c++) {
				int id = topMap[r][c];
				if(id == 0 || map[r][c] > 0)continue;
				
				tops.remove(id);
				map[r][c] = 0;
				topMap[r][c] = 0;
			}
		}
	}
	
	static void markRelateAttack(int r, int c) {
		relateAttack[r][c] = true;
	}
	
	static void attackTarget() {
		relateAttack = new boolean[N][M];
		markRelateAttack(current.p.r, current.p.c);
		markRelateAttack(target.p.r, target.p.c);
		
		if(canLaserAttack()) {
//			System.out.println("레이저 공격 할 수 있어요!");
			laserAttack(target.p.r, target.p.c);
		} 
		else {
//			System.out.println("레이저 공격 못해요!");
			bomb();
		}
	}
	
	static void bomb() {
		updateTopPower(target.p.r, target.p.c, -current.power);
		
		for(int d = 0; d < 8; d++) {
			int nr = (target.p.r + dr[d] + N) % N;
			int nc = (target.p.c + dc[d] + M) % M;
			
			// 벽이거나 공격자라면 X
			if(map[nr][nc] == 0 || (nr == current.p.r && nc == current.p.c)) continue;
			updateTopPower(nr, nc, -current.power / 2);
			relateAttack[nr][nc] = true;
		}
	}
	static void updateTopPower(int r, int c, int power) {
		int id = topMap[r][c];
		tops.get(id).power += power;
		map[r][c] = tops.get(id).power;
	}
	static void laserAttack(int r, int c) {
		if(r == current.p.r && c == current.p.c) return;
		if(r == target.p.r && c == target.p.c) {
			updateTopPower(r, c, -current.power);
		} else {
			updateTopPower(r, c, -current.power / 2);
			relateAttack[r][c] = true;
		}
		
//		System.out.println("현재: " + r + " " + c + ", 이전: " + step[r][c].toString());
		laserAttack(step[r][c].r, step[r][c].c);
	}
	static void printStepMap() {
		System.out.println("stepMap 출력합니다 --------------");
		for(int r = 0; r < step.length; r++) {
			System.out.println(Arrays.toString(step[r]));
		}
	}
	static boolean canLaserAttack() {
		step = new Point[N][M]; // r, c 지점을 올 때, 직전 c 확인하기 위한 용도
		
		boolean[][] v = new boolean[N][M];
		
		Deque<Point> q = new ArrayDeque<Point>();
		q.add(current.p);
		v[current.p.r][current.p.c] = true;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			if(current.r == target.p.r && current.c == target.p.c) return true;
			
			for(int d = 0; d < 4; d++) {
				int nr = (current.r + dr[d] + N) % N;
				int nc = (current.c + dc[d] + M) % M;
				
				if(map[nr][nc] == 0 || v[nr][nc]) continue;
				step[nr][nc] = new Point(current.r, current.c); // nr, nc 가기 직전에는 current.r, current.c를 갔다!
				q.add(new Point(nr, nc));
				v[nr][nc] = true;
			}
		}
		return false;
	}
	
	static void printCurrentAndTarget() {
		System.out.println("공격 포탑: " + current.toString());
		System.out.println("피해 포탑: " + target.toString());
		
	}

	static void selectCurrentAndTarget(int turn) {
		current = null; target = null;
		ArrayList<Top> list = new ArrayList<Top>();
		
		list.addAll(tops.values());
		Collections.sort(list);
		
		current = list.get(0); // 공격자 선정
		updateTopPower(current.p.r, current.p.c, N + M); // 핸디캡 적용
		target = list.get(list.size() - 1);

		current.time = turn; // 최신순을 알아야 되기 떄문에 time 변경해주기
	}
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
	}
	
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/포탑부수기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 행의 크기
		M = Integer.parseInt(st.nextToken()); // 열의 크기
		K = Integer.parseInt(st.nextToken()); // 턴의 수
		
		map = new int[N][M];
		topMap = new int[N][M];
		tops = new HashMap<>();
		
		int idx = 1;
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				int power = Integer.parseInt(st.nextToken());
				if(power == 0) continue;
				
				map[r][c] = power;
				tops.put(idx, new Top(new Point(r, c), power));
				topMap[r][c] = idx++;
			}
		}
		
//		printTopsInfo();
//		printPowerMap();
//		printTopIdMap();
	}
	
	static void printTopsInfo() {
		System.out.println("Top들의 정보 출력합니다 ------------------");
		tops.entrySet().forEach(s -> {
			System.out.println(s.getKey() + " " + s.getValue());
		});
	}
	static void printTopIdMap() {
		System.out.println("TopId 정보 출력합니다 --------------");
		for(int r = 0; r < N; r++) 
			System.out.println(Arrays.toString(topMap[r]));
	}
	static void printPowerMap() {
		System.out.println("powerMap 정보 출력합니다 ---------------");
		for(int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	}
}
