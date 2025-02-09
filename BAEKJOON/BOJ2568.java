import java.io.*;
import java.util.*;

/*
 * 전깃줄 - 2
 */

public class BOJ2568 {
	static class Node {
		int left, right;
		public Node(int left, int right) {
			this.left = left;
			this.right = right;
		}
		@Override
		public String toString() {
			return "Node [left=" + left + ", right=" + right + "]";
		}
	}
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Node[] nodes = new Node[N];
		
		StringTokenizer st;
		
		TreeSet<Integer> set = new TreeSet<Integer>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			nodes[i] = new Node(l, r);
			set.add(l);
		}
		
		Arrays.sort(nodes, Comparator.comparingInt(o -> o.left));
		
		int[] lis = new int[N];
		int[] memo = new int[N];
		
		int length = 1;
		
		for(int i = 1; i < N; i++) {
			int prev = nodes[lis[length - 1]].right;
			int current = nodes[i].right;
			
			if(prev < current) {
				memo[i] = length;
				lis[length++] = i;
			} else {
				int idx = binarySearch(lis, 0, length, nodes, current);
				lis[idx] = i;
				memo[i] = idx;
			}
		}
		
		int[] answer = new int[length];
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(N - length).append("\n");

		for(int i = N - 1; i >= 0; i--) {
			if(length - 1 == memo[i]) {
				set.remove(nodes[i].left);
				length--;
			}
		}
		
		for(int s: set) sb.append(s).append("\n");
		
		System.out.println(sb);
	}
	
	static int binarySearch(int[] lis, int s, int e, Node[] nodes, int target) {
		
		while(s <= e) {
			int m = (s + e) / 2;
			
			if(nodes[lis[m]].right < target) s = m + 1;
			else e = m - 1;
		}
		
		return s;
	}
}
