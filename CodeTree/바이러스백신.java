import java.io.*;
import java.util.*;

/*
 * 조합 공식 공부 빠짝 하기
 * 바이러스가 하나도 없을 경우 주의
 * 시간 구하는거 위치 주의
 * break L; 사용할 때, queue 초기화 잘 해주기!
 */

public class 바이러스백신 {
	static class Point {
		int r, c, d;

		public Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	static LinkedList<Point> list = new LinkedList<Point>();
	static int N, M, K, virusTotalCnt, time = Integer.MAX_VALUE;
	static int map[][];
	static Queue<Point> queue = new LinkedList<Point>();
	static boolean sel[], v[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		initInput();
		if(!checkVirusCnt()) {
			System.out.println(0);
			return;
		}
		comb(0, 0);
		System.out.println(time == Integer.MAX_VALUE ? -1 : time);
	}

    private static boolean checkVirusCnt() {
    	if(virusTotalCnt == 0) return false;
    	return true;
	}

	private static void comb(int start, int depth) {
        if (depth == M) {
        	bfs();
            return;
        }

        for (int i = start; i < list.size(); i++) {
        	if(sel[i]) continue;
        	sel[i] = true;
            comb(i + 1, depth + 1);
            sel[i] = false;
        }
    }

	private static void bfs() {
		queue.clear();
		initVisit();
		for(int i = 0; i < sel.length; i++) {
			if(!sel[i]) continue;
			queue.add(list.get(i));
			v[list.get(i).r][list.get(i).c] = true;
		}
		
		
		int virusCnt = 0;
		int tmpTime = 0;
		boolean cleanVirus = false;
		
		L: while(!queue.isEmpty()) {
			Point current = queue.poll();
			tmpTime = current.d + 1;
			
			for(int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				if(!check(nr, nc) || v[nr][nc] || map[nr][nc] == 1) continue;
				
				if(map[nr][nc] == 0) virusCnt++;

				if(virusCnt == virusTotalCnt) {
					cleanVirus = true;
					break L;
				}
				
				queue.add(new Point(nr, nc, current.d + 1));
				v[nr][nc] = true;
			}
		}
		

		if(cleanVirus) time = Math.min(time, tmpTime);
	}

	private static void initVisit() {
		for(int r = 0; r < v.length; r++) {
			Arrays.fill(v[r], false);
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

	private static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		v = new boolean[N][N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				if(map[r][c] == 0) virusTotalCnt++;
				if(map[r][c] == 2) list.add(new Point(r, c, 0));
			}
		}
		
		sel = new boolean[list.size()];
	}
}
