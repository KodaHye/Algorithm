import java.util.*;
import java.io.*;

/*
 * 좌표 정렬하기 2
 */
public class BOJ11651 {
	static class Point implements Comparable<Point> {
		int r, c;
		
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Point o) {
			if(this.c == o.c) return Integer.compare(this.r,  o.r);
			return Integer.compare(this.c, o.c);
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PriorityQueue<Point> queue = new PriorityQueue();
	static StringTokenizer st;
	static int N;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		for (int test_case = 0; test_case < N; test_case++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			queue.add(new Point(a, b));
		}
		
		while(!queue.isEmpty()) {
			Point point = queue.poll();
			
			System.out.println(point.r + " " + point.c);
		}
	}
}
