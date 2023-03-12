import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1010 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T, N, M, arr[][];

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 0; test_case < T; test_case++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());		
			
			int size = Math.max(N, M);
			arr = new int[size + 1][size + 1];
			
			for(int i = 0; i < arr.length; i++) {
				arr[i][i] = 1;
				arr[i][0] = 1;
				arr[i][1] = i;
			}
			
			for(int i = 2; i < arr.length; i++) {
				for(int j = 1; j < i; j++) {
					arr[i][j] = arr[i - 1][j] + arr[i - 1][j - 1];
				}
			}
			sb.append(arr[M][N] + "\n");
		}
		System.out.println(sb);
	}
}
