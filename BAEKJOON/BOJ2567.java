import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 스카프
 * 샘플 제작에 사용하는 검은색 스카프를 최소한만 사용
 * 입력
 * - N: 검은 스카프의 수
 * - 검은 스카프를 놓은 위치
 * 	- r: 검은색 스카프의 왼쪽 변과 흰색 천의 왼쪽 변 사이
 * 	- c: 검은색 스카프의 아래쪽 변과 흰색 천의 아래 쪽 변의 사이
 */

public class BOJ2567 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, r, c, map[][];
	static int dr[] = {-1 , 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static int result;
	
	public static void main(String[] args) throws Exception {
		map = new int[100][100];
		
		N = Integer.parseInt(br.readLine());
		
		// 스카프의 점 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			for(int x = r; x < r + 10; x++) {
				for(int y = c; y < c + 10; y++) {
					map[x][y] = 1;
				}
			}
		}
		// 스카프의 길이를 구하기 위해 
		// map에서 1이 나올 때 주변에 0을 세는 함수
		count();
		System.out.println(result);
	}
	
	// map 배열에서 값이 1을 때 사방 탐색을 하여 주변이 0이 있거나 경계선이 되었을 때 길이를 1씩 증가시킴
	private static void count() {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(map[r][c] == 1) {
					for(int i = 0; i < 4; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						if(!check(nr, nc)) {
							result++;
							continue;
						}
						if(map[nr][nc] == 0) {
							result++;
							continue;
						}
					}
				}
			}
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < 100 && nc >= 0 && nc < 100;
	}

	private static void print(int[][] map) {
		for(int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
}
