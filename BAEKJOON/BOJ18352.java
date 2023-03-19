import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 특정 거리의 도시 찾기
 */
public class BOJ18352 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, K, X;
	static ArrayList<Integer>[] adjList;
	static int[] v;
	static ArrayList<Integer> answer;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 도시의 개수
		M = Integer.parseInt(st.nextToken()); // 도로의 개수
		K = Integer.parseInt(st.nextToken()); // 거리 정보
		X = Integer.parseInt(st.nextToken()); // 출발 도시의 번호
		
		adjList = new ArrayList[N + 1];
		answer = new ArrayList<>();
		
		v = new int[N + 1];
		Arrays.fill(v, -1);
		
		for (int i = 0; i < N + 1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjList[a].add(b);
		}
		
		bfs(X);
		
		for (int i = 0; i < v.length; i++) {
			if(v[i] == K) answer.add(i);
		}
		
		if(answer.isEmpty()) System.out.println(-1);
		else {
			Collections.sort(answer);
			
			for (int ans : answer) {
				System.out.println(ans);
			}
		}
	}

	private static void bfs(int X) {
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(X);
		v[X]++;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			for (Integer i: adjList[now]) {
				if(v[i] == -1) {
					v[i] = v[now] + 1;
					queue.add(i);
				}
			}
		}
	}
}
