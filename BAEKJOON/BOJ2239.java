import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 스도쿠
 */
public class BOJ2239 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[][] map = new int[9][9];
	static ArrayList<int[]> list;
	public static void main(String[] args) throws Exception {
		list = new ArrayList<>();
		
		for(int r = 0; r < map.length; r++) {
			String input = br.readLine();
			for(int c = 0; c < map[r].length; c++) {
				map[r][c] = input.charAt(c) - '0';
				if(map[r][c] == 0) list.add(new int[] {r, c});
			}
		}

		// list의 idx
		func(0);
	}
	
	private static void func(int k) {
		if(k == list.size()) {
			print(map);
			System.exit(0);
			return;
		}
		
		for(int i = 1; i <= 9; i++) {

			int r = list.get(k)[0];
			int c = list.get(k)[1];
			
			// map의 값을 바꾸기 전에 확인하기!!
			if(!check(r, c, i)) continue;
			map[r][c] = i;
			func(k + 1);
			// 값 원복하기! 원하는 값이 아니라면 원복 해야 됨! 
			// 왕 중요
			map[r][c] = 0;

		}
	}

	// 해당 점에서 스도쿠가 올바른지 확인
	// 가로, 세로, 사각형을 비교하면서 올바르지 않다면 false return
	private static boolean check(int r, int c, int num) {
		int[] nums = new int[10]; // 0 ~ 9 idx의 배열
		// 가로, 세로 확인
		for(int i = 0; i < 9; i++) {
			nums[map[i][c]]++;
			nums[map[r][i]]++;
		}
		
		int Sr = r / 3 * 3;
		int Sc = c / 3 * 3;
		
		for(int i = Sr; i < Sr + 3; i++) {
			for(int j = Sc; j < Sc + 3; j++) {
				nums[map[i][j]]++;
			}
		}
		for(int i = 1; i < nums.length; i++) {
			if(nums[i] > 3 || nums[num] != 0) return false;
		}
		return true;
	}

	private static void print(int[][] map) {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				System.out.print(map[r][c]);
			}System.out.println();
		}
	}
}
