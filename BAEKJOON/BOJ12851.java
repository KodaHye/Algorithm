import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 숨바꼭질 2
 */
public class BOJ12851 {
	static int N, K, time, count;
	static boolean v[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		time= Integer.MAX_VALUE;
		
		func();
		
		System.out.println(time + "\n" + count);
	}
	private static void func() {
		Queue<int[]> queue = new LinkedList<>();
		v = new boolean[100001];
		
		queue.offer(new int[] {N, 0});
		v[N] = true;
		
		while(!queue.isEmpty()) {
			// add 할 때 방문처리하면 한 번 갔던 곳을 다시 갈 수 없음!
			// 그래서 poll 할 때 방문처리를 한다!
			int[] cur = queue.poll();
			v[cur[0]] = true;
			
			if(cur[0] == K) {
				if(cur[1] <= time) {
					time = cur[1];
				}
				if(cur[1] == time) count++;
			}
			
			if(cur[0] - 1 >= 0 && !v[cur[0] - 1]) {
				queue.add(new int[] {cur[0] - 1, cur[1] + 1});
			} 
			if(cur[0] + 1 <= 100000 && !v[cur[0] + 1]) {
				queue.add(new int[] {cur[0] + 1, cur[1] + 1});
			} 
			if(cur[0] * 2 <= 100000 && !v[cur[0] * 2]) {
				queue.add(new int[] {cur[0] * 2, cur[1] + 1});
			}
		}
	}
}
