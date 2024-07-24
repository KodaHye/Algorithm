import java.io.*;
import java.util.*;

/*
트리의 부모 찾기
 */

public class BOJ11725 {
    static ArrayList<Integer> adj[];
    static int p[];

    public static void main(String[] args) throws Exception {
        initInput();
        bfs();
    }

    public static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        p[1] = 1;

        while(!queue.isEmpty()) {
            int current = queue.poll();
            for(int next: adj[current]) {
                if(p[next] != 0) continue;
                p[next] = current;
                queue.add(next);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 2; i < p.length; i++) {
            sb.append(p[i] + "\n");
        }
        System.out.print(sb);
    }

    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N + 1];
        p = new int[N + 1];

        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }
    }
}
