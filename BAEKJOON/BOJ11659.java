import java.util.*;
import java.io.*;

/*
구간 합 구하기 4
 */

public class BOJ11659 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int sum[] = new int[N + 1];

		st = new StringTokenizer(br.readLine());

		for(int i = 1; i < N + 1; i++) {
			sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			sb.append(sum[e] - sum[s - 1] + "\n");
		}
		System.out.print(sb);
	}
}
