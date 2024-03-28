import java.io.*;
import java.util.*;

public class 원자충돌 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Atom {
		Point p;
		int m, s, d;
		public Atom(Point p, int m, int s, int d) {
			this.p = p;
			this.m = m;
			this.s= s;
			this.d = d;
		}
	}
	
	static HashMap<Integer, Atom> hashMap = new HashMap<Integer, Atom>();
	static ArrayList<Integer> map[][];
	static int dr[] = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
	static int N, key;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N + 1][N + 1];
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) map[r][c] = new ArrayList<Integer>();
		}
		
		for(int i = 1; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			map[r][c].add(i);
			hashMap.put(i, new Atom(new Point(r, c), m, s, d));
		}
		key = M + 1;
		
		while(K-- > 0) {
			moveAtoms();
			hapsung();
		}
		int result = 0;
		
		for(Atom a: hashMap.values()) {
			result += a.m;
		}
		
		System.out.println(result);
	}

	private static void hapsung() {
		for(int r = 1; r < map.length; r++) {
			for(int c = 1; c < map[0].length; c++) {
				if(map[r][c].size() < 2) continue;
				
				int m = 0;
				int s = 0;
				int cnt = map[r][c].size();

				int checkTmpDir = hashMap.get(map[r][c].get(0)).d % 2;
				boolean checkDir = true;
				
				for(int k = 0; k < map[r][c].size(); k++) {
					m += hashMap.get(map[r][c].get(k)).m;
					s += hashMap.get(map[r][c].get(k)).s;

					// 요런 부분 주의할것,,,
					// 나중에 다시 바뀔 수 있으니까
					// 특정 경우에만 바뀔 수 있게 하기(바뀌고,,, 또 바뀌고,,, 이렇게 되면 안됨!)
					if(k != 0 && (hashMap.get(map[r][c].get(k)).d % 2 != checkTmpDir)) checkDir = false;
				}
				
				for(int k = 0; k < map[r][c].size(); k++) {
					hashMap.remove(map[r][c].get(k));
				}
				map[r][c].clear();
				
				if(m / 5 == 0) continue;
				int start = 0;
				if(!checkDir) start = 1;
				
				for(int d = start; d < 8; d += 2) {
					map[r][c].add(key);
					hashMap.put(key, new Atom(new Point(r, c), m / 5, s / cnt, d));
					key++;
				}
			}
		}
	}

	private static void moveAtoms() {
		hashMap.entrySet().forEach(s -> {
			int r = s.getValue().p.r;
			int c = s.getValue().p.c;
			
			int dir = s.getValue().d;
			int len = s.getValue().s;
			int nr = (r + len * dr[dir]) % N;
			int nc = (c + len * dc[dir]) % N;
			if(nr <= 0) nr += N;
			if(nc <= 0) nc += N;
			
			map[r][c].remove(s.getKey());
			map[nr][nc].add(s.getKey());
			s.getValue().p.r = nr;
			s.getValue().p.c = nc;
		});
	}
}