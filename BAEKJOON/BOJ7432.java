import java.io.*;
import java.util.*;

/*
디스크 트리
 */

public class BOJ7432 {
    static class tNode {
        TreeMap<String, tNode> next = new TreeMap<>();
    }

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tNode root = new tNode();

        for(int tc = 0; tc < N; tc++) {
            String arr[] = br.readLine().split("\\\\");

            tNode current = root;
            for(int i = 0; i < arr.length; i++) {
                if(!current.next.containsKey(arr[i])) {
                    current.next.put(arr[i], new tNode());
                }

                current = current.next.get(arr[i]);
            }
        }

        printDirectoryName(root, 0);

        System.out.println(sb);
    }

    static void printDirectoryName(tNode node, int order) {
        for(String s : node.next.keySet()) {
            for(int i = 0; i < order; i++) sb.append(" ");
            sb.append(s + "\n");
            printDirectoryName(node.next.get(s), order + 1);
        }
    }
}
