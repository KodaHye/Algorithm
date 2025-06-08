import java.io.*;
import java.util.*;

/*
 * 주사위 쌓기
 */

public class BOJ2116 {
	
	static class Dice { // 주사위 정보
		int[] num, idx;
		
		public Dice(int[] num, int[] idx) {
			this.num = num;
			this.idx = idx;
		}
	}
	
	static Dice[] dices;
	public static void main(String[] args) throws Exception {
		
		initInput();
		System.out.println(solution());
	}
	
	static int solution() {
		int result = 0;
		
		// 맨 바닥 숫자가 1일때부터 6일 때까지
		for(int i = 1; i <= 6; i++) {
			
			int tmp = 0;
			
			int bottom = i;
			
			for(Dice d: dices) {

				int[] info = getInfo(d, bottom);
				
				tmp += info[0];
				bottom = info[1];
			}
			
			result = Math.max(result, tmp);
		}
		
		return result;
	}
	
	// num이 아래 위치에 있을 때, 가능한 4방면에서 최댓값
	static int[] getInfo(Dice d, int num) {
		
		int idx = d.idx[num]; // num의 인덱스를 구함
		
		int excepIdx = -1;
		
		if(idx == 0) excepIdx = 5;
		if(idx == 1) excepIdx = 3;
		if(idx == 2) excepIdx = 4;
		if(idx == 3) excepIdx = 1;
		if(idx == 4) excepIdx = 2;
		if(idx == 5) excepIdx = 0;
		
		int maxValue = 0;
		
		for(int i = 0; i < 6; i++) {
			if(i == idx || i == excepIdx) continue;
			maxValue = Math.max(maxValue, d.num[i]);
		}
		
		return new int[] {maxValue, d.num[excepIdx]};
	}
	
	static void printDiceInfo() {
		for(Dice d: dices) {
			System.out.println(Arrays.toString(d.num) + " " + Arrays.toString(d.idx));
		}
	}
	
	static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		dices = new Dice[n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			int[] num = new int[6];
			int[] idx = new int[7];

			for(int j = 0; j < 6; j++) {
				num[j] = Integer.parseInt(st.nextToken());
				idx[num[j]] = j; // num[j]는 j번째 idx에 있음
			}
			
			dices[i] = new Dice(num, idx);
		}
	}
}
