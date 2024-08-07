import java.io.*;
import java.util.*;

/*
N번째 큰 수
 */

public class BOJ2075 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) pq.add(Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < N - 1; i++) pq.poll();

        System.out.println(pq.peek());
    }
}
