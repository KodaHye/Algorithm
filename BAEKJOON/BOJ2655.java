import java.io.*;
import java.util.*;

/*
 * 가장 높은 탑 쌓기
 */

public class BOJ2655 {

    static int maxHeight;
    static StringBuilder sb = new StringBuilder();

    static class Block implements Comparable<Block> {
        int idx, size, height, weight;

        public Block(int idx, int size, int height, int weight) {
            this.idx = idx;
            this.size = size;
            this.height = height;
            this.weight = weight;
        }

        @Override
        public int compareTo(Block o) {
            if (this.size != o.size) return Integer.compare(this.size, o.size);
            return Integer.compare(this.weight, o.weight);
        }
    }

    static LinkedHashMap<Integer, Block> blockMap = new LinkedHashMap<>();
    static ArrayList<Integer>[] prevList = new ArrayList[101];
    static int[] dp = new int[101]; // 높이에 대해 저장하는 메모이제이션 배열
    static ArrayDeque<Integer>[] path = new ArrayDeque[101]; // 최대 높이를 만들 때의 경로 저장용 (어떤 순서대로 쌓이는지)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // 밑변의 크기순 & 무게 순으로 정렬해주기 위해 PQ 사용
        PriorityQueue<Block> pq = new PriorityQueue<>();

        for (int i = 0; i < 101; i++) prevList[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            int size = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            pq.add(new Block(i + 1, size, height, weight));
        }

        while (!pq.isEmpty()) {
            Block current = pq.poll();
            blockMap.put(current.idx, current);
        }

        // prevList에 이전 블럭이 어떤게 올 수 있는지 저장함
        // blockMap은 순서대로 저장되어 이씩 때문에 b.equals(prev)가 true일 때 break를 함
        for (Block b : blockMap.values()) {
            for (Block prev : blockMap.values()) {
                if (b.equals(prev)) break;
                if (b.weight < prev.weight) continue;
                prevList[b.idx].add(prev.idx);
            }
        }

        int start = 0;
        for (int i = 0; i < 101; i++) {
            if (!blockMap.containsKey(i)) continue;
            
            int h = dfs(i); // i가 맨 위에 있을 때, 가능한 최대 높이
            if (h > maxHeight) {
                maxHeight = h;
                start = i;
            }
        }

        sb.append(path[start].size()).append("\n");
        while (!path[start].isEmpty()) {
            sb.append(path[start].poll()).append("\n");
        }

        System.out.println(sb);
    }

    static int dfs(int idx) {
        if (dp[idx] != 0) return dp[idx];

        int max = 0;
        
        ArrayDeque<Integer> tmpPath = new ArrayDeque<Integer>();

        for (int next : prevList[idx]) {
            int h = dfs(next);
            if (h > max) {
                max = h;
                tmpPath = (ArrayDeque<Integer>) path[next].clone();
            }
        }

        dp[idx] = max + blockMap.get(idx).height;

        path[idx] = tmpPath;
        path[idx].add(idx);

        return dp[idx];
    }
}
