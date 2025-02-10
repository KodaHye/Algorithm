import java.io.*;
import java.util.*;

/*
 * 숨바꼭질 4
 */

public class BOJ13913 {
	static final int LIMIT = 100_001;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] prev = new int[LIMIT];
		int[] time = new int[LIMIT];
		boolean[] v = new boolean[LIMIT];
		
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		q.add(N);
		v[N] = true;
		prev[N] = -1;
		
		int result = 0;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			if(current == K) {
				result = time[current];
				break;
			}
			
			if(isValid(current - 1) && !v[current - 1]) {
				q.add(current - 1);
				v[current - 1] = true;
				prev[current - 1] = current;
				time[current - 1] = time[current] + 1;
			}
			
			if(isValid(current + 1) && !v[current + 1]) {
				q.add(current + 1);
				v[current + 1] = true;
				prev[current + 1] = current;
				time[current + 1] = time[current] + 1;
			}
			
			if(isValid(2 * current) && !v[current * 2]) {
				q.add(2 * current);
				v[2 * current] = true;
				prev[2 * current] = current;
				time[2 * current] = time[current] + 1;
			}
		}
		
		Stack<Integer> stack = new Stack<Integer>();
		stack.add(K);
		
		while(prev[stack.peek()] != -1) {
			stack.add(prev[stack.peek()]);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(result).append("\n");
		
		while(!stack.isEmpty()) sb.append(stack.pop()).append(" ");
		
		System.out.println(sb);
	}
	
	static boolean isValid(int pos) {
		return pos >= 0 && pos < LIMIT;
	}
}
