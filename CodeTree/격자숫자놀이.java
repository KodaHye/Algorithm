import java.io.*;
import java.util.*;

/*
 * 시간 계산 잘 해야될 것
 * while문 조건의 연산으로 함부로 사용하면 올바른 시간이 출력되지 않을 수 있음
 * 이 부분 주의하자!
 */

public class 격자숫자놀이 {
	static class Point implements Comparable<Point> {
		int num, cnt;

		public Point(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Point o) {
			if(this.cnt == o.cnt) return Integer.compare(this.num, o.num);
			return Integer.compare(this.cnt, o.cnt);
		}
	}
	static int nums[] = new int[101];
	static PriorityQueue<Point> queue = new PriorityQueue<Point>();
	static int map[][] = new int[101][101];
	static int R, C, K, row = 3, col = 3, result = -1;
	
	public static void main(String[] args) throws Exception {
		initInput();
		func();
		System.out.println(result);
	}

	private static void func() {
		int time = 0;
		
		while(true) {
			
			if(map[R][C] == K) {
				result = time;
				break;
			}
			
			// 행의 개수가 열의 개수보다 크거나 같은 경우
			if(row >= col) {
				col = 0;
				
				for(int r = 1; r < 1 + row; r++) {
					
					Arrays.fill(nums, 0);
					
					for(int c = 1; c < 101; c++) nums[map[r][c]]++;
					
					addQueue();

					col = Math.max(col, queue.size() * 2);
					Arrays.fill(map[r], 0);
					
					for(int c = 1; c < 51; c++) {
						if(queue.isEmpty()) break;
						
						Point current = queue.poll();
						map[r][2 * c - 1] = current.num;
						map[r][2 * c] = current.cnt;
					}
					
				}
			} else {
				row = 0;
				
				for(int c = 1; c < 1 + col; c++) {
					Arrays.fill(nums, 0);
					
					for(int r = 1; r < 101; r++) nums[map[r][c]]++;
					
					addQueue();
					
					row = Math.max(row, queue.size() * 2);
					
					// 해당 열 0으로 채우기
					colFillZero(c);

					for(int r = 1; r < 51; r++) {
						if(queue.isEmpty()) break;
						
						Point current = queue.poll();
						map[2 * r - 1][c] = current.num;
						map[2 * r][c] = current.cnt;
					}
				}
			}
			
			time++;
			if(time > 100) break;
		}
	}

	private static void addQueue() {
		for(int i = 1; i < nums.length; i++) {
			if(nums[i] == 0) continue;
			queue.add(new Point(i, nums[i]));
		}		
	}

	private static void colFillZero(int c) {
		for(int r = 1; r < 101; r++) map[r][c] = 0;
	}

	private static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for(int r = 1; r < 4; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c < 4; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
