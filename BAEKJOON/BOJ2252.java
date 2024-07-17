import java.io.*;
import java.util.*;

/*
줄 세우기
 */

public class BOJ2252 {
    static int N, M, indegree[];
    static ArrayList<Integer> adj[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        indegree = new int[N + 1];

        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            indegree[b]++;
            adj[a].add(b);
        }

        Queue<Integer> queue = new LinkedList<>();

        for(int i = 1; i < indegree.length; i++) {
            if(indegree[i] == 0) queue.add(i);
        }

        StringBuilder sb = new StringBuilder();

        while(!queue.isEmpty()) {
            int current = queue.poll();
            sb.append(current + " ");

            for(int next: adj[current]) {
                indegree[next]--;
                if(indegree[next] == 0) queue.add(next);
            }
        }

        System.out.println(sb);
    }
}
