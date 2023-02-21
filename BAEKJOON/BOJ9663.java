
import java.util.Scanner;

/*
 * N-Queen
 * N x N 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제
 */
public class BOJ9663 {
	static Scanner sc = new Scanner(System.in);
	static int N;
	static int[][] arr;
	static int count;

	public static void main(String[] args) {
		N = sc.nextInt();
		arr = new int[N][N];

		// 행의 번호
		func(0);
		
		System.out.println(count);
	}

	private static void func(int r) {
		if (r == N) {
			count++;
			return;
		}
		// 행을 하나씩 처리해 주고,
		// 해당 행에서 열들을 check 해준다
		// queen은 위에서부터 놓는다고 가정한다.
		// queen이 있으면 1, 없으면 0
		for (int c = 0; c < N; c++) {
			if (!check(r, c)) continue;
			arr[r][c] = 1;
			func(r + 1);
			// 원상복구
			arr[r][c] = 0;
		}
	}

	private static boolean check(int r, int c) {
		// 상
		for(int i = r - 1; i >= 0; i--) {
			if(arr[i][c] == 1) return false;
		}
		
		// 좌상
		for(int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
			if(arr[i][j] == 1) return false;
		}
		// 우상
		for(int i = r - 1, j = c + 1; i >= 0 && j < N; i--, j++) {
			if(arr[i][j] == 1) return false;
		}
		return true;
	}
}
