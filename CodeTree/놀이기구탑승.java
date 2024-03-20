import java.io.*;
import java.util.*;

public class 놀이기구탑승 {
    static class Point implements Comparable<Point> {
        int r, c, like, empty;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Point o) {
            if(this.like == o.like) {
                if(this.empty == o.empty) {
                    if(this.r == o.r) return Integer.compare(this.c, o.c);
                    return Integer.compare(this.r, o.r);
                }
                return Integer.compare(o.empty, this.empty);
            }
            return Integer.compare(o.like, this.like);
        }
    }

    static LinkedHashMap<Integer, ArrayList<Integer>> students = new LinkedHashMap();
    static int map[][], idx[], n, score[] = {0, 1, 10, 100, 1000};
    static PriorityQueue<Point> queue = new PriorityQueue();
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        idx = new int [n * n];
        map = new int[n][n];

        for(int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            ArrayList<Integer> likes = new ArrayList();

            idx[i] = num;
            while(st.hasMoreTokens()) likes.add(Integer.parseInt(st.nextToken()));

            students.put(num, likes);
        }

        for(int i = 0; i < n * n; i++) {
            for(int r = 0; r < n; r++) {

                for(int c = 0; c < n; c++) {
                    if(map[r][c] != 0) continue;

                    Point next = new Point(r, c);
                    int empty = 0, like = 0;

                    for(int d = 0; d < 4; d++) {
                        int nr = next.r + dr[d];
                        int nc = next.c + dc[d];

                        if(!check(nr, nc)) continue;
                        if(map[nr][nc] == 0) empty++;
                        if(checkLike(idx[i], nr, nc)) like++;
                    }

                    next.like = like;
                    next.empty = empty;

                    queue.add(next);
                }
            }

            Point current = queue.poll();
            map[current.r][current.c] = idx[i];

            queue.clear();
        }

        int result = 0;
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                int like = 0;
                for(int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(!check(nr, nc)) continue;
                    if(students.get(map[r][c]).contains(map[nr][nc])) like++;
                }

                result += score[like];
            }
        }

        System.out.println(result);
    }

    private static boolean checkLike(int s, int nr, int nc) {
        return students.get(s).contains(map[nr][nc]);
    }

    private static boolean check(int nr, int nc) {
        return nr >= 0 && nr < n && nc >= 0 && nc < n;
    }
}
