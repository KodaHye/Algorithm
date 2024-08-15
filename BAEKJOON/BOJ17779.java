import java.io.*;
import java.util.*;

/*
게리맨더링 2
 */

public class BOJ17779 {
    static int result, sum;
    static int N, map[][];

    static void solution() {
        for(int r = 1; r < N + 1; r++) {
            for(int c = 1; c < N + 1; c++) {
                for(int d1 = 1; d1 < N + 1; d1++) {
                    if(c - d1 < 1) continue;
                    for(int d2 = 1; d2 < N + 1; d2++) {
                        if(r + d1 + d2 > N || c + d2 > N) continue;

                        int people[] = new int[5];

                        for (int i = 1; i <= r + d1 - 1; i++) {
                            people[0] += map[i][c - Math.max(0, i - (r - 1))];
                        }

                        for (int i = 1; i <= r + d2; i++) {
                            people[1] += map[i][N] - map[i][c + Math.max(0, i - r)];
                        }

                        for (int i = r + d1; i <= N; i++) {
                            people[2] += map[i][c - d1 + d2 - 1 - Math.max(0, r + d1 + d2 - i)];
                        }

                        for (int i = r + d2 + 1; i <= N; i++) {
                            people[3] += map[i][N] - map[i][c - d1 + d2 - 1 + Math.max(0, r + d1 + d2 + 1 - i)];
                        }

                        people[4] = sum - (people[0] + people[1] + people[2] + people[3]);

                        int min = people[0];
                        int max = people[0];

                        for(int i = 1; i < 5; i++) {
                            min = Math.min(min, people[i]);
                            max = Math.max(max, people[i]);
                        }

                        result = Math.min(result, max - min);
                    }
                }
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        result = Integer.MAX_VALUE;
        for(int r = 1; r < map.length; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 1; c < map[0].length; c++) {
                int num = Integer.parseInt(st.nextToken());
                map[r][c] = map[r][c - 1] + num;
            }

            sum += map[r][N];
        }
        solution();
    }
}
