import java.io.*;
import java.util.*;

/*
 * 퇴사
 */

public class BOJ15486 {
	static class Node {
		int t, p;
		public Node(int t, int p) {
			this.t = t;
			this.p = p;
		}
	}
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./BAEKJOON/input/BOJ15486.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1]; // i번째 날까지 일할 때, 얻을 수 있는 최대 수익
		
		Node[] nodes = new Node[N];
		for(int i = 0; i < nodes.length; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			
			nodes[i] = new Node(t, p);
		}
		
		for(int i = N - 1; i >= 0; i--) {
			dp[i] = Math.max(dp[i], dp[i + 1]);
			if(i + nodes[i].t <= N) {
				dp[i] = Math.max(dp[i], dp[i + nodes[i].t] + nodes[i].p);
			}
		}
		
		System.out.println(dp[0]);
	}
}
