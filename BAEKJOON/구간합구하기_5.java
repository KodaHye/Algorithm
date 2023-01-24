import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기_5 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./BAEKJOON/Input/구간합구하기_5.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 배열의 크기
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] A = new int[N + 1][N + 1];
		for(int tmp1 = 1; tmp1 < N + 1; tmp1++) {
			st = new StringTokenizer(br.readLine());
			for(int tmp2 = 1; tmp2 < N + 1; tmp2++) {
				A[tmp1][tmp2] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] S = new int[N + 1][N + 1];
		
	
		for(int tmp1 = 1; tmp1 < N + 1; tmp1++) {
			for(int tmp2 = 1; tmp2 < N + 1; tmp2++) {
//				S[tmp1][tmp2] = S[tmp1][tmp2 - 1] + Integer.parseInt(st.nextToken());
				S[tmp1][tmp2] = S[tmp1][tmp2 - 1] + S[tmp1 - 1][tmp2] - S[tmp1 - 1][tmp2 - 1] + A[tmp1][tmp2];
			}
		}
		
		for(int test_case = 0; test_case < M; test_case++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int result = 0;
			
//			for(int tmp = x1; tmp < x2 + 1; tmp++) {
//				result += (S[tmp][y2] - S[tmp][y1 - 1]);
//			}
			
			result = S[x2][y2] - S[x1-1][y2] - S[x2][y1 - 1] + S[x1 - 1][y1 - 1];
			System.out.println(result);
		}
		
	}

}
