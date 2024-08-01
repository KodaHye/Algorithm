import java.io.*;
import java.util.*;

/*
개미굴
 */

public class BOJ14725 {
    static class tNode {
        TreeMap<String, tNode> next = new TreeMap<>();
    }
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        tNode root = new tNode();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());

            tNode current = root;

            for(int n = 0; n < a; n++) {
                String s = st.nextToken();

                if(!current.next.containsKey(s)) current.next.put(s, new tNode());
                current = current.next.get(s);
            }
        }
        printWord(root, 0);
        System.out.println(sb);
    }

    private static void printWord(tNode t, int step) {
        for(String s: t.next.keySet()) {
            for(int i = 0; i < step; i++) sb.append("--");
            sb.append(s + "\n");
            printWord(t.next.get(s), step + 1);
        }
    }
}
