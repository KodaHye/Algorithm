import java.util.Scanner;

public class 분해합 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int result = 0;
		for(int i = 0; i < n; i++) {
			int tmp = 0;
			tmp += i;
			for(int j = 0; j < Integer.toString(i).length(); j++) {
				tmp += (int)(Integer.toString(i).charAt(j) - '0'); 
//				System.out.println(tmp);				
			}

//			System.out.println(tmp);
			if(tmp == n) {
				result = i;
				break;
			}
		}
		System.out.println(result);
	}

}
