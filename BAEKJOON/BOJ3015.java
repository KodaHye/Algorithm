import java.io.*;
import java.util.*;

/*
 * 오아시스 재결합
 * 노드에 이제까지 나온 횟수를 저장하는 것이 중요함
 * 처음에는 stack.peek()이 같을 때까지 계속 pop을 하고 다시 push 해줬는데, 이 부분에서 시간 초과가 발생함
 * Stack 보단 ArrayDeque 사용하기
 */

public class BOJ3015 {
	static class Node {
		int h, cnt;
		
		public Node(int h, int cnt) {
			this.h = h;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayDeque<Node> stack = new ArrayDeque<>();
		
		// 처음에는 무조건 스택에 처음 담음
		stack.add(new Node(Integer.parseInt(br.readLine()), 1));

		long result = 0;
		
		for(int i = 0; i < N - 1; i++) {
			int h = Integer.parseInt(br.readLine());
			Node current = new Node(h, 1);
			
			while(!stack.isEmpty() && stack.peekLast().h <= current.h) {
				Node pop = stack.pollLast();
				
				result += pop.cnt;
				if(pop.h == h) current.cnt += pop.cnt;
			}
			
			if(!stack.isEmpty()) result += 1;
			stack.addLast(current);
		}
		
		System.out.println(result);
	}
}