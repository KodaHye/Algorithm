import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 이분 그래프
 * 그래프의 정점의 집합을 둘로 분할하여, 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할할 수 있을 때, 그러한 그래프를 이분그래프라고 함
 * 이분그래프라면 YES, 아니라면 NO 출력
 */

public class BOJ1707 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int K, V, E;
	static ArrayList<Integer>[] adjList;
	static boolean v[];
	static int check[];
	static boolean isEven;
	public static void main(String[] args) throws Exception {
		K = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case < K + 1; test_case++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken()); // V: 정점의 개수
			E = Integer.parseInt(st.nextToken()); // E: 간선의 개수
			
			adjList = new ArrayList[V + 1];
			v = new boolean[V + 1];
			check = new int[V + 1];
			isEven = true;
			
			for(int i = 0; i < adjList.length; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				adjList[a].add(b);
				adjList[b].add(a);
			}
			
			for(int i = 1; i < V + 1; i++) {
				if(isEven) {
					dfs(i);
				} else {
					break;
				}
			}
			
			String result = isEven == true ? "YES" : "NO";
			System.out.println(result);
		}
	}

	private static void dfs(int node) {
		v[node] = true;
		
		for(int i: adjList[node]) {
			if(!v[i]) {
				check[i] = (check[node] + 1) % 2;
				dfs(i);
			}
			else if(check[node] == check[i]) {
				isEven = false;
			}
		}
	}
}
