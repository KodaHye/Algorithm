import java.io.*;
import java.util.*;

/*
 * 백양로 브레이크
 * 출발지와 도착지를 입력하면 도착지로 가기 위해 최소 몇개의 길을 양방향으로 바꿔야 되는지 
 */

public class BOJ11562 {

	static final int INF = Integer.MAX_VALUE, SINGLE = 1, DOUBLE = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 건물의 수
		int M = Integer.parseInt(st.nextToken()); // 길의 수
		
		int[][] roadStatus = new int[N + 1][N + 1];
		
		for(int r = 0; r < N + 1; r++) {
			for(int c = 0; c < N + 1; c++) {
				if(r == c) continue;
				roadStatus[r][c] = INF;
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			roadStatus[u][v] = 0;
			roadStatus[v][u] = 0;
			
			// 일방통행
			if(b == 0) roadStatus[v][u] = 1;
		}
		
		int k = Integer.parseInt(br.readLine());

		for(int l = 1; l < N + 1; l++) { // 경유지
			for(int s = 1; s < N + 1; s++) { // 출발지
				
				if(l == s || roadStatus[s][l] == INF) continue;
				
				for(int e = 1; e < N + 1; e++) {
					if(l == e || s == e || roadStatus[l][e] == INF) continue;
					
					roadStatus[s][e] = Math.min(roadStatus[s][e], roadStatus[s][l] + roadStatus[l][e]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(k-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			sb.append(roadStatus[s][e]).append("\n");
		}
		
		System.out.println(sb);
	}
}
