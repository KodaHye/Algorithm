import java.io.*;
import java.util.*;

/*
 * 케빈 베이컨의 6단계 법칙
 */
public class BOJ1389 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		final int INF = 987654321;
		
		int N, M, map[][], d[]; // N: 유저의 수, M: 친구 관계의 수
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][N + 1];
		d = new int[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			map[a][b] = 1;
			map[b][a] = 1;
		}
		
		for (int r = 1; r < N + 1; r++) {
			for (int c = 1; c < N + 1; c++) {
				if(r != c && map[r][c] == 0) map[r][c] = INF;
			}
		}
		
		for(int j = 1; j < N + 1; j++) {
			for (int i = 1; i < N + 1; i++) {
				if(i == j) continue;
				for (int k = 1; k < N + 1; k++) {
					if(k == i || k == j) continue;
					map[i][k] = Math.min(map[i][k], map[i][j] + map[j][k]);
				}
			}
		}
		
		int max = Integer.MAX_VALUE;
		int result = 0;
		
		for(int i = N; i > 0; i--) {
			for(int j = 1; j < N + 1; j++) {
				d[i] += map[i][j];
			}
			if(max >= d[i]) {
				max = d[i];
				result = i;
			}
		}
		
		System.out.println(result);
	}
}
