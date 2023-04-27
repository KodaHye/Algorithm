import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * 최대 힙
 */
public class BOJ11279 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return -Integer.compare(o1, o2);
			}
		});
		
		int N = sc.nextInt();
		
		for(int i = 0; i < N; i++) {
			int a = sc.nextInt();
			if(a == 0) {
				if(queue.isEmpty()) sb.append(0 + "\n");
				else sb.append(queue.poll() + "\n");
			} else {
				queue.add(a);
			}
		}
		System.out.println(sb);
	}
}