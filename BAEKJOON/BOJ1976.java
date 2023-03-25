import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 여행 가자
 * 동혁이의 여행 일정이 주어졌을 때, 여행 경로가 가능한 것인지 출력
 * N: 도시의 수
 * M: 여행 계획에 속한 도시들의 수
 */
public class BOJ1976 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, dosi[][], p[];
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		p = new int[N + 1];
		dosi = new int[N + 1][N + 1];
		
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				dosi[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 초기화
		for(int i = 0; i < p.length; i++) {
			p[i] = i;
		}
		
		for (int i = 1; i < N + 1; i++) {
			for (int j = 0; j < N + 1; j++) {
				if(dosi[i][j] == 1) union(i, j);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int first = p[Integer.parseInt(st.nextToken())];

		for(int i = 1; i < M; i++) {
			if(first != p[Integer.parseInt(st.nextToken())]) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}

	private static void union(int i, int j) {
		i = find(i);
		j = find(j);
		
		// 부모요소를 찾음
		if(i != j) {
			if(i < j) p[j] = i;
			else p[i] = j;
		}
	}

	private static int find(int i) {
		return p[i] = p[i] == i ? i : find(p[i]);
	}
}