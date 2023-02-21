package D2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1974 {
	static int[][] map = new int[9][9];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case < T + 1; test_case++) {
			int result = 1;
			boolean flag = true;
			for (int i = 0; i < 9; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {
					int[] nums = new int[10]; // 0 ~ 9 idx의 배열
					// 가로, 세로 확인
					for (int i = 0; i < 9; i++) {
						nums[map[i][c]]++;
						nums[map[r][i]]++;
					}

					int Sr = r / 3 * 3;
					int Sc = c / 3 * 3;

					for (int i = Sr; i < Sr + 3; i++) {
						for (int j = Sc; j < Sc + 3; j++) {
							nums[map[i][j]]++;
						}
					}
					for (int i = 1; i < nums.length; i++) {
						if (nums[i] > 3)
							flag = false;
						result = 0;
					}
					result = 1;
				}
			}

			result = flag == false ? 0 : 1;
			sb.append("#" + test_case + " " + result + "\n");
		}
		System.out.println(sb);
	}

}
