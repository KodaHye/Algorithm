import java.io.*;
import java.util.*;

/*
수 묶기
 */

public class BOJ1744 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> plusPQ = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        PriorityQueue<Integer> minusPQ = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            if(n > 0) plusPQ.add(n);
            else minusPQ.add(n);
        }

        long result = 0;
        while(plusPQ.size() > 1) {
            int a = plusPQ.poll();
            int b = plusPQ.poll();
            if(a == 1 || b == 1) result += (a + b);
            else result += (a * b);
        }

        while(!plusPQ.isEmpty()) result += plusPQ.poll();

        while(minusPQ.size() > 1) {
            int a = minusPQ.poll();
            int b = minusPQ.poll();

            result += (a * b);
        }

        while(!minusPQ.isEmpty()) result += minusPQ.poll();

        System.out.println(result);
    }
}
