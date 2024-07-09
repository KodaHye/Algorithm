import java.io.*;
import java.util.*;

/*
ABCDE
 */

public class BOJ13023 {
    static ArrayList<Integer> adj[];
    static boolean v[], arrive;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N];
        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        v = new boolean[N];

        for(int i = 0; i < adj.length; i++) {
            dfs(i, 0);

            if(arrive) {
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);
    }

    private static void dfs(int current, int cnt) {
        if(cnt == 4 || arrive) {
            arrive = true;
            return;
        }

        v[current] = true;
        for(int next: adj[current]) {
            if(v[next]) continue;
            dfs(next, cnt + 1);
        }
        v[current] = false;
    }
}
