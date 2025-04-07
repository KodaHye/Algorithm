import java.io.*;
import java.util.*;

/*
 * 변수 크기 고려 잘 하기
 * 최적화 할 수 있는 부분이 어디가 있을지 꼼꼼하게 고려하기
 */

public class 토끼와경주 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static class Point {
		int r, c;
		public Point() {}
	}
	static class Rabbit implements Comparable<Rabbit> {
		int pidx, jumpCnt, d; // d: 토끼가 이동해야 되는 거리
		long score; 
		
		Point p = new Point();
		
		public Rabbit(int pidx, int d) {
			this.pidx = pidx;
			this.d = d;
		}
		
		@Override
		public int compareTo(Rabbit o) {
			long pSum = getPointSum(this.p);
			long oSum = getPointSum(o.p);
			
			if(this.jumpCnt != o.jumpCnt) return Integer.compare(this.jumpCnt, o.jumpCnt);
			if(pSum != oSum) return Long.compare(pSum, oSum);
			if(this.p.r != o.p.r) return Long.compare(this.p.r, o.p.r);
			if(this.p.c != o.p.c) return Long.compare(this.p.c, o.p.c);
			return Integer.compare(this.pidx, o.pidx);
		}
		
		int getPointSum(Point p) {
			return p.r + p.c;
		}
	}
	
	static PriorityQueue<Rabbit> q = new PriorityQueue<>();
	static HashMap<Integer, Rabbit> rabbitMap = new HashMap<>();
	static int Q, N, M, P;
	static long totalScore;
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("./input/토끼와경주.txt"));
		
		br = new BufferedReader(new InputStreamReader(System.in));
		Q = Integer.parseInt(br.readLine()); // 명령의 수
		
		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int order = Integer.parseInt(st.nextToken());
			if(order == 100) step1(st);
			if(order == 200) step2(st);
			if(order == 300) step3(st);
			if(order == 400) step4();
		}
	}
	
	static void step4() {
		
		long result = Long.MIN_VALUE;

		for(Rabbit r: rabbitMap.values()) {
		    long actualScore = r.score + totalScore;
		    result = Math.max(result, actualScore);
		}
		System.out.println(result);
	}
	
	static void step3(StringTokenizer st) {
		int pid = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int d = rabbitMap.get(pid).d;
		rabbitMap.get(pid).d = L * d;
	}

	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1}; // 상, 좌, 하, 우
	
	static void step2(StringTokenizer st) {
		
		int K = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		HashSet<Integer> rabbits = new HashSet<>();

		while(K-- > 0) {
			moveRabbit(rabbits);
		}
		
		PriorityQueue<Rabbit> resultRabbit = new PriorityQueue<>((o1, o2) -> {
			if(o1.p.r + o1.p.c != o2.p.r + o2.p.c) return -1 * Integer.compare(o1.p.r + o1.p.c, o2.p.r + o2.p.c); // 큰 순서대로
			if(o1.p.r != o2.p.r) return -1 * Integer.compare(o1.p.r, o2.p.r);
			return -1 * Integer.compare(o1.p.c, o2.p.c);
		});
		
		for(int idx: rabbits) {
			resultRabbit.add(rabbitMap.get(idx));
		}
		
		resultRabbit.peek().score += S;
	}
	
	static void moveRabbit(HashSet<Integer> rabbits) {
		Rabbit rabbit = q.poll(); 
		rabbit.jumpCnt += 1;
		
		// 네 방향으로 di 만큼 이동했을 때의 위치를 구함
		// 4개의 위치 중 1. (행 번호 + 열 번호), 2. 행 번호, 3. 열 번호가 큰 순서대로 우선순의를 둠
		
		int mr = -1, mc = -1;
		
		for(int d = 0; d < 4; d++) {
			long mod = 0;
			
			int td = d;
			
			if(d == 0 || d == 2) mod = 2 * N - 2;
			else mod = 2 * M - 2;
			
			int dis = rabbit.d;
			if(dis > mod) dis %= mod;
			
			// dis 만큼 가주기
			int r = rabbit.p.r, c = rabbit.p.c;
			
			int nr = r + dis * dr[d], nc = c + dis * dc[d];
			
			while(!check(nr, nc)) {
				if(!check(nr, nc)) {
					if(nr < 0) nr = Math.abs(nr);
					if(nr >= N) nr = (N - 1) - (nr + 1 - N);
					if(nc < 0) nc = Math.abs(nc);
					if(nc >= M) nc = (M - 1) - (nc + 1 - M);
				}
			}
			
			r = nr;
			c = nc;
			
			// 최종 다 갔으면 (r, c)
			// 4개의 위치 중 1. (행 번호 + 열 번호), 2. 행 번호, 3. 열 번호가 큰 순서대로 우선순의를 둠
			if(mr + mc < r + c) {
				mr = r;
				mc = c;
			} else if(mr + mc == r + c) {
				if(mr < r) {
					mr = r;
					mc = c;					
				} else if(mr == r) {
					if(mc < c) {
						mr = r;
						mc = c;
					}
				}
			}
			
			d = td;
		}
		
		rabbit.p.r = mr; //  토끼 위치 옮겨주기
		rabbit.p.c = mc;
		
		q.add(rabbit);
		
		rabbits.add(rabbit.pidx);

		long sharedScore = (mr + 1) + (mc + 1);  // 공통 점수
		totalScore += sharedScore;
		rabbit.score -= sharedScore;  // 나만 마이너스
	}
	
	static boolean check(long r, long c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
	
	static void step1(StringTokenizer st) {
		N = Integer.parseInt(st.nextToken()); // 격자의 크기
		M = Integer.parseInt(st.nextToken()); // 격자의 크기
		P = Integer.parseInt(st.nextToken()); // 토끼의 수
		
		for(int i = 0; i < P; i++) {
			
			int pid = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			Rabbit r = new Rabbit(pid, d);
			
			rabbitMap.put(pid, r);
			q.add(r);
		}
	}
}
 