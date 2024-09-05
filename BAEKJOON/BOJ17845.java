import java.io.*;
import java.util.*;

/*
수강 과목
 */

public class BOJ17845 {
    static class Subject {
        int i, t;
        public Subject(int i, int t) {
            this.i = i;
            this.t = t;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int knapsack[][] = new int[K + 1][N + 1];
        Subject subjects[] = new Subject[K + 1];

        for(int k = 1; k < subjects.length; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            subjects[k] = new Subject(i, t);
        }

        for(int i = 1; i < knapsack.length; i++) {
            for(int t = 1; t < N + 1; t++) {
                if(subjects[i].t <= t) {
                    knapsack[i][t] = Math.max(knapsack[i - 1][t - subjects[i].t] + subjects[i].i, knapsack[i - 1][t]);
                } else {
                    knapsack[i][t] = knapsack[i - 1][t];
                }
            }
        }

        System.out.println(knapsack[K][N]);
    }
}
