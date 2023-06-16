import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 비밀 메뉴
 */

public class Softeer214354 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int M, N, K, password[], input[]; // M개의 조작 버튼
	static String result;

	public static void main(String args[]) throws Exception {
		st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		password = new int[M];
		input = new int[N];
		result = "normal";

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < password.length; i++) {
			password[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < input.length; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = M - 1; i < N; i++) {
			boolean flag = true;

			for (int j = 0; j < M; j++) {
				if (input[i - M + j + 1] != password[j]) {
					flag = false;
					break;
				}
			}

			if (flag) {
				result = "secret";
				break;
			}
		}

		System.out.println(result);
	}
}