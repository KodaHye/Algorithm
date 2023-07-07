import java.io.*;
import java.util.*;

/*
음악 프로그램
 */
public class BOJ2623 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M, arr[];
    static boolean v[];
    static ArrayList<Integer> adj[];

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        arr = new int[N + 1];
        v = new boolean[N + 1];

        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int tmp[] = new int[a];

            int idx = 0;
            while (st.hasMoreTokens()) {
                tmp[idx] = Integer.parseInt(st.nextToken());
                idx++;
            }

            for(int j = 0; j < a - 1; j++) {
                int s = tmp[j];
                int e = tmp[j + 1];
                adj[s].add(e);
                arr[e]++;
            }
        }

        Queue<Integer> queue = new LinkedList();
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] == 0) {
                queue.add(i);
                v[i] = true;
            }
        }

        while(!queue.isEmpty()) {
            int current = queue.poll();
            sb.append(current + "\n");

            for (Integer next: adj[current]) {
                arr[next]--;
            }

            for(int i = 1; i < arr.length; i++) {
                if(!v[i] && arr[i] == 0) {
                    queue.add(i);
                    v[i] = true;
                }
            }
        }

        for(int i = 1; i < arr.length; i++) {
            if(!v[i]) {
                sb.setLength(0);
                sb.append(0);
            }
        }

        System.out.println(sb);
    }
}
