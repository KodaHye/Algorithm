import java.io.*;
import java.util.*;

/*
 * 마법사의 숲 탐색
 */

public class 마법사의숲탐색 {
	static class Node {
		int r, c, d;
		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static int[][] map, door;
	static int R, C, K, result;
	static Node nodes[];
	
	static void solution() {
		for(int i = 1; i < nodes.length; i++) {
			while(true) {
				if(moveDown(nodes[i])) continue;
				if(move(nodes[i], 3)) continue;
				if(move(nodes[i], 1)) continue;
				break;
			}
			if(nodes[i].r < 4) {
				initMap();
				continue;
			}
			
			checkMap(nodes[i], i);
			result += bfs(nodes[i], i);
		}
		System.out.println(result);
	}
	
    private static int bfs(Node n, int id) {
        int result = n.r;
        
        ArrayDeque<int[]> q = new ArrayDeque<int[]>();
        boolean[][] visit = new boolean[map.length][map[0].length];
        q.offer(new int[]{n.r, n.c});
        visit[n.r][n.c] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int k = 0; k < 4; k++) {
                int nr = cur[0] + dr[k], nc = cur[1] + dc[k];

                if(!check(nr, nc)) continue;
                if (!visit[nr][nc] && (map[nr][nc] == map[cur[0]][cur[1]] || (map[nr][nc] != 0 && door[cur[0]][cur[1]] != 0))) {
                    q.offer(new int[]{nr, nc});
                    visit[nr][nc] = true;
                    result = Math.max(result, nr);
                    
                }
            }
        }
        
        return result - 2;
    }
	static void checkMap(Node n, int idx) {
		// door 표시
		door[n.r + dr[n.d]][n.c + dc[n.d]] = idx;
		
		map[n.r][n.c] = idx;
		for(int d = 0; d < 4; d++) {
			int nr = n.r + dr[d];
			int nc = n.c + dc[d];
			
			map[nr][nc] = idx;
		}
	}
	
	static void initMap() {
		door = new int[R][C];
		map = new int[R][C];
	}
	static boolean moveDown(Node n) {
		int checkR = n.r + dr[2];
		int checkC = n.c + dc[2];
		
		for(int d = -1; d < 2; d++) {
			int nd = 2 + d;
			int nr = checkR + dr[nd];
			int nc = checkC + dc[nd];
			
			if(!check(nr, nc) || map[nr][nc] != 0) return false;
		}

		n.r = checkR;
		n.c = checkC;
		
		return true;
	}
	
	static boolean move(Node n, int dir) {
		int checkR = n.r + dr[dir];
		int checkC = n.c + dc[dir];
		
		// idx == 3 이면 서쪽
		// idx == 1이면 동쪽
		
		for(int checkDir = -1; checkDir < 2; checkDir++) {
			int nd = (dir + checkDir + 4) % 4;
			
			int nr = checkR + dr[nd];
			int nc = checkC + dc[nd];
			
			if(!check(nr, nc) || map[nr][nc] != 0) return false;
		}
		

		checkR += 1;
		int checkDir;
		// n.dir 위치 변경해주기
		// idx == 3이면 반시계 방향
		
		int tmpD = n.d;
		if(dir == 3) {
			tmpD = (n.d - 1 + 4) % 4;
			checkDir = 2;
		}
		else {
			tmpD = (n.d + 1) % 4;
			checkDir = 1;
		}
		
		for(int check = checkDir; check < checkDir + 2; check++) {
			int nr = checkR + dr[check];
			int nc = checkC + dc[check];
			
			if(!check(nr, nc) || map[nr][nc] != 0) return false;
		}
		
		
		n.r = checkR;
		n.c = checkC;
		n.d = tmpD;
		
		return true;
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
	}
	
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
	}
	
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/마법사의숲.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken()) + 3;
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		initMap();
	
		nodes = new Node[K + 1];
		
		for(int i = 1; i < nodes.length; i++) {
			st= new StringTokenizer(br.readLine());
			
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			
			nodes[i] = new Node(1, c, d);
		}
	}
}
