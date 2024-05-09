import java.io.*;
import java.util.*;

/*
프로젝트 스케줄링
 */

public class BOJ14907 {
    static int inArr[], time[], result[];
    static ArrayList<Integer> adj[];

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    private static void solution() {
        Queue<Integer> queue = new LinkedList<>();

        int resultTime = 0;
        for(int i = 0; i < inArr.length; i++) {
            if(time[i] > 0 && inArr[i] == 0) {
                queue.add(i);
                result[i] = time[i];
            }
        }

        while(!queue.isEmpty()) {
            int current = queue.poll();
            resultTime = Math.max(resultTime, time[current]);

            for(int next: adj[current]) {
                result[next] = Math.max(result[next], time[next] + result[current]);
                resultTime = Math.max(result[next], resultTime);

                inArr[next]--;
                queue.add(next);
            }
        }

        System.out.println(resultTime);
    }

    private static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        inArr = new int[26];
        time = new int[26];
        adj = new ArrayList[26];
        result = new int[26];
        Arrays.fill(result, Integer.MIN_VALUE);

        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        char c = st.nextToken().charAt(0);
        int n = Integer.parseInt(st.nextToken());
        time[ctoi(c)] = n;

        String str;

        while ((str = br.readLine()) != null) {
            if (str.isEmpty()) break;
            st = new StringTokenizer(str);

            int a = ctoi(st.nextToken().charAt(0));
            n = Integer.parseInt(st.nextToken());
            time[a] = n;

            if(!st.hasMoreTokens()) continue;
            String s = st.nextToken();
            for(int idx = 0; idx < s.length(); idx++) {
                int b = ctoi(s.charAt(idx));
                adj[b].add(a);
                inArr[a]++;
            }
        }

    }

    private static int ctoi(char c) {
        return c - 'A';
    }
}