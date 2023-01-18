import java.util.Scanner;

public class 영화감독숌 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int count = 0;
		int min_num = 666;
		while(count < n) {
			if(Integer.toString(min_num).contains("666")) {
				count += 1;
			}
			min_num += 1;
		}
		System.out.println(min_num - 1);
	}

}
