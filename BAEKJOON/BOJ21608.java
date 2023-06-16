import java.io.*;
import java.util.*;

/*
 * 상어 초등학교
 */
public class BOJ21608 {
	
	static class Point implements Comparable<Point> {
		int r, c, count, zero;

		public Point(int r, int c, int count, int zero) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
			this.zero = zero;
		}

		@Override
		public int compareTo(Point o) {
		    if (this.count == o.count) {
		        if (this.zero == o.zero) {
		            if (this.r == o.r) return Integer.compare(this.c, o.c);
		            else return Integer.compare(this.r, o.r);
		        } else return Integer.compare(o.zero, this.zero);
		    } else return Integer.compare(o.count, this.count);
	    }
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, result, map[][];
	static ArrayList<Integer> list[];
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		list = new ArrayList[N * N + 1];
		
		for(int i = 0; i < list.length; i++) list[i] = new ArrayList<>();
		
		// 학생들이 좋아하는 학생들의 번호 저장
		for(int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int student = Integer.parseInt(st.nextToken());
			
			while(st.hasMoreTokens()) {
				list[student].add(Integer.parseInt(st.nextToken()));
			}
			
			// map을 확인하면서 자리 배정하기
			checkMap(student);
			
		}
		
		// list에 저장된 선호 학생 내역 확인하면서 만족도의 총 합 구하기
		calSatis();
		
		System.out.println(result);
	}

	private static void checkMap(int student) {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				int count = 0;
				int zero = 0;
				
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(!check(nr, nc)) continue;
					for(int i = 0; i < list[student].size(); i++) {
						if(map[nr][nc] == list[student].get(i)) count++;
						if(map[nr][nc] == 0) zero++;
					}
				}
				
				queue.add(new Point(r, c, count, zero));
			}
		}
				
		while(!queue.isEmpty()) {

			Point point = queue.poll();

			if(map[point.r][point.c] == 0) {
				map[point.r][point.c] = student;
				break;
			}		
		}
	}

	private static void calSatis() {
		for(int i = 1; i < N * N  + 1; i++) {
			for(int r = 0; r < map.length; r++) {
				for(int c = 0; c < map[r].length; c++) {
					if(map[r][c] == i) {
						if(checkAround(r, c, i) == 0) result += 0;
						else result += Math.pow(10, checkAround(r, c, i) - 1);
					}
				}
			}
		}
	}
	
	private static int checkAround(int r, int c, int i) {
		int count = 0;
		
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(!check(nr, nc)) continue;
			
			for(int j = 0; j < list[i].size(); j++) {
				if(list[i].get(j) == map[nr][nc]) count++;
			}
		}
		
		return count;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
