import java.util.*;
import java.io.*;

/*
 * GBC
 */

public class Softeer584 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, elevator[], test[];

    public static void main(String args[]) throws Exception {
        st = new StringTokenizer(br.readLine());

        elevator = new int[101];
        test = new int[101];

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        setHeight(N, elevator);

        setHeight(M, test);

        System.out.println(calDiff());
    }

    public static void setHeight(int n, int[] elevator) throws Exception {
        
        int height = 1;
        int maxHeight = 1;

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            maxHeight += a;

            for(int j = height; j < maxHeight; j++) {
                elevator[j] = b;
            }

            height += a;
        }
    }

    public static int calDiff() {
        int maxDiff = 0;

        for(int i = 1; i < 101; i++) {
            maxDiff = Math.max(test[i] - elevator[i], maxDiff);
        }
        return maxDiff;
    }
}