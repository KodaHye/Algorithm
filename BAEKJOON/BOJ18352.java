import java.io.*;
import java.util.*;

/*
특정 거리의 도시 찾기
 */

public class BOJ18352 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        ArrayList<Integer> adj[] = new ArrayList[N + 1];
        boolean v[] = new  boolean[N + 1];
        ArrayList<Integer> list = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            adj[s].add(e);
        }

        queue.add(new int[] {X, 0});
        v[X] = true;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            int currentNum = current[0];
            if(current[1] == K) list.add(currentNum);

            for(int next: adj[currentNum]) {
                if(v[next]) continue;
                queue.add(new int[] {next, current[1] + 1});
                v[next] = true;
            }
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for(int n: list) sb.append(n + "\n");

        System.out.print(list.size() == 0 ? -1 : sb);
    }
}
