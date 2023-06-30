import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 결혼식
 */
public class BOJ5567 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static ArrayList<Integer> adj[];
	static boolean v[];
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[N + 1];
		v = new boolean[N + 1];
		
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adj[a].add(b);
			adj[b].add(a);
		}
		
		int start = 1;

		Queue<int []> queue = new LinkedList<>();
		
		queue.add(new int[] {start, 0});
		
		int count = 0;
		
		while(!queue.isEmpty()) {
			int current[] = queue.poll();
			
			for (Integer next : adj[current[0]]) {
				if(!v[next] && current[1] <= 2) {
					queue.add(new int[] {next, current[0] + 1});
					v[next] = true;
				}
			}
		}
		
		for(int i = 2; i < v.length; i++) {
			if(v[i]) count++;
		}
		System.out.println(count);
	}
}
