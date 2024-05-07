import java.io.*;
import java.util.*;

/*
문제집
 */

public class BOJ1766 {
    static int inArr[];
    static ArrayList<Integer> adj[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        initInput();
        solve();
    }

    static void solve() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for(int i = 1; i < inArr.length; i++) {
            if(inArr[i] == 0) queue.add(i);
        }

        while(!queue.isEmpty()) {
            int current = queue.poll();
            sb.append(current + " ");

            for(int next: adj[current]) {
                inArr[next]--;
                if(inArr[next] == 0) queue.add(next);
            }
        }

        System.out.print(sb);
    }

    static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        inArr = new int[N + 1];
        adj = new ArrayList[N + 1];
        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            inArr[b]++;
        }
    }
}
