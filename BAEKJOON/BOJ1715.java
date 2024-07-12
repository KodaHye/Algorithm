import java.util.*;
import java.io.*;

/*
카드 정렬하기
 */

public class BOJ1715 {
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) pq.add(Integer.parseInt(br.readLine()));

        int result = 0;

        int a, b;
        while(pq.size() > 1) {
            a = pq.poll();
            b = pq.poll();

            result += (a + b);
            pq.add(a + b);
        }

        System.out.println(result);
    }
}
