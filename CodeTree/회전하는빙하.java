import java.util.*;
import java.io.*;

public class 회전하는빙하 {
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int n, map[][], copy[][], max;
    static boolean v[][];
    static Queue<Point> queue = new LinkedList<>(), ice = new LinkedList<>();
    static int dr[] ={1, 0, 0, -1}, dc[] = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken()); // 회전 횟수

        int size = (int) Math.pow(2, n);
        map = new int[size][size];
        copy = new int[size][size];

        for(int r = 0; r < map.length; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < map[0].length; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }


        st = new StringTokenizer(br.readLine());

        while(st.hasMoreTokens()) {
            int l = Integer.parseInt(st.nextToken()); // 회전레벨
            // 빙하 회전
            
            if(l != 0) rotate(l);

            for(int r = 0; r < map.length; r++) {
                for(int c = 0; c < map[0].length; c++) {
                    int cnt = 0;
                    for(int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if(!check(nr, nc)) continue;
                        if(map[nr][nc] > 0) cnt++;
                    }
                    
                    if(cnt < 3) ice.add(new Point(r, c));
                }
            }



            while(!ice.isEmpty()) {
                Point current = ice.poll();
                
                if(map[current.r][current.c] > 0) map[current.r][current.c]--;
            }
        }

        StringBuilder sb = new StringBuilder();

        int sum = 0;
        
        v = new boolean[map.length][map[0].length];
        
        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[0].length; c++) {
                
                if(map[r][c] == 0 || v[r][c]) continue;
                int gunzip = bfs(r, c);
                sum += gunzip;
            }
        }

        sb.append(sum + "\n" + max);
        System.out.println(sb);
    }

    static void rotate(int l) {
        int tmp = (int) Math.pow(2,  l);
        
        copy();
        int cnt = 0;
        
        for(int r = 0; r < map.length; r += tmp) {
            for(int c = 0; c < map[0].length; c += tmp) {
                int ttmp = tmp / 2;
                int d = 0;

                for(int kr = r; kr < r + ttmp + 1; kr += ttmp) {
                    for(int kc = c; kc < c + ttmp + 1; kc += ttmp) {
                        Point prev = new Point(kr + ttmp * dr[d], kc + ttmp * dc[d]);
                        for(int kkr = 0; kkr < ttmp; kkr++) {
                            for(int kkc = 0; kkc < ttmp; kkc++) {
                                map[kr + kkr][kc + kkc] = copy[prev.r + kkr][prev.c + kkc];
                            }
                        }
                        d++;
                    }
                }
            } 
        }
    }

    static void copy() {
        for(int r = 0; r < map.length; r++) {
            copy[r] = Arrays.copyOf(map[r], map[r].length);
        }
    }

    static int bfs(int r, int c) {
        queue.add(new Point(r, c));
        v[r][c] = true;
        int gunzip = map[r][c];
        int size = 1;

        while(!queue.isEmpty()) {
            Point current = queue.poll();

            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if(!check(nr, nc) || v[nr][nc] || map[nr][nc] == 0) continue;
                v[nr][nc] = true;
                gunzip += map[nr][nc];
                queue.add(new Point(nr, nc));
                size++;
            }
        }

        max = Math.max(max, size);
        return gunzip;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
    }
}