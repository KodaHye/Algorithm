import java.io.*;
import java.util.*;

/*
문자열 집합
 */

public class BOJ14425 {
    static class tNode {
        tNode next[] = new tNode[26];
        boolean isLeaf;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        tNode root = new tNode();

        for(int k = 0; k < N; k++) {
            String str = br.readLine();

            tNode now = root;

            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if(now.next[c - 'a'] == null) now.next[c - 'a'] = new tNode();

                now = now.next[c - 'a'];
                if(i == str.length() - 1) now.isLeaf = true;
            }
        }

        int cnt = 0;
        for(int k = 0; k < M; k++) {
            String str = br.readLine();

            tNode now = root;
            for(int i = 0; i < str.length(); i++) {
                int c = str.charAt(i);

                if(now.next[c - 'a'] == null) break;
                now = now.next[c - 'a'];

                if(i == str.length() - 1 && now.isLeaf) cnt++;
            }
        }

        System.out.println(cnt);
    }
}
