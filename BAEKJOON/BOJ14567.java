import java.io.*;
import java.util.*;

/*
선수과목
 */

public class BOJ14567 {
    static int subject[];
    static int indegree[];
    static ArrayList<Integer> adj[];

    public static void main(String[] args) throws Exception {
        initInput();
        solve();
    }

    private static void solve() {
        StringBuilder sb = new StringBuilder();

        Queue<int[]> queue = new LinkedList<>();

        for(int i = 1; i < indegree.length; i++) if(indegree[i] == 0) queue.add(new int[] {i, 1});

        while(!queue.isEmpty()) {
            int current[] = queue.poll();
            subject[current[0]] = Math.max(subject[current[0]], current[1]);

            for(int next: adj[current[0]]) {
                indegree[next]--;

                if(indegree[next] == 0) queue.add(new int[] {next, current[1] + 1});
            }
        }

        for(int i = 1; i < subject.length; i++) {
            sb.append(subject[i] + " ");
        }

        System.out.println(sb);
    }

    private static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        subject = new int[N + 1];
        indegree = new int[N + 1];
        adj = new ArrayList[N + 1];

        for(int i = 0; i < indegree.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            indegree[b]++;
            adj[a].add(b);
        }
    }
}
