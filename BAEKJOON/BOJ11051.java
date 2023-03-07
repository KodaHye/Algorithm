import java.util.Scanner;

/*
 * 이항 계수 2
 */
public class BOJ11051 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K= sc.nextInt();
		int D[][] = new int[N + 1][N + 1];
		
		for(int i = 0; i < N + 1; i++) {
			D[i][0] = 1;
			D[i][1] = i;
			D[i][i] = 1;
		}
		
		for(int i = 2; i < N + 1; i++) {
			for(int j = 1; j < i; j++) {
				D[i][j] = D[i - 1][j] + D[i - 1][j - 1];
				D[i][j] %= 10007;
			}
		}
		
		System.out.println(D[N][K]);
	}
}
