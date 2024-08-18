import java.io.*;
import java.util.*;

/*
새로운 게임 2
 */

public class BOJ17837 {
    static class Node {
        int r, c, d;

        public Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    static int N, K, dr[] = {0, 0, 0, -1, 1}, dc[] = {0, 1, -1, 0, 0};;
    static char map[][];
    static Queue<Integer> horse[][];
    static HashMap<Integer, Node> horses;

    static void solution() {
        int turn = 0;
        while(turn++ < 1000) {
            if(move()) break;
        }
        System.out.println(turn > 1000? -1 : turn);
    }

    static boolean move() {
        for(int key: horses.keySet()) {

            Node h = horses.get(key);
            int r = h.r, c = h.c, d = h.d;

            int nr = h.r + dr[h.d];
            int nc = h.c + dc[h.d];

            if(!check(nr, nc) || map[nr][nc] == 'B') {
                if(h.d % 2 == 1) d += 1;
                if(h.d % 2 == 0) d -= 1;

                nr = h.r + dr[d];
                nc = h.c + dc[d];
            }

            h.d = d;
            if(!check(nr, nc) || map[nr][nc] == 'B') continue;

            if(map[nr][nc] == 'W') {
                int size = horse[r][c].size();
                Queue<Integer> tmp = new LinkedList<>();
                tmp.addAll(horse[r][c]);
                horse[r][c].clear();

                for(int i = 0; i < size; i++) {
                    if(tmp.peek() == key) {
                        while(!tmp.isEmpty()) {
                            int idx = tmp.poll();
                            horses.get(idx).r = nr;
                            horses.get(idx).c = nc;
                            horse[nr][nc].add(idx);
                        }
                        break;
                    }
                    horse[r][c].add(tmp.poll());
                }
            }

            if(map[nr][nc] == 'R') {
                int size = horse[r][c].size();
                Deque<Integer> tmp = new LinkedList<>();
                tmp.addAll(horse[r][c]);
                horse[r][c].clear();

                for(int i = 0; i < size; i++) {
                    if(tmp.peek() == key) {
                        while(!tmp.isEmpty()) {
                            int idx = tmp.pollLast();
                            horses.get(idx).r = nr;
                            horses.get(idx).c = nc;
                            horse[nr][nc].add(idx);
                        }
                        break;
                    }
                    horse[r][c].add(tmp.pollFirst());
                }
            }
            if(horse[nr][nc].size() >= 4) return true;
        }
        return false;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        horse = new Queue[N][N];
        horses = new HashMap<>();

        for(int r = 0; r < horse.length; r++)
            for(int c = 0; c < horse[0].length; c++) horse[r][c] = new LinkedList<>();

        for(int r = 0; r < map.length; r++) {
            st =  new StringTokenizer(br.readLine());
            for(int c = 0; c < map[r].length; c++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 0) map[r][c] = 'W';
                if(num == 1) map[r][c] = 'R';
                if(num == 2) map[r][c] = 'B';
            }
        }

        int idx = 0;
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            horse[r][c].add(idx);
            horses.put(idx, new Node(r, c, d));
            idx++;
        }

        solution();
    }
}
