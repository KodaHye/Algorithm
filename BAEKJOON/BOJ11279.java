import java.io.*;
import java.util.*;

/*
최대 힙
 */

public class BOJ11279 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            if(num == 0) {
                if(pq.isEmpty()) sb.append(0 + "\n");
                if(!pq.isEmpty()) sb.append(pq.poll() + "\n");
            } else pq.add(num);
        }

        System.out.print(sb);
    }
}
