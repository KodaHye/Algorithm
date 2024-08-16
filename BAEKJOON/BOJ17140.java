import java.io.*;
import java.util.*;

/*
이차원 배열과 연산
 */

public class BOJ17140 {
    static class Node implements Comparable<Node> {
        int num, cnt;
        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            if(this.cnt == o.cnt) return Integer.compare(this.num, o.num);
            return Integer.compare(this.cnt, o.cnt);
        }
    }
    static HashMap<Integer, Integer> hashMap;
    static PriorityQueue<Node> pq;
    static int r, c, k, rLength, cLength, map[][];

    static void solution() {
        int result = 0;

        while(map[r][c] != k && result < 101) {
            if(rLength >= cLength) calR();
            else calC();
            result++;
        }

        System.out.println(result > 100 ? -1: result);
    }

    static void calC() {
        int tmpRLength = rLength;
        rLength = 0;

        for (int c = 0; c < cLength; c++) {

            hashMap = new HashMap<>();

            for (int r = 0; r < tmpRLength; r++) countNumCnt(r, c);

            hashMap.entrySet().forEach(s -> pq.add(new Node(s.getKey(), s.getValue())));
            rLength = Math.max(rLength, pq.size() * 2);

            int size = pq.size();
            for (int r = 0; r < size; r++) {
                Node n = pq.poll();
                map[r * 2][c] = n.num;
                map[r * 2 + 1][c] = n.cnt;
            }
        }
    }

    static void countNumCnt(int r, int c) {
        if(map[r][c] == 0) return;
        if(!hashMap.containsKey(map[r][c])) hashMap.put(map[r][c], 1);
        else hashMap.put(map[r][c], hashMap.get(map[r][c]) + 1);
        map[r][c] = 0;
    }

    static void calR() {
        int tmpCLength = cLength;
        cLength = 0;

        for(int r = 0; r < rLength; r++) {

            hashMap = new HashMap<>();

            for(int c = 0; c < tmpCLength; c++)countNumCnt(r, c);
            hashMap.entrySet().forEach(s -> pq.add(new Node(s.getKey(), s.getValue())));
            cLength = Math.max(cLength, pq.size() * 2);

            int size = pq.size();
            for(int c = 0; c < size; c++) {
                Node n = pq.poll();
                map[r][2 * c] = n.num;
                map[r][2 * c + 1] = n.cnt;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        map = new int[100][100];
        pq = new PriorityQueue<>();

        for(int r = 0; r < 3; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < 3; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        rLength = 3; cLength = 3;

        solution();
    }
}
