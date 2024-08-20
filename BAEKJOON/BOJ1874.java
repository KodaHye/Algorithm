import java.io.*;
import java.util.*;

/*
스택 수열
 */

public class BOJ1874 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        int num = 1;

        for(int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            if(!stack.isEmpty() && stack.peek() == n) {
                stack.pop();
                sb.append("-\n");
                continue;
            }

            while(num <= n) {
                stack.add(num);
                num++;
                sb.append("+\n");
            }

            if(stack.peek() == n) {
                stack.pop();
                sb.append("-\n");
            }
        }

        System.out.print(stack.isEmpty() ? sb : "NO");
    }
}
