import java.io.*;
import java.util.*;

/*
최소 힙
 */

public class BOJ1927 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            if(num == 0) {
                if(!pq.isEmpty()) sb.append(pq.poll() + "\n");
                else sb.append(0 + "\n");
            }

            else pq.add(num);
        }

        System.out.print(sb);
    }
}
