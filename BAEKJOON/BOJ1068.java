import java.io.*;
import java.util.*;

/*
트리
 */

public class BOJ1068 {
    static ArrayList<Integer> adj[];
    static int root, delete;

    public static void main(String[] args) throws Exception {
        initInput();
        bfs();
    }

    public static void bfs() {
        int cnt = 0;

        Queue<Integer> q = new LinkedList<>();
        boolean v[] = new boolean[adj.length];

        q.add(root);
        v[root] = true;

        while(!q.isEmpty()) {
            int current = q.poll();

            int node = 0;
            for(int next: adj[current]) {
                if(v[next] || next == delete) continue;
                q.add(next);
                v[next] = true;
                node++;
            }

            if(node == 0) cnt++;
        }

        if(root == delete) cnt = 0;
        System.out.println(cnt);
    }

    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N];
        for(int i = 0; i < N; i++) adj[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if(n == -1) {
                root = i;
                continue;
            }

            adj[n].add(i);
        }

        delete = Integer.parseInt(br.readLine());
    }
}
