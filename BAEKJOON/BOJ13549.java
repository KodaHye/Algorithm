import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 숨바꼭질 3
 */
public class BOJ13549 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K, arr[], count;
	static class Point implements Comparable<Point>{
		int x, c;

		public Point(int x, int c) {
			super();
			this.x = x;
			this.c = c;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.c, o.c);
		}
	}
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수빈이 위치
		K = Integer.parseInt(st.nextToken()); // 동생 위치
		
		arr = new int[100001]; // 0 ~ 100,000
		
		while(true) {
			PriorityQueue<Point> queue = new PriorityQueue<>();
			boolean v[] = new boolean[100001];

			boolean flag = false;
			
			queue.add(new Point(N, 0));
			v[N] = true;
			
			while(!queue.isEmpty())  {
				Point current = queue.poll();
				v[current.x] = true; // 이 때 방문처리를 해야 순서에 대한 의존도가 생기지 않음
				
				if(current.x == K) {
					flag = true;
					count = current.c;
					break;
				}
				
				if(2 * current.x <= 100000 && !v[2 * current.x]) queue.add(new Point(current.x * 2, current.c));
				if(current.x + 1 <= 100000 && !v[current.x + 1]) queue.add(new Point(current.x + 1, current.c + 1));
				if(current.x - 1 >= 0 && !v[current.x - 1])queue.add(new Point(current.x - 1, current.c + 1));
			}
			
			if(flag) break;
		}
		
		System.out.println(count);
	}
}
