import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 좌표 정렬하기
 */
public class BOJ11650 {
	static class Point implements Comparable<Point> {
		int r, c;
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
		@Override
		public int compareTo(Point o) {
			if(this.r == o.r) return Integer.compare(this.c, o.c);
			return Integer.compare(this.r, o.r);
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		PriorityQueue<Point> queue = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			queue.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		while(!queue.isEmpty()) {
			Point point = queue.poll();
			
			System.out.println(point.r + " " + point.c);
		}
	}
}
