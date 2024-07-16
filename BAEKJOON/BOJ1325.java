import java.io.*;
import java.util.*;

/*
효율적인 해킹
 */

public class BOJ1325 {
	static boolean v[];
	static ArrayList<Integer> adj[];
	static int ans[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];
		ans = new int[N + 1];

		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adj[a].add(b);
		}

		Queue<Integer> queue = new LinkedList<>();

		int maxValue = Integer.MIN_VALUE;

		for(int i = 1; i < adj.length; i++) {
			v = new boolean[N + 1];
			queue.add(i);
			v[i] = true;

			while(!queue.isEmpty()) {
				int current = queue.poll();

				for(int next: adj[current]) {
					if(v[next]) continue;
					ans[next]++;

					maxValue = Math.max(maxValue, ans[next]);
					queue.add(next);
					v[next] = true;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < ans.length; i++) {
			if(ans[i] == maxValue) sb.append(i + " " );
		}
		System.out.println(sb);
	}
}