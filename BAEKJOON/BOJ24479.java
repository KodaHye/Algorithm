import java.io.*;
import java.util.*;

/*
알고리즘 수업 - 깊이 우선 탐색 1
 */

public class BOJ24479 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M, R;
    static ArrayList<Integer> adj[];
    static int v[], cnt = 1;

    public static void main(String[] args) throws Exception {
        initInput();

        v[R] = 1;
        dfs(R);

        for(int i = 1; i < v.length; i++) sb.append((v[i]) + "\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void dfs(int start) {
        Collections.sort(adj[start]);
        for(int next: adj[start]) {
            if(v[next] != 0) continue;
            v[next] = ++cnt;
            dfs(next);
        }
    }
    public static void initInput() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        v = new int[N + 1];
        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            adj[s].add(e);
            adj[e].add(s);
        }
    }
}
