import java.util.*;
import java.io.*;

/*
 * 마법사의 숲 탐색
 */

public class 마법의숲탐색 {
	static class Info {
		int num;
		boolean exit;
		
		public Info(int num, boolean exit) {
			this.num = num;
			this.exit = exit;
		}
	}
	static class Point {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
    static class Golam {
        int exit;
        Point p, p1, p2, p3;
        
        public Golam(Point p) {
        	this.p = p;
        }
        public Golam(Point p, int exit) {
        	this.p = p;
            this.exit = exit;
        }
    }

    static Queue<Golam> queue = new LinkedList<Golam>();
    static ArrayList<Golam> list;
    static Info map[][];
    static boolean v[][];
    static int dr[] = {-1, 0, 1, 0};
    static int dc[] = {0, 1, 0, -1};
    static int R, C, K;

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }
    
    public static void solution() {
    	
    	int result = 0;
    	
    	for(int i = 0; i < list.size(); i++) {
    		Golam g = list.get(i);
    		boolean flag = false;
    		while(true) {
    			
    			
    			if(moveSouthGolam(g)) flag = true;
    			else flag = false;
    			
    			boolean flag1 = false;
    			
        		if(!flag && g.p.r != map.length - 3 && checkMoveWestGolam(g) && check(g.p.r + dr[3] * 2, g.p.c + dc[3] * 2)) { // 서쪽으로 갈 수 있는지 확인
        			g.p.c -= 1;
        			g.exit = g.exit - 1 < 0 ? g.exit -1 + 4: g.exit - 1;
        			
        			if(moveSouthGolam(g)) {
        				flag = true;
        				flag1 = true;
        			}
        			else {
        				g.p.c += 1;
        				g.exit += 1;
        				g.exit %= 4;
        			}
        		} 
        		if(!flag && !flag1 && g.p.r != map.length - 3 && checkMoveEastGolam(g) && check(g.p.r + dr[1] * 2, g.p.c + dc[1] * 2)) { // 동쪽으로 갈 수 있는지 확인
        			g.p.c += 1;
        			g.exit += 1;
        			g.exit %= 4;
        			
        			if(moveSouthGolam(g)) flag = true;
        			else {
        				g.p.c -= 1;
        				g.exit -= 1;
        				g.exit = g.exit < 0 ? g.exit += 4: g.exit;
        			}
        		} 
        		
        		if(!flag) {
        			// 자기 영역 표시
        			markArea(i, g);
        			
        			// 아래로 이동
        			int tmp = bfs(i, g);
        			
        			if(g.p.r < 4) {
        				for(int r = 0; r < map.length; r++) {
        					Arrays.fill(map[r], null);
        				}
        			} else result += tmp;
        			break;
        		}
    		}
    	}
    	
    	System.out.println(result);
    }

    private static boolean moveSouthGolam(Golam g) {
    	if(checkMoveSouthGolam(g) && check(g.p.r + dr[2] * 2, g.p.c + dc[2] * 2)
    			&& map[g.p.r + dr[2] * 2][g.p.c + dc[2] * 2] == null) { // 아래로 갈 수 있는지 확인
			g.p.r += 1;
			return true;
		} 
    	
    	return false;
	}

	private static int bfs(int i, Golam g) {
		int row = 0;
    	
		for(int r = 0; r < v.length; r++) Arrays.fill(v[r], false);

		queue.add(g);
		v[g.p.r][g.p.c] = true;
		
		while(!queue.isEmpty()) {
			Golam current = queue.poll();
			row = Math.max(row, current.p.r);
			
			for(int d = 0; d < 4; d++) {
				int nr = current.p.r + dr[d];
				int nc = current.p.c + dc[d];
				
				if(!check(nr, nc) || v[nr][nc] || map[nr][nc] == null) continue;
				
				if(map[nr][nc].num == map[current.p.r][current.p.c].num ||
						(map[current.p.r][current.p.c].exit && map[nr][nc] != null && map[nr][nc].num != i)) {
					v[nr][nc] = true;
					queue.add(new Golam(new Point(nr, nc)));
				}
			}
			
		}
		
    	return row = row - 2 < 0 ? 0 : row - 2;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 1 && nr < map.length - 1 && nc >= 1 && nc < map[0].length - 1;
	}

	private static void markArea(int num, Golam g) {
    	map[g.p.r][g.p.c] = new Info(num, false);
    	
    	for(int d = 0; d < 4; d++) {
    		int nr = g.p.r + dr[d];
    		int nc = g.p.c + dc[d];
    		
    		if(map[nr][nc] == null) map[nr][nc] = new Info(num, false);
    	}
    	if(map[g.p.r + dr[g.exit]][g.p.c + dc[g.exit]].num == num) {
    		map[g.p.r + dr[g.exit]][g.p.c + dc[g.exit]].exit = true;
    	}
	}

	private static boolean checkMoveEastGolam(Golam g) {
    	g.p1.r = g.p.r - 1;
    	g.p1.c = g.p.c + 1;
    	
    	g.p2.r = g.p.r;
    	g.p2.c = g.p.c + 2;
    	
    	g.p3.r = g.p.r + 1;
    	g.p3.c = g.p.c + 1;
		return checkAvailableMove(g);
	}

	private static boolean checkMoveWestGolam(Golam g) {
    	g.p1.r = g.p.r - 1;
    	g.p1.c = g.p.c - 1;
    	
    	g.p2.r = g.p.r;
    	g.p2.c = g.p.c -2;
    	
    	g.p3.r = g.p.r + 1;
    	g.p3.c = g.p.c - 1;
    	
		return checkAvailableMove(g);
	}

	private static boolean checkMoveSouthGolam(Golam g) {
    	g.p1.r = g.p.r + 1;
    	g.p1.c = g.p.c - 1;
    	
    	g.p2.r = g.p.r + 1;
    	g.p2.c = g.p.c + 1;
    	
    	g.p3.r = g.p.r + 2;
    	g.p3.c = g.p.c;
    	return checkAvailableMove(g);
	}

	private static boolean checkAvailableMove(Golam g) {
		return getMap(g.p1) == null && getMap(g.p2) == null && getMap(g.p3) == null;
	}

	private static Info getMap(Point p) {
		return map[p.r][p.c];
	}

	public static void initInput() throws Exception {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        list = new ArrayList<Golam>();
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        map = new Info[R + 4][C + 2];
        v = new boolean[R + 4][C + 2];
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int c = Integer.parseInt(st.nextToken());
            int exit = Integer.parseInt(st.nextToken());
            
            list.add(new Golam(new Point(1, c), exit));
            list.get(i).p1 = new Point(1, c);
            list.get(i).p2 = new Point(1, c);
            list.get(i).p3 = new Point(1, c);
        }
    }
}