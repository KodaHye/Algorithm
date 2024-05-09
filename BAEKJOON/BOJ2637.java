import java.io.*;
import java.util.*;

/*
장난감 조립
 */

public class BOJ2637 {
    static class Node {
        int e, w;

        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Node> adj[];
    static int inArr[], result[];

    public static void main(String[] args) throws Exception {
        initInput();
        func();
    }

    private static void func() {

        Queue<Integer> queue = new LinkedList<>();
        TreeSet<Integer> set = new TreeSet<>();

        for(int i = 1; i < inArr.length; i++)
            if(inArr[i] == 0) {
                queue.add(i);
                result[i] = 1;
            }


        while(!queue.isEmpty()) {
            int current = queue.poll();

            boolean flag = false;
            for(Node next: adj[current]) {
                flag = true;
                result[next.e] += (result[current] * next.w);

                inArr[next.e]--;
                if(inArr[next.e] == 0) queue.add(next.e);
            }
            if(!flag) set.add(current);
        }

        for(int n: set) {
            sb.append(n + " " + result[n] + "\n");
        }

        System.out.println(sb);
    }

    private static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];
        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        inArr = new int[N + 1];
        result = new int[N + 1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, w));
            inArr[b]++;
        }
    }
}
