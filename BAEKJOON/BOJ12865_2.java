import java.io.*;
import java.util.*;

/*
 * 평범한 배낭
 */

public class BOJ12865_2 {
	
	static class Item {
		int w, v;
		public Item(int w, int v) {
			this.w = w;
			this.v = v;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 물품의 수
		int K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게
		
		Item[] items = new Item[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			items[i] = new Item(w, v);
		}
		
		// 배낭에 넣을 수 있는 물건들의 가치 합
		// K 무게까지 버틸 수 있음
		int[][] dp = new int[N + 1][K + 1];
		
		for(int i = 1; i < N + 1; i++) {
			for(int w = 1; w < K + 1; w++) {
				// 가방에 물건을 놓을 수 있는경우
				if(w >= items[i].w) {
					dp[i][w] = Math.max(items[i].v + dp[i - 1][w - items[i].w], dp[i - 1][w]);
				} 
				
				// 가방에 물건을 놓을 수 없는 경우
				else dp[i][w] = dp[i - 1][w];
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
