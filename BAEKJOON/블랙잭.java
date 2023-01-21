import java.util.Scanner;

public class 블랙잭 {
	/* 
	 * 카드의 합이 21이 넘지 않는 한도 내에서 가장 크게
	 * 각 카드: 양수, 딜러: N장의 카드를 모두 숫자가 보이게, 딜러: 숫자M을 외침
	 * N장의 카드 중 합이 M을 넘지 않는 카드 3장 뽑기
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int max = Integer.MIN_VALUE;
		
		int[] arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		for(int i = 0; i < arr.length - 2; i++) {
			int sum = 0;
			for (int j = i + 1; j < arr.length - 1; j++) {
				for (int k = j + 1; k < arr.length; k++) {
					sum = arr[i] + arr[j] + arr[k];
					if(sum <= M) {
						max = Math.max(max, sum);
					}
				}
			}
		}
		System.out.println(max + " ");
	}
}
