import java.util.Scanner;

public class D2_2001 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case < T + 1; test_case++) {
			int max = 0;
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			int[][] arr = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			for(int i = 0; i < n - m + 1; i++) {
				for(int j = i; i < n + m + 1; j++) {
					int total = 0;
					for(int k = i; k < i + m; k++) {
					}
					if(max < total) max = total;
				}
			}
			
			System.out.println(max);
		}
	}

}
