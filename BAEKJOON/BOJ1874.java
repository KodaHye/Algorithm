import java.io.*;
import java.util.*;

/*
스택 수열
 */

public class BOJ1874 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int num = 1;
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            int current = Integer.parseInt(br.readLine());

            if(!stack.isEmpty() && stack.peek() == current) {
                stack.pop();
                sb.append("-\n");
                continue;
            }

            while(num <= current) {
                stack.add(num);
                num++;
                sb.append("+\n");
            }

            if(stack.peek() == current) {
                stack.pop();
                sb.append("-\n");
            }
        }

        if(stack.isEmpty()) System.out.print(sb);
        else System.out.print("NO");
    }
}
