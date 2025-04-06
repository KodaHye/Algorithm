import java.io.*;
import java.util.*;

/*
 * 반대로 생각하기 어렵다면 ArrayList나 HashSet같은거 활용해보기
 */

public class 코드트리빵 {
	static final int EMPTY = 0, BC = 1, CANT = 2;
    static int N;
    static int M;
    static int[][] map; // 지나갈 수 있는지 여부 기록
    static Point[] stores;
    static Person[] people;
    static ArrayList<Point> baseCamps;
    static int[] mr = {-1, 0, 0, 1}, mc = {0, -1, 1, 0};
    
    static int arrivedPeople;
    
    private static int simulation() {
        int time = 0;

        while (true) {

            // ① 격자 안의 사람들 편의점으로 1칸 이동
            for (Person person : people) {
                person.goToStore();
            }

            // ② 편의점에 도착했는지 확인
            for (Person person : people) {
                person.checkArrived();
            }

            // ③ 격자 밖의 사람 베이스캠프로 이동
            if (time < M) {
                people[time].goToBaseCamp();
            }
            
            time++;
            
            // ④ 모든 사람이 편의점에 도착했으면 종료
            if(arrivedPeople == M) break;
        }

        return time;
    }

    private static void init() throws IOException {
    	
    	System.setIn(new FileInputStream("./input/코드트리빵.txt"));
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        baseCamps = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
            	map[r][c] = Integer.parseInt(st.nextToken());
            	
            	if(map[r][c] == 1) {
                    baseCamps.add(new Point(r, c));
                }
            }
        }

        stores = new Point[M];
        people = new Person[M];

        for (int i = 0; i < M; i++) {
        	
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            
            stores[i] = new Point(r, c);
            people[i] = new Person(i, -1, -1);
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        int answer = simulation();
        System.out.println(answer);
    }

    private static int bfs(int sr, int sc, int dr, int dc) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        q.offer(new int[] {sr, sc, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int distance = cur[2];

            if (r == dr && c == dc) return distance;

            for(int d = 0; d < 4; d++) {
                int nr = r + mr[d];
                int nc = c + mc[d];

                if(!check(nr, nc) || map[r][c] == CANT || visited[nr][nc]) continue;
                
                q.add(new int[] {nr, nc, distance + 1});
                visited[nr][nc] = true;
            }
        }

        return Integer.MAX_VALUE;
    }

    static boolean check(int r, int c) {
    	return r >= 0 && r < N && c >= 0 && c < N;
    }
    
    static class Point {
        int r;
        int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        // 편의점과 가장 가까운 베이스캠프의 번호를 반환
        Point getMinBaseCamp() {
            int minDistance = Integer.MAX_VALUE;
            Point minBaseCamp = null;

            for (Point baseCamp: baseCamps) {
                int distance = baseCamp.getDistance(this);
                if (minDistance > distance) {
                    minDistance = distance;
                    minBaseCamp = baseCamp;
                }
            }

            return minBaseCamp;
        }
        
        int getDistance(Point p) {
            if (map[r][c] == CANT) return Integer.MAX_VALUE;
            return bfs(r, c, p.r, p.c);
        }
    }

    static class Person {
        int num;
        int r;
        int c;
        boolean isIn, isArrived;

        Person(int num, int r, int c) {
            this.num = num;
            this.r = r;
            this.c = c;
        }


        boolean canMove() {
            return isIn && !isArrived;
        }

        void checkArrived() {
            if (!canMove()) return;

            Point dest = stores[this.num];
            
            if (this.r == dest.r && this.c == dest.c) {
                this.isArrived = true;
                map[dest.r][dest.c] = CANT;
                arrivedPeople++;
            }
        }

        // 편의점 방향을 향해서 최단 거리로 1칸 이동
        void goToStore() {
            if (!canMove()) return;

            int minDistance = Integer.MAX_VALUE;

            Point dest = stores[num];
            int md = 0;
            
            // 격자에 있는 사람들이 가고 싶은 방향을 향해 한 칸 이동
            // ↑, ←, →, ↓
            for(int d = 0; d < 4; d++) {
            	int nr = r + mr[d];
            	int nc = c + mc[d];
            	
            	if(!check(nr, nc)) continue;
                int distance = bfs(nr, nc, dest.r, dest.c);

                if (minDistance > distance) {
                    minDistance = distance;
                    md = d;
                }
            }

            this.r += mr[md];
            this.c += mc[md];
        }

        void goToBaseCamp() {
            Point destBaseCamp = stores[num].getMinBaseCamp();
            map[destBaseCamp.r][destBaseCamp.c] = CANT;

            this.r = destBaseCamp.r;
            this.c = destBaseCamp.c;
            this.isIn = true;
        }
    }
}

