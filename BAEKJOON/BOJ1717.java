import java.io.*;
import java.util.*;

/*
집합의 표현
 */

public class BOJ1717 {
    static StringBuilder sb = new StringBuilder();
    static int p[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        p = new int[n + 1];
        for(int i = 0; i < p.length; i++) p[i] = i;

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(k == 0) union(a, b);
            if(k == 1) {
                if(find(a) == find(b)) sb.append("YES\n");
                else sb.append("NO\n");
            }
        }

        System.out.print(sb);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) p[b] = a;
    }

    private static int find(int a) {
        return a == p[a] ? p[a] : find(p[a]);
    }
}
