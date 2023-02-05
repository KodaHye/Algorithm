import java.util.Scanner;

public class BOJ1934 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		

		for(int test_case = 1; test_case < T + 1; test_case++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int result = 1;
			
			int mod = 2;
			while(true) {
				if(A % mod == 0 && B % mod == 0) {
					A /= mod;
					B /= mod;
					result *= mod;
				}
				if(A % mod != 0 || B % mod != 0) {
					mod++;
				}
				if(mod > A || mod > B) {
					result *= (A * B);
					break;
				}
			}
			System.out.println(result);
		}
	}
}
