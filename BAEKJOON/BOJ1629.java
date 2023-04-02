import java.util.Scanner;

/*
 * 곱셈
 * 모듈러 연산
 * (A+B)%C == (A%C+B%C)
 * (A×B)%C == (A%C×B%C)
 */
public class BOJ1629 {
	static int A, B, C;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
		
		System.out.println(func(A, B));;
	}

	private static long func(int a, int b) {
		if(b == 1) {
			return a % C;
		} 
		long tmp = func(a, b / 2);
		
		if(b % 2 == 1) return (tmp * tmp % C) * a % C;
		return tmp * tmp % C;
	}
}
