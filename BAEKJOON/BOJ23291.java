import java.io.*;
import java.util.*;

/*
 * 어항 정리
 */

public class BOJ23291 {
	static BufferedReader br;
	static StringTokenizer st;
	static int N, K, minValue;
	static int[] arr, tmp2, dr = {0, -1, 0, 1}, dc = {-1, 0, 1, 0};
	static int[][] tmp1;

	static void solution() {
		int result = 0;
		while(getDiffArr() > K) {
			result++;
			initTmp();
			getDiffArr();
			putFishMinSpace();
			setTmp();
			arrangeFishCnt1(true);
			updateArr(true);
			arrangeFishCnt2();
			arrangeFishCnt1(false);
			updateArr(false);
		}
		
		System.out.println(result);
	}
	
	static void arrangeFishCnt2() {
		int[][] arrangeTmp = new int[2][arr.length / 2];

		for(int r = 0; r < arr.length / 2; r++) {
			arrangeTmp[0][arrangeTmp[0].length - 1 - r] = arr[r];
		}

        if (arr.length - arr.length / 2 >= 0)
            System.arraycopy(arr, arr.length / 2, arrangeTmp[1], 0, arr.length - arr.length / 2);
		
		tmp1 = new int[4][arrangeTmp[0].length / 2];
		for(int r = 0; r < arrangeTmp.length; r++) {
			for(int c = 0; c < arrangeTmp[0].length; c++) {
				if(c < tmp1[0].length) {
					tmp1[2 - r - 1][tmp1[0].length - 1 - c] = arrangeTmp[r][c];
				} else {
					tmp1[r + 2][c - tmp1[0].length] = arrangeTmp[r][c];
				}
			}
		}
			
		updateArr(false);
	}
	
	static void updateArr(boolean flag) {
		int idx = 0;
		for(int c = 0; c < tmp1[0].length; c++) {
			for(int r = tmp1.length - 1; r >= 0; r--) {
				arr[idx++] = tmp1[r][c];
			}
		}
		
		if(!flag) return;
		for(int value: tmp2) arr[idx++] = value;
	}
	static void arrangeFishCnt1(boolean flag) {
		int[][] diffTmp1 = new int[tmp1.length][tmp1[0].length];
		
		for(int r = 0; r < tmp1.length; r++) {
			for(int c = 0; c < tmp1[0].length; c++) {
				for(int dir = 0; dir < 2; dir++) {
					int nr = r + dr[dir];
					int nc = c + dc[dir];
					
					if(!check(nr, nc)) continue;
					int d = Math.abs(tmp1[nr][nc] - tmp1[r][c]) / 5;						
					
					if(d > 0) {
						if(tmp1[nr][nc] > tmp1[r][c]) {
							diffTmp1[nr][nc] -= d;
							diffTmp1[r][c] += d;
						} else {
							diffTmp1[nr][nc] += d;
							diffTmp1[r][c] -= d;
						}
					}
				}
			}
		}
		
		int[] diffTmp2 = new int[tmp2.length];
		if(flag) {
			for(int r = 0; r < tmp2.length; r++) {
				int prev = 0;
				if(r == 0) {
					prev = tmp1[tmp1.length - 1][tmp1[0].length - 1];
					
					int d = Math.abs(prev - tmp2[r]) / 5;
					if(d > 0) {
						if(prev > tmp2[r]) {
							diffTmp1[tmp1.length - 1][tmp1[0].length - 1] -= d;
							diffTmp2[r] += d;
						} else {
							diffTmp1[tmp1.length - 1][tmp1[0].length - 1] += d;
							diffTmp2[r] -= d;
						}
					}
				} else {
					prev = tmp2[r - 1];
					
					int d = Math.abs(prev - tmp2[r]) / 5;
					if(prev > tmp2[r]) {
						diffTmp2[r - 1] -= d;
						diffTmp2[r] += d;
					} else {
						diffTmp2[r - 1] += d;
						diffTmp2[r] -= d;
					}
				}
			}		
			
		}
		
		for(int r = 0; r < diffTmp1.length; r++) {
			for(int c = 0; c < diffTmp1[0].length; c++) {
				tmp1[r][c] += diffTmp1[r][c];
			}
		}
		
		if(flag) {
			for(int r = 0; r < diffTmp2.length; r++) {
				tmp2[r] += diffTmp2[r];
			}
		}
	}
	
	static void setTmp() {
		int arrIdx = arr.length - 1;
		
		for(int i = tmp2.length - 1; i >= 0; i--) {
			tmp2[i] = arr[arrIdx--];
		}
			
		int r = tmp1.length - 1, c = tmp1[0].length - 1;
		int d = 0;
		
		while(true) {
			tmp1[r][c] = arr[arrIdx--];
			
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(!check(nr, nc) || tmp1[nr][nc] != 0) {
				d = (d + 1) % 4;
				nr = r + dr[d];
				nc = c + dc[d];
				if(tmp1[nr][nc] != 0) break;
			}
			
			r = nr; c = nc;
		}
	}
	static boolean check(int r, int c) {
		return r >= 0 && r < tmp1.length && c >= 0 && c < tmp1[0].length;
	}
	
	static void putFishMinSpace() {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] != minValue) continue;
			arr[i] += 1;
		}
	}
	static int getDiffArr() {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for(int a: arr) {
			max = Math.max(max, a);
			min = Math.min(min, a);
		}
		
		minValue = min;
		return max - min;
	}
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 상어가 가지고 있는 어항 개수 (4의 배수)
		K = Integer.parseInt(st.nextToken()); // 어항의 물고기 수 차이가 K 이하가 된다면 정리 X
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		solution();
	}
	
	static void initTmp() {
		int i, j = 0;
		int tmpR = 0, tmpC = 0;
		for(i = 1; i * j <= N; i++) {
			for(j = i - 1; j <= i; j++) {
				if(j == 0) continue;
				if(i * j > N) break;
				
				tmpR = i;
				tmpC = j;
			}
			j = i - 1;
		}
			
		tmp1 = new int[tmpR][tmpC];
		tmp2 = new int[N - tmpR * tmpC];
	}
}
