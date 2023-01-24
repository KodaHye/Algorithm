import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기4 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./BAEKJOON/Input/구간합구하기_4.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long[] S = new long[N + 1];
		
		// 배열 입력
		st = new StringTokenizer(br.readLine());
		
		for (int k = 1; k < N + 1; k++) {
			S[k] = S[k - 1] + Integer.parseInt(st.nextToken());
		}

		for(int k = 0; k < M; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			sb.append((S[j] -S[i - 1]) + "\n");
		}
		System.out.println(sb);
	}

}
