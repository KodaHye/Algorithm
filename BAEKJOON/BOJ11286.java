import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * 절댓값 힙
 */
public class BOJ11286 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
			int tmp1 = Math.abs(o1);
			int tmp2 = Math.abs(o2);
			
			if(tmp1 == tmp2) return Integer.compare(o1, o2);
			return Integer.compare(tmp1, tmp2);
		});
		
		int N = sc.nextInt();
		
		for(int i = 0; i < N; i++) {
			int x = sc.nextInt();
			
			if(x == 0) {
				if(queue.isEmpty()) System.out.println(0);
				else System.out.println(queue.poll());
			} else {
				queue.add(x);
			}
		}
	}
}
