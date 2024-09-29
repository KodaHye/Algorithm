import java.io.*;
import java.util.*;

/*
 * 어른 상어
 * hashset을 사용할 때, forEach랑 remove를 같이 사용하지 말자
 * 크기가 정확하게 정해져있다면, hashset보다는 배열을 사용하자
 */

public class BOJ19237 {
	static class Smell {
		int sharkNum, time;
		public Smell(int sharkNum, int time) {
			this.sharkNum = sharkNum;
			this.time = time;
		}
	}
	static class Shark {
		boolean isDie;
		int r, c, dir;
		int[][] dirPriority;
		public Shark(int r, int c, int dir, int[][] dirPriority) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.dirPriority = dirPriority;
		}
	}
	static int[] dr = {0, -1, 1, 0, 0}, dc = {0, 0, 0, -1, 1};
	static Smell[][] smellMap;
	static int[][] sharkMap;
	static Shark[] sharks;
	static BufferedReader br;
	static StringTokenizer st;
	static int N, M, K, leave;

	static void solution() {
		int time = 0;
		while(time < 1_001) {
			moveSharks();
			updateSmellTime();
			time++;
			if(leave == 1) break;
		}

		System.out.println(time > 1_000? -1: time);
	}

	static void updateSmellTime() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(smellMap[r][c].sharkNum == 0) continue;
				smellMap[r][c].time--;
				if(smellMap[r][c].time == 0) smellMap[r][c].sharkNum = 0;
			}
		}

		for(Shark s: sharks) {
			if(s.isDie) continue;
			smellMap[s.r][s.c].sharkNum = sharkMap[s.r][s.c];
			smellMap[s.r][s.c].time = K;
		}
	}
	static void move(int idx, int r, int c, int nr, int nc, int d) {
		sharkMap[r][c] = 0;
		sharks[idx - 1].r = nr;
		sharks[idx - 1].c = nc;
		sharks[idx - 1].dir = d;
		sharkMap[nr][nc] = idx;
	}

	static boolean isExistSmallNumShark(int r, int c, int nr, int nc, int idx) {
		if(sharkMap[nr][nc] != 0 && sharkMap[nr][nc] < idx) {
			sharkMap[r][c] = 0;
			sharks[idx - 1].isDie = true;
			leave--;
			return true;
		}
		return false;
	}
	static void moveShark(Shark s) {
		int idx = sharkMap[s.r][s.c];

		for(int d = 0; d < 4; d++) {
			int nr = s.r + dr[s.dirPriority[s.dir - 1][d]];
			int nc = s.c + dc[s.dirPriority[s.dir - 1][d]];
			if(!check(nr, nc) || smellMap[nr][nc].sharkNum != 0) continue;
			if(isExistSmallNumShark(s.r, s.c, nr, nc, idx)) return;
			int dirTmp = getDir(idx, s.dir, d);
			move(idx, s.r, s.c, nr, nc, dirTmp);
			return;
		}

		for(int d = 0; d < 4; d++) {
			int nr = s.r + dr[s.dirPriority[s.dir - 1][d]];
			int nc = s.c + dc[s.dirPriority[s.dir - 1][d]];

			if(!check(nr, nc) || smellMap[nr][nc].sharkNum != idx) continue;

			if(isExistSmallNumShark(s.r, s.c, nr, nc, idx)) return;
			int dirTmp = getDir(idx, s.dir, d);
			move(idx, s.r, s.c, nr, nc, dirTmp);
			return;
		}
	}

	static int getDir(int idx, int dir, int d) {
		int ddr = dr[sharks[idx - 1].dirPriority[dir - 1][d]];
		int ddc = dc[sharks[idx - 1].dirPriority[dir - 1][d]];

		if(ddr == -1 && ddc == 0) return 1;
		if(ddr == 1 && ddc == 0) return 2;
		if(ddr == 0 && ddc == -1) return 3;
		else return 4;
	}
	static void moveSharks() {
		for(Shark s: sharks) {
			if(s.isDie) continue;
			moveShark(s);
		}
	}

	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // map의 크기
		M = Integer.parseInt(st.nextToken()); // 상어의 수
		K = Integer.parseInt(st.nextToken()); // 냄새 유지 시간

		leave = M;
		sharks = new Shark[M];
		sharkMap = new int[N][N];
		smellMap = new Smell[N][N];

		for(int r = 0; r < N; r++) for(int c = 0; c < N; c++) {
			smellMap[r][c] = new Smell(0, 0);
		}

		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				sharkMap[r][c] = Integer.parseInt(st.nextToken());
				if(sharkMap[r][c] == 0) continue;
				sharks[sharkMap[r][c] - 1] = new Shark(r, c, 0, new int[4][4]);
				smellMap[r][c].sharkNum = sharkMap[r][c];
			}
		}

		st = new StringTokenizer(br.readLine());
		for(Shark s: sharks) {
			s.dir = Integer.parseInt(st.nextToken());
			smellMap[s.r][s.c].time = K;
		}

		for(int i = 1; i < M + 1; i++) {
			for(int d = 0; d < 4; d++) {
				st = new StringTokenizer(br.readLine());
				setSharkDir(i, d, st);
			}
		}
		solution();
	}

	static void setSharkDir(int idx, int d, StringTokenizer st) {
		for(int i = 0; i < 4; i++) {
			sharks[idx - 1].dirPriority[d][i] = Integer.parseInt(st.nextToken());
		}
	}
}