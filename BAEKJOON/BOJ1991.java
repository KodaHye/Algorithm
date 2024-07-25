import java.io.*;
import java.util.*;

/*
트리 순회
 */

public class BOJ1991 {
    static class Node {
        int left, right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
    static StringBuilder sb = new StringBuilder();
    static Node binaryTree[];

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void solution() {
        preOrder(0);
        sb.append("\n");
        inOrder(0);
        sb.append("\n");
        postOrder(0);
        System.out.println(sb);
    }

    static void postOrder(int idx) {
        if(idx == -1) return;
        postOrder(binaryTree[idx].left);
        postOrder(binaryTree[idx].right);
        sb.append(itoc(idx));
    }

    static void inOrder(int idx) {
        if(idx == -1) return;
        inOrder(binaryTree[idx].left);
        sb.append(itoc(idx));
        inOrder(binaryTree[idx].right);
    }
    static void preOrder(int idx) {
        if(idx == -1) return;
        sb.append(itoc(idx));
        preOrder(binaryTree[idx].left);
        preOrder(binaryTree[idx].right);
    }

    static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        binaryTree = new Node[26];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int c = ctoi(st.nextToken().charAt(0));
            int l = ctoi(st.nextToken().charAt(0));
            int r = ctoi(st.nextToken().charAt(0));

            binaryTree[c] = new Node(l, r);
        }
    }
    private static char itoc(int idx) {
        return (char) (idx + 'A');
    }
    private static int ctoi(char c) {
        if(c == '.') return -1;
        return c - 'A';
    }
}
