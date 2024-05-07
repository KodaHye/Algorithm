import java.io.*;
import java.util.*;

/*
ACM Craft
 */

public class BOJ1005 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int arr[], buildTime[], result[];
        ArrayList<Integer> adj[];
        Queue<Integer> queue;

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 0; test_case < T; test_case++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            queue = new LinkedList<>();
            arr = new int [N + 1]; // 진입차수 배열
            buildTime = new int[N + 1];
            adj = new ArrayList[N + 1];
            result = new int[N + 1];

            for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            for(int i = 1; i < buildTime.length; i++) buildTime[i] = Integer.parseInt(st.nextToken());

            for(int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                adj[a].add(b);
                arr[b]++;
            }

            int W = Integer.parseInt(br.readLine());

            for(int i = 1; i < arr.length; i++) {
                result[i] = buildTime[i];
                if(arr[i] == 0) queue.add(i);
            }

            while(!queue.isEmpty()) {
                int current = queue.poll();

                for(int next: adj[current]) {
                    result[next] = Math.max(result[next], result[current] + buildTime[next]);
                    arr[next]--;
                    if(arr[next] == 0) queue.add(next);
                }
            }

            sb.append(result[W] + "\n");
        }

        System.out.print(sb);
    }
}
