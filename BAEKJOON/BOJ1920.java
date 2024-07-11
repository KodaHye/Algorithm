import java.io.*;
import java.util.*;

/*
수 찾기
 */

public class BOJ1920 {
    static int arr[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < M; i++) {
            boolean isIn = binarySearch(Integer.parseInt(st.nextToken()));
            if(isIn) sb.append("1\n");
            else sb.append("0\n");
        }

        System.out.print(sb);
    }

    public static boolean binarySearch(int n) {
        int s = 0;
        int e = arr.length - 1;

        while(s <= e) {
            int m = (s + e) / 2;
            if(arr[m] < n) {
                s = m + 1;
            } else if(arr[m] > n) {
                e = m - 1;
            } else return true;
        }
        return false;
    }
}
