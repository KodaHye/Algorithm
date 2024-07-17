import java.io.*;
import java.util.*;

/*
알고리즘 수업 - 너비 우선 탐색 1
 */

public class BOJ24444 {
    static ArrayList<Integer> adj[];
    static int v[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N];
        v = new int[N];

        for(int i = 0; i <adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            adj[u].add(v);
            adj[v].add(u);
        }


        bfs(R - 1, 1);

        StringBuilder sb = new StringBuilder();
        for(int n: v) sb.append(n + "\n");
        System.out.println(sb);
    }

    public static void bfs(int n, int cnt) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        v[n] = cnt++;

        while(!queue.isEmpty()) {
            int current = queue.poll();

            Collections.sort(adj[current]);
            for(int next: adj[current]) {
                if(v[next] != 0) continue;
                queue.add(next);
                v[next] = cnt++;
            }
        }
    }
}
