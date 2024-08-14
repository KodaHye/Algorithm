import java.io.*;
import java.util.*;

/*
한 줄로 서기
 */

public class BOJ1138 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int arr[] = new int[N];
        for(int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = arr.length - 1; i >= 0; i--) list.add(arr[i], i);

        StringBuilder sb = new StringBuilder();
        for(int order: list) sb.append((order + 1) + " ");

        System.out.print(sb);

    }
}
