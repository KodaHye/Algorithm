import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class BOJ2563 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		int[][] arr = new int[100][100];
		int count = 0;
		
		for(int i = 0; i < t; i++) {
			// 왼쪽 변과 도화지 왼쪽 변 사이의 거리
			int a = sc.nextInt();
			// 색종이 아래와 아래 변 사이의 거리
			int b = sc.nextInt();
			
			for(int j = a; j < a + 10; j++) {
				for(int k = b; k < b + 10; k++) {
					arr[j][k] += 1;
				}
			}

		}
		
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(arr[i][j] > 0) {
					count += 1;
				}
			}
		}
		System.out.println(count);
	}

}
