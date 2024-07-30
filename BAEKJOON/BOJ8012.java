import java.util.*;
import java.io.*;

/*
한동이는 영업사원!
 */

public class BOJ8012 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static ArrayList<Integer> adj[];
    static int N, M, depth, p[], d[], parents[][];

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }
    static void solution() throws Exception {
        bfs();
        fillParents();

        int start = 1;
        int result = 0;

        for(int tc = 0; tc < M; tc++) {
            int end = Integer.parseInt(br.readLine());

            result += LCA(start, end);
            start = end;
        }

        System.out.println(result);
    }

    static int LCA(int a, int b) {
        int depthA = d[a];
        int depthB = d[b];
        int dis = 0;

        if(depthA > depthB) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        int diff = Math.abs(depthA - depthB);
        while(diff > 0) {
            int depth = (int) (Math.log(diff) / Math.log(2));
            b = parents[depth][b];
            diff -= Math.pow(2, depth);
            dis += Math.pow(2, depth);
        }

        for(int k = parents.length - 1; k >= 0; k--) {
            if(parents[k][a] != parents[k][b]) {
                a = parents[k][a];
                b = parents[k][b];
                dis += (Math.pow(2, k) * 2);
            }
        }

        if(a != b) dis += 2;
        return dis;
    }
    static void fillParents() {

        depth = (int) (Math.log(depth) / Math.log(2)) + 1;
        parents = new int[depth][N + 1];

        parents[0] = Arrays.copyOf(p, p.length);
        for(int k = 1; k < parents.length; k++) {
            for(int n = 1; n < parents[k].length; n++) {
                parents[k][n] = parents[k - 1][parents[k - 1][n]];
            }
        }
    }

    static void bfs() {
        depth = Integer.MIN_VALUE;
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        d[1] = 1;

        while(!q.isEmpty()) {
            int current = q.poll();
            depth = Math.max(depth, d[current]);

            for(int next: adj[current]) {
                if(d[next] != 0) continue;
                d[next] = d[current] + 1;
                p[next] = current;
                q.add(next);
            }
        }
    }

    static void initInput() throws Exception {
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N + 1];
        d = new int[N + 1];
        p = new int[N + 1];

        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        M = Integer.parseInt(br.readLine());
    }
}
