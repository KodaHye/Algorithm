import java.io.*;
import java.util.*;

/*
회의실 배정
 */

public class BOJ1931 {
    static class Node implements Comparable<Node> {
        int s, e;
        public Node(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Node o) {
            if(this.e == o.e) return Integer.compare(this.s, o.s);
            return Integer.compare(this.e, o.e);
        }
    }
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            pq.add(new Node(s, e));
        }

        Node current = pq.poll();
        int cnt = 1;

        while(!pq.isEmpty()) {
            Node next = pq.poll();

            if(next.s >= current.e) {
                cnt++;
                current = next;
            }
        }

        System.out.println(cnt);
    }
}
