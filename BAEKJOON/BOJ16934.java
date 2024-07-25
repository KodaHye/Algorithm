import java.io.*;

/*
게임 닉네임
 */

public class BOJ16934 {
    static class tNode {
        tNode next[] = new tNode[26];
        int cnt;
    }
    static StringBuilder sb = new StringBuilder();
    static tNode root;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int cnt = 0;

        root = new tNode();

        while(cnt++ < N) {
            String str = br.readLine();
            trie(str);
        }

        System.out.print(sb);
    }

    static void trie(String str) {
        tNode current = root;
        int idx = 0;
        int cnt = 0;
        boolean flag = false;

        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if(current.next[c - 'a'] == null) {
                current.next[c - 'a'] = new tNode();

                if(!flag) {
                    idx = i;
                    flag = true;
                }
            }

            current = current.next[c - 'a'];
            if(i == str.length() - 1) {
                current.cnt++;
                cnt = current.cnt;
            }
        }

        sb.append(flag ? str.substring(0, idx + 1): str).append(cnt > 1 ? cnt : "").append("\n");
    }
}
