import java.io.*;
import java.util.*;

/*
버블 소트
 */

public class BOJ1377 {
    static class Node {
        int idx, num;
        public Node(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Node arr[] = new Node[N];
        int answer = Integer.MIN_VALUE;

        for(int i = 0; i < arr.length; i++) arr[i] = new Node(i, Integer.parseInt(br.readLine()));
        Arrays.sort(arr, Comparator.comparingInt(o -> o.num));

        for(int i = 0; i < arr.length; i++) {
            answer = Math.max(arr[i].idx - i, answer);
        }

        System.out.println(answer + 1);
    }
}
