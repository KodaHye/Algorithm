import java.util.Arrays;
import java.util.Scanner;

/*
 * 부녀회장이 될테야
 * a층의 b호에 살려면 자신의 아래 (a-1)층의 1호부터 b호까지 사람들의 수의 합만큼 사람들을 데려와 살아야 함
 * k와 n에 대해 k층에 n호에는 몇 명이 살고 있는지 출력, 0층의 i호에는 i명이 삶
 * 
 */
public class BOJ2775 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int test_case = 1; test_case < T + 1; test_case++) {
			int k = sc.nextInt();
			int n = sc.nextInt();
			
			int map[][] = new int[k + 1][n];
			
			for(int i = 0; i < n; i++) map[0][i] = i + 1;
			
			for(int i = 1; i <= k; i++) {
				int tmp = 0;
				
				for (int j = 0; j < n; j++) {
					tmp += map[i - 1][j];
					map[i][j] = tmp;
				}
			}
			
//			print(map);
			System.out.println(map[k][n - 1]);
		}
	}

	private static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		
	}
}
