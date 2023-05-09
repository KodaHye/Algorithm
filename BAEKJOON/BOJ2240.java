import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 자두나무
 */
public class BOJ2240 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, W, dp[][];

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken()); // 움직일 수 있는 횟수

		dp = new int[W + 1][T + 1];

		int tmp = Integer.parseInt(br.readLine());

		// 1초일 때
		for (int i = 0; i < W + 1; i++) {
			if ((tmp == 1 && i % 2 == 0) || (tmp == 2 && i % 2 == 1))
				dp[i][1]++;

		}

		// 1초 이후 값 채우기
		for (int i = 2; i < T + 1; i++) {
			tmp = Integer.parseInt(br.readLine());

			for (int j = 0; j < W + 1; j++) {
				if (j == 0) {
					dp[j][i] = dp[j][i - 1];
					if (tmp == 1)
						dp[j][i]++;

				} else {
					int a = dp[j - 1][i - 1];
					int b = dp[j][i - 1];

					// (j % 2 == 1 && tmp == 2): 1인 나무
					// (j % 2 == 0 && tmp == 1): 2인 나무
					if ((j % 2 == 1 && tmp == 2) || (j % 2 == 0 && tmp == 1)) {
						a++;
						b++;
					}

					dp[j][i] = Math.max(a, b);
				}
			}
		}

		int max = 0;
		for (int i = 0; i < W + 1; i++) {
			max = Math.max(dp[i][T], max);
		}

		System.out.println(max);
	}
}
