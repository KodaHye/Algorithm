import java.util.Scanner;

/*
 * 달팽이는 올라가고 싶다
 */
public class BOJ2869 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long A = sc.nextInt();
		long B = sc.nextInt();
		long V = sc.nextInt();
		
		long count = 0;
		count = (V - B) / (A - B);
		
		if((V - B) % (A - B) != 0) count++;
		System.out.println(count);
	}
}
