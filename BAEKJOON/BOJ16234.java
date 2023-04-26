import java.io.*;
import java.util.*;
/*
 * 인구 이동
 */
public class BOJ16234 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
    static int N, L, R, map[][];
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};
    
    public static void main(String[] args) throws Exception {
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	L = Integer.parseInt(st.nextToken());
    	R = Integer.parseInt(st.nextToken());
    	
    	map = new int[N][N];
    	
    	for (int r = 0; r < N; r++) {
    		st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
    	
    	int count = 0;
    	
    	while(true) {
    		boolean flag = false;
    		int sum = 0;
    		boolean v[][] = new boolean[N][N];
    		
        	for (int r = 0; r < N; r++) {
    			for (int c = 0; c < N; c++) {
    				if(!v[r][c]) {
        	    		Queue<Point> queue = new LinkedList<>();
        	    		Queue<Point> queue2 = new LinkedList<>();
        	    		
        	    		sum = map[r][c];
        				
        				queue.add(new Point(r, c));
         				queue2.add(new Point(r, c));
         				
        				v[r][c] = true;
        				
        				while(!queue.isEmpty()) {
        					Point current = queue.poll();
        					
        					int cr = current.r;
        					int cc = current.c;
        					
            				for(int d = 0; d < 4; d++) {
            					int nr = cr + dr[d];
            					int nc = cc + dc[d];
            					
            					if(!check(nr, nc)) continue;
            					int tmp = Math.abs(map[nr][nc] - map[cr][cc]);
            					
            					if(!v[nr][nc] && tmp >= L && tmp <= R) {
            						sum += map[nr][nc];
            						v[nr][nc] = true;
            						queue.add(new Point(nr, nc));
            						queue2.add(new Point(nr, nc));
            					}
            				}
        				}
            			if(queue2.size() > 1) {
            				flag = true;
            				int size = queue2.size();
            				
            				while(!queue2.isEmpty()) {
            					Point tmp = queue2.poll();
            					map[tmp.r][tmp.c] = sum / size;
            				}
            			}
    				}
    			}
    		}
        	if(!flag) break;
        	count++;
    	}
    	
    	System.out.println(count);
    }

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}