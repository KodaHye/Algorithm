import java.io.*;
import java.util.*;

/*
랭킹전 대기열
 */

public class BOJ20006 {
    static class Node {
        int l, cnt;
        PriorityQueue<Player> pq = new PriorityQueue<>();

        public Node(int l, int cnt) {
            this.l = l;
            this.cnt = cnt;
        }
    }

    static class Player implements Comparable<Player> {
        int l;
        String name;

        public Player(int l, String name) {
            this.l = l;
            this.name = name;
        }

        @Override
        public int compareTo(Player o) {
            return this.name.compareTo(o.name);
        }
    }
    static ArrayList<Node> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        for(int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            String s = st.nextToken();

            int current = list.size() - 1;
            boolean enterRoom = false;

            for(Node n: list) {
                if(l >= n.l - 10 && l <= n.l + 10 && n.cnt < m) {
                    n.cnt++;
                    n.pq.add(new Player(l, s));
                    enterRoom = true;
                    break;
                }
            }

            if(enterRoom) continue;

            list.add(new Node(l, 1));
            current += 1;
            list.get(current).pq.add(new Player(l, s));
        }

        StringBuilder sb = new StringBuilder();
        for(Node n: list) {
            if(n.cnt == m) sb.append("Started!\n");
            else sb.append("Waiting!\n");

            while(!n.pq.isEmpty()) {
                Player current = n.pq.poll();
                sb.append(current.l + " " + current.name + "\n");
            }
        }

        System.out.print(sb);
    }
}
