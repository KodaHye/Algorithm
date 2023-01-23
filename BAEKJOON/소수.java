import java.util.Scanner;

public class 소수 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int M = sc.nextInt();
		int N =sc.nextInt();
		int min = Integer.MAX_VALUE;
		int sum = 0;
		int result = -1;
		
		for(int i = M; i < N + 1; i++) {
			int count = 0;
			for(int j = 1; j <= i; j++) {
				if(i % j == 0) {
					count+= 1;
				}
			}
			if(count == 2) {
				min = Math.min(min, i);
				sum += i;
				result = 0;
			}
			if(count == 0) System.out.println(-1);
		}
		if(result == -1) System.out.println(result);
		else System.out.println(sum + "\n" + min);
	}
}
