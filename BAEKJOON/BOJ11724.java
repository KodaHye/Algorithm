import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 연결 요소의 개수
 */
public class BOJ11724 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static ArrayList<Integer> adj[];
	static boolean v[];

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];
		v = new boolean[N + 1];

//		인접리스트 초기화
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adj[a].add(b);
			adj[b].add(a);
		}

		int count = 0;

		for (int i = 1; i < N + 1; i++) {
			if (!v[i]) {
				v[i] = true;
				count++;

				Queue<Integer> queue = new LinkedList<>();

				queue.add(i);

				while (!queue.isEmpty()) {
					int current = queue.poll();

					for (Integer integer : adj[current]) {
						if (!v[integer]) {
							v[integer] = true;
							queue.add(integer);
						}
					}
				}
			}
		}
		System.out.println(count);
	}
}
