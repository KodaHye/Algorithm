import java.io.*;
import java.util.*;

/*
연결 요소의 개수
 */

public class BOJ11724 {
    static ArrayList<Integer> adj[];
    static boolean v[];

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    public static void solution() {
        int result = 0;
        for(int i = 1; i < adj.length; i++) {
            if(v[i]) continue;
            dfs(i);

            result++;
        }

        System.out.println(result);
    }

    public static void dfs(int i) {
        v[i] = true;

        for(int next: adj[i]) {
            if(v[next]) continue;
            dfs(next);
        }
    }
    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        v = new boolean[N + 1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u].add(v);
            adj[v].add(u);
        }
    }
}
