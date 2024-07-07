import java.io.*;
import java.util.*;

/*
최솟값 찾기
 */

public class BOJ11003 {
    static class Node {
        int idx, num;

        public Node(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }
    static Deque<Node> deque = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int arr[] = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
        for(int e = 0; e < arr.length; e++) {

            while(!deque.isEmpty() && deque.peekLast().num > arr[e]) {
                deque.pollLast();
            }

            deque.add(new Node(e, arr[e]));
            if(deque.peekFirst().idx <= e - L) deque.pollFirst();

            sb.append(deque.peekFirst().num + " ");
        }

        System.out.println(sb);
    }
}
