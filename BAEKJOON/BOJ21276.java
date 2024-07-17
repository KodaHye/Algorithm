import java.io.*;
import java.util.*;

/*
계보 복원가 호석
 */

public class BOJ21276 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int indegree[] = new int[N];

        ArrayList<Integer> adj[] = new ArrayList[N + 1];
        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        TreeMap<String, Integer> nameToIdx = new TreeMap<>();
        TreeMap<Integer, String> idxToName = new TreeMap<>();
        HashMap<Integer, TreeSet<Integer>> relation = new HashMap<>();
        for(int i = 0; i < N; i++) relation.put(i, new TreeSet<>());

        TreeSet<String> name = new TreeSet<>();

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) name.add(st.nextToken());
        for(int i = 0; i < N; i++) {
            String s = name.pollFirst();
            nameToIdx.put(s, i);
            idxToName.put(i, s);
        }

        int M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = nameToIdx.get(st.nextToken());
            int b = nameToIdx.get(st.nextToken());

            indegree[a]++;
            adj[b].add(a);
        }

        Queue<Integer> queue = new LinkedList<>();

        int cnt = 0;
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
                cnt++;

                sb.append(idxToName.get(i) + " ");
            }
        }
        sb = new StringBuilder(cnt + "\n" + sb + "\n");

        while(!queue.isEmpty()) {
            int current = queue.poll();

            for(int next: adj[current]) {
                indegree[next]--;

                if(indegree[next] == 0) {
                    queue.add(next);
                    relation.get(current).add(next);
                }
            }
        }

        for(int idx: idxToName.keySet()) {
            sb.append(idxToName.get(idx) + " " + relation.get(idx).size() + " ");
            for(int child: relation.get(idx))
                sb.append(idxToName.get(child) + " ");
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
