import java.io.*;
import java.util.*;

/*
크게 만들기
 */

public class BOJ2812 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String s = br.readLine();
        Deque<Character> deque = new LinkedList<>();

        for(int i = 0; i < N; i++) {
            while(K > 0 && !deque.isEmpty() && deque.getLast() < s.charAt(i)) {
                deque.pollLast();
                K--;
            }
            deque.addLast(s.charAt(i));
        }

        while(deque.size() > K) {
            sb.append(deque.pollFirst());
        }

        System.out.println(sb);
    }
}
