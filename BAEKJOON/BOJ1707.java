import java.io.*;
import java.util.*;

/*
이분 그래프
노드가 간선으로 이어져 있지 않은 경우도 고려할 것
 */

public class BOJ1707 {
    static StringBuilder sb = new StringBuilder();
    static int K, status[];
    static LinkedList<Integer> queue = new LinkedList<>();
    static ArrayList<Integer> adj[];
    static boolean v[];

    public static void main(String[] args) throws Exception {
        initInput();
        System.out.print(sb);
    }
    public static void solution() {
        queue.clear();
        v = new boolean[adj.length];

        for(int i = 1; i < adj.length; i++) {
            if(v[i]) continue;
            queue.add(i);
            status[i] = 1;
            v[i] = true;

            while(!queue.isEmpty()) {
                int current = queue.poll();

                for(int next: adj[current]) {
                    if(status[next] == status[current]) {
                        sb.append("NO\n");
                        return;
                    }
                    if(v[next]) continue;
                    queue.add(next);
                    status[next] = status[current] * -1;
                    v[next] = true;
                }
            }
        }

        sb.append("YES\n");
    }

    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());

        for(int test_case = 0; test_case < K; test_case++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            adj = new ArrayList[V + 1];
            status = new int[V + 1];

            for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

            for(int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                adj[s].add(e);
                adj[e].add(s);
            }
            solution();
        }
    }
}
