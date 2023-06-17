import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 카드 1
 */
public class BOJ2161 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int N = sc.nextInt();
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1; i < N + 1; i++) {
			queue.add(i);
		}
		
		while(!queue.isEmpty()) {
			sb.append(queue.poll() + " ");
			
			if(queue.isEmpty()) break;
			
			int a = queue.poll();
			
			queue.add(a);
		}
		
		System.out.println(sb);
	}
}
