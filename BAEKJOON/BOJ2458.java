import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 키 순서
 */
public class BOJ2458 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, arr[][];
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1][N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[a][b] = 1;
		}
		
		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i++) {
				if(i == k || arr[i][k] == 0) continue; // 경유지와 출발지가 같으면 필요 X
				for (int j = 1; j < N + 1; j++) {
					// i < k < j: 학생 i보다 학생 k가 키가 크고, 학생 k보다 학생 j가 i보다 j의 키가 큼
					if(arr[i][j] == 1) continue;
					arr[i][j] = arr[i][k] & arr[k][j];
				}
			}
		}
		
		int count = 0;
		
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				arr[i][0] += arr[i][j]; // 자신보다 키가 큰 학생수 누적
				arr[0][j] += arr[i][j]; // 자신보다 키가 작은 학생수 누적
			}
		}

		for (int i = 1; i < N + 1; i++) {
			if(arr[i][0] + arr[0][i] == N - 1) count++;
		}
		System.out.println(count);
	}
}
