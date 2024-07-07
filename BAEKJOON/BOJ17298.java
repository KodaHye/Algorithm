import java.io.*;
import java.util.*;

/*
오큰수
 */

public class BOJ17298 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];
        int ans[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < N; i++) {
            if(!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                while(arr[stack.peek()] < arr[i]) {
                    ans[stack.pop()] = arr[i];
                    if(stack.size() == 0) break;
                }
            }

            stack.add(i);
        }

        while(!stack.isEmpty()) ans[stack.pop()] = -1;

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ans.length; i++) sb.append(ans[i] + " ");

        System.out.println(sb);
    }
}
