import java.util.Scanner;

/*
 * 신기한 소수
 */
public class BOJ2023 {
	static int N;
	static StringBuilder sb;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		
		N =sc.nextInt();

		func(0, N);
		
		System.out.println(sb);
	}

	private static void func(int k, int n) {
		if(n == 0) {
			if(checkPrime(k)) sb.append(k).append("\n");
			
			return;
		}
		for(int i = 0; i < 10; i++) {
			int tmp = k * 10 + i;
			if(checkPrime(tmp)) func(tmp, n - 1);
		}
	}

	private static boolean checkPrime(int tmp) {
		if(tmp < 2) return false;
		for (int i = 2; i <= Math.sqrt(tmp); i++) {
			if(tmp % i == 0) return false;
		}
		return true;
	}
}