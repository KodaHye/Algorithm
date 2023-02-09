package D2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 * 파리 퇴치
 */
public class SWEA2001 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case < T + 1; test_case++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int max = 0;
			
			int[][] arr = new int[N][N];
			
			for(int i = 0; i < arr.length; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < arr.length; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < N - M + 1; i++) {
				for(int j = 0; j < N - M + 1; j++) {
					int tmp = 0;
					for(int r = 0; r < M; r++) {
						for(int c = 0; c < M; c++) {
							tmp += arr[i + r][j + c];
						}
					}
					max = Math.max(max, tmp);
				}
			}
			System.out.println("#" + test_case + " " + max);
		}
		
	}
}
