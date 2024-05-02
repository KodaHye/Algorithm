import java.util.*;

class 가장먼노드 {
    ArrayList<Integer> adj[];
    LinkedList<Integer> queue;

    public int solution(int n, int[][] edge) {
        int answer = 0;

        queue = new LinkedList<Integer>();
        adj = new ArrayList[n + 1];
        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Integer>();

        for(int i = 0; i < edge.length; i++) {
            int s = edge[i][0];
            int e = edge[i][1];

            adj[s].add(e);
            adj[e].add(s);
        }

        int start = 1;

        boolean v[] = new boolean[n + 1];
        int dis[] = new int[n + 1];
        int maxDis = 0;
        queue.add(start);
        v[start] = true;

        while(!queue.isEmpty()) {
            int current = queue.poll();

            for(int next: adj[current]) {
                if(v[next]) continue;

                dis[next] = dis[current] + 1;

                if(dis[next] > maxDis) {
                    maxDis = dis[next];
                    answer = 0;
                }
                if(dis[next] == maxDis) answer++;
                v[next] = true;
                queue.add(next);
            }
        }
        return answer;
    }
}