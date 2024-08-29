import java.io.*;
import java.util.*;

/*
나무 재테크
 */

public class BOJ16235 {
    static class Tree implements Comparable<Tree> {
        int age;
        public Tree(int age) {
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return Integer.compare(this.age, o.age);
        }
    }
    static int N, M, K, plus[][], nutrition[][];
    static int dr[] = {-1, -1, -1, 0, 1, 1, 1, 0}, dc[] = {-1, 0, 1, 1, 1, 0, -1, -1};
    static ArrayList<Tree> map[][];

    static void spring() {
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(map[r][c].size() == 0) continue;
                Collections.sort(map[r][c]);

                int idx = 0;
                while(idx < map[r][c].size()) {
                    if(nutrition[r][c] - map[r][c].get(idx).age < 0) break;
                    nutrition[r][c] -= map[r][c].get(idx).age;
                    map[r][c].get(idx).age += 1;
                    idx++;
                }

                int sum = 0;
                for(int i = map[r][c].size() - 1; i >= idx; i--) {
                    sum += map[r][c].get(i).age/ 2;
                    map[r][c].remove(i);
                }

                nutrition[r][c] += sum;
            }
        }
    }
    static void fall() {
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(map[r][c].size() == 0) continue;

                for(Tree t: map[r][c]) {
                    if(t.age == 1 || t.age % 5 != 0) continue;

                    for(int d = 0; d < 8; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if(!check(nr, nc)) continue;
                        map[nr][nc].add(new Tree(1));
                    }
                }
            }
        }
    }

    static void winter() {
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                nutrition[r][c] += plus[r][c];
            }
        }
    }
    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static void solution() {
        while(K-- > 0) {
            spring();
            fall();
            winter();
        }
    }
    public static void main(String[] args) throws Exception {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        plus = new int[N][N];
        nutrition = new int[N][N];
        map = new ArrayList[N][N];

        for(int r = 0; r < map.length; r++) for(int c = 0; c < N; c++) map[r][c] = new ArrayList();
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                plus[r][c] = Integer.parseInt(st.nextToken());
                nutrition[r][c] = 5;
            }
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            map[x][y].add(new Tree(z));
        }

        solution();
        int result = 0;
        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[0].length; c++) {
                result += map[r][c].size();
            }
        }
        System.out.println(result);
    }
}
