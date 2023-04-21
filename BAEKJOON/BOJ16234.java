import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16234 {
    static int N, M;
    static PriorityQueue<Edge> roads;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        roads = new PriorityQueue<>();
        parent = new int[N];

        // 간선 구성
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            roads.add(new Edge(Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken())));
        }

        // parent 구성
        for(int i=0; i<N; i++) {
            parent[i] = i;
        }

        System.out.println(kruskal());
    }

    private static long kruskal() {
        long sum = 0;
        Edge ed;
        int cnt = 0;
        
        while(!roads.isEmpty()){
            ed = roads.poll();
            // 추가해서 사이클이 만들어지는가
            if(find(ed.v1) == find(ed.v2)) continue;
            // 안만들어지면 추가
            union(ed);
            cnt++;
            sum += ed.weight;
            if(cnt == N - 2) break;
        }
        return sum;
    }

    private static void union(Edge ed) {
        int p1 = find(ed.v1);
        int p2 = find(ed.v2);
        parent[p1] = p2;
    }

    private static int find(int v) {
        if(parent[v] == v) return v;
        return parent[v] = find(parent[v]);
    }

    static class Edge implements Comparable<Edge> {
        int v1, v2;
        int weight;

        public Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

}