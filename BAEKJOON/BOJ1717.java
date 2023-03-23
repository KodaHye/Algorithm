import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 집합의 표현
 * n + 1개의 집합이 있을 때, 합집합 연산과, 두 원소가 같은 집합에 포함되어 있는지 확인하는 연산을 수행하려고 함
 * 집합을 표현하는 프로그램 작성
 * 입력
 * n, m: 연산의 개수
 * 0 a b: union(a, b)
 * 1 a b: find(a, b)
 */

public class BOJ1717 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int n, m, a, b, parent[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
			
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parent = new int[n + 1];
		
		// 초기화(처음 부모를 자기 자신으로 설정)
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}

		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int cal = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(cal == 0) union(a, b);
			else {
				if(find(a) == find(b)) sb.append("YES" + "\n");
				else sb.append("NO" + "\n");
			};
		}
		
		System.out.println(sb);
	}

	private static int find(int a) {
		return parent[a] = a == parent[a] ? a : find(parent[a]);	// 
	}
	
	// union연산
	// 각 노드의 대표노드를 찾아서 연결
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) {
			parent[b] =a;
		}
	}
}
