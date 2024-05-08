import java.io.*;
import java.util.*;

/*
임계경로
 */

public class BOJ1948 {
    static class Node {
        int e, w;

        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
    static int N, M, start, end, inArr[], time[];
    static ArrayList<Node> adj[], reverseAdj[];

    public static void main(String[] args) throws Exception {
        initInput();
        solve();
    }

    private static void solve() {
        Queue<Integer> queue = new LinkedList<>();

        time = new int[adj.length];

        queue.add(start);

        int cnt = 0;
        while(!queue.isEmpty()) {
            int current = queue.poll();

            for(Node n: adj[current]) {
                time[n.e] = Math.max(time[n.e], time[current] + n.w);
                inArr[n.e]--;

                if(inArr[n.e] == 0) queue.add(n.e);
            }
        }

        queue.add(end);
        boolean v[] = new boolean[N + 1];

        while(!queue.isEmpty()) {
            int current = queue.poll();

            for(Node n: reverseAdj[current]) {
                if(time[current] == time[n.e] + n.w) {
                    cnt++;

                    if(v[n.e]) continue;
                    queue.add(n.e);
                    v[n.e] = true;
                }
            }
        }
        System.out.println(time[end] + "\n" + cnt);
    }

    private static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 도시의 개수
        N = Integer.parseInt(br.readLine());
        inArr = new int[N + 1];

        adj = new ArrayList[N + 1];
        reverseAdj = new ArrayList[N + 1];

        for(int i = 0; i < adj.length; i++) {
            reverseAdj[i] = new ArrayList<>();
            adj[i] = new ArrayList<>();
        }

        // 도로의 개수
        M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, w));
            reverseAdj[b].add(new Node(a, w));
            inArr[b]++;
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }
}
