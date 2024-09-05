import java.io.*;
import java.util.*;

/*
벼락치기
 */

public class BOJ14728 {
    static class Subject {
        int k, s;
        public Subject(int k, int s) {
            this.k = k;
            this.s = s;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        Subject subject[] = new Subject[N + 1];
        for(int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            subject[i] = new Subject(k, s);
        }

        int knapsack[][] = new int[N + 1][T + 1];
        for(int i = 1; i < N + 1; i++) {
            for(int w = 1; w < T + 1; w++) {
                if(subject[i].k <= w) {
                    knapsack[i][w] = Math.max(subject[i].s + knapsack[i - 1][w - subject[i].k], knapsack[i - 1][w]);
                } else {
                    knapsack[i][w] = knapsack[i - 1][w];
                }
            }
        }

        System.out.print(knapsack[N][T]);
    }
}
