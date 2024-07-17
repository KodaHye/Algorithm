import java.io.*;
import java.util.*;

/*
Strahler 순서
 */

public class BOJ9470 {
    static class Node {
        int order, cnt;

        public Node(int order, int cnt) {
            this.order = order;
            this.cnt = cnt;
        }
    }
    static StringBuilder sb = new StringBuilder();
    static int indegree[];
    static Node nodeInfo[];
    static ArrayList<Integer> adj[];
    static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < T; testCase++) {
            st = new StringTokenizer(br.readLine());

            int K = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            indegree = new int[M + 1];
            nodeInfo = new Node[M + 1];
            adj = new ArrayList[M + 1];

            for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

            for(int i = 0; i < P; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                indegree[b]++;
                adj[a].add(b);
            }

            for(int i = 1; i < indegree.length; i++) {
                if(indegree[i] == 0) {
                    queue.add(i);
                    nodeInfo[i] = new Node(1, 1);
                }
            }

            int result = 0;

            while(!queue.isEmpty()) {
                int current = queue.poll();

                if(nodeInfo[current].cnt > 1) nodeInfo[current].order += 1;
                result = Math.max(result, nodeInfo[current].order);

                for(int next: adj[current]) {
                    indegree[next]--;
                    if(indegree[next] == 0) {
                        queue.add(next);
                    }

                    if(nodeInfo[next] == null) nodeInfo[next] = new Node(nodeInfo[current].order, 1);
                    else {
                        int nextOrder = nodeInfo[next].order;
                        int currentOrder = nodeInfo[current].order;

                        if(nextOrder == currentOrder) {
                            nodeInfo[next].order = currentOrder;
                            nodeInfo[next].cnt++;
                        }
                        if(nextOrder < currentOrder) {
                            nodeInfo[next].order = currentOrder;
                            nodeInfo[next].cnt = 1;
                        }
                    }
                }
            }

            sb.append(K + " " + result + "\n");
        }

        System.out.print(sb);
    }
}
