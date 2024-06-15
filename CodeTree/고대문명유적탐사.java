import java.io.*;
import java.util.*;

/*
 * 고대 문명 유적 탐사   		
 */

public class 고대문명유적탐사 {
	static class Point implements Comparable<Point> {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Point o) {
			if (this.c == o.c)
				return -1 * Integer.compare(this.r, o.r);
			return Integer.compare(this.c, o.c);
		}
	}

	static class RotateInfo {
		Point p;
		int n;

		public RotateInfo(Point p, int n) {
			this.p = p;
			this.n = n;
		}
	}

	static StringBuilder sb = new StringBuilder();
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	static RotateInfo rotateInfo;
	static Queue<Point> queue;
	static PriorityQueue<Point> tmpQueue, pq;
	static int K, M, map[][], tmp[][], idx;
	static boolean v[][];
	static int arr[];

	public static void main(String[] args) throws Exception {
		initInput();
		solution();
	}

	private static void solution() {
		while (K-- > 0) {

			int cnt = 0;

			int findRotateMax = findRoateMax();
			if (findRotateMax == 0) break;

			rotate(rotateInfo.p.r, rotateInfo.p.c, rotateInfo.n);

			for (int r = 0; r < map.length; r++) map[r] = Arrays.copyOf(tmp[r], tmp[r].length);

			while (true) {
				pq.clear();
				int getMaxValue = getMaxValue();
				if (getMaxValue == 0) break;

				cnt += getMaxValue;
				while (!pq.isEmpty()) {
					Point current = pq.poll();

					tmp[current.r][current.c] = arr[idx];
					idx = (idx + 1) % arr.length;
				}
			}

			for (int r = 0; r < map.length; r++)
				map[r] = Arrays.copyOf(tmp[r], tmp[r].length);
			sb.append(cnt + " ");
		}

		System.out.println(sb);
	}

	private static int findRoateMax() {
		int cnt = 0;

		for (int k = 1; k < 1 + 3; k++) {
			for (int c = 1; c < 4; c++) {
				for (int r = 1; r < 4; r++) {
					int tmp = rotate(r, c, k);
					if (cnt < tmp) {
						cnt = tmp;
						modifyRotateInfo(r, c, k);
					}
				}
			}
		}

		return cnt;
	}

	private static int rotate(int i, int j, int n) {
		for (int r = 0; r < tmp.length; r++)
			tmp[r] = Arrays.copyOf(map[r], map[r].length);

		for (int r = i - 1; r < i + 2; r++) {
			for (int c = j - 1; c < j + 2; c++) {
				if (r == i && c == j)
					continue;

				int a = r - i;
				int b = c - j;
				if (n == 1) tmp[i + b][j - a] = map[r][c];
				else if (n == 2) tmp[i - a][j - b] = map[r][c];
				else if (n == 3) tmp[i - b][j + a] = map[r][c];
			}
		}

		return getMaxValue();
	}

	private static int getMaxValue() {
		for (int r = 0; r < v.length; r++) {
			Arrays.fill(v[r], false);
		}

		int total = 0;

		for (int r = 0; r < v.length; r++) {
			for (int c = 0; c < v[0].length; c++) {
				if (v[r][c]) continue;
				int tmp = bfs(r, c);
				if (tmp > 2) {
					total += tmp;
					pq.addAll(tmpQueue);
				}
			}
		}

		return total;
	}

	private static int bfs(int r, int c) {
		int cnt = 1;

		queue.clear();
		tmpQueue.clear();

		queue.add(new Point(r, c));
		tmpQueue.add(new Point(r, c));

		v[r][c] = true;
		while (!queue.isEmpty()) {
			Point current = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];

				if (!check(nr, nc) || v[nr][nc] || (tmp[nr][nc] != tmp[current.r][current.c]))
					continue;

				v[nr][nc] = true;
				queue.add(new Point(nr, nc));
				tmpQueue.add(new Point(nr, nc));
				cnt++;
			}
		}

		return cnt;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < 5 && nc >= 0 && nc < 5;
	}

	private static void modifyRotateInfo(int r, int c, int n) {
		rotateInfo.p.r = r;
		rotateInfo.p.c = c;
		rotateInfo.n = n;
	}

	private static void initInput() throws Exception {

		System.setIn(new FileInputStream("./input/고대문명유적탐사.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		rotateInfo = new RotateInfo(new Point(0, 0), 0);
		queue = new LinkedList<Point>();
		tmpQueue = new PriorityQueue<Point>();
		pq = new PriorityQueue<Point>();
		map = new int[5][5];
		tmp = new int[5][5];
		v = new boolean[5][5];
		K = Integer.parseInt(st.nextToken()); // 탐사 반복 횟수
		M = Integer.parseInt(st.nextToken()); // 벽면에 적힌 유물 조각의 개수

		String s = null;

		for (int r = 0; r < map.length; r++) {
			s = br.readLine();
			for (int c = 0; c < map[0].length; c++) {
				map[r][c] = s.charAt(c * 2) - '0';
			}
		}

		arr = new int[M];
		s = br.readLine();

		for (int i = 0; i < M; i++) arr[i] = s.charAt(i * 2) - '0';
	}
}
