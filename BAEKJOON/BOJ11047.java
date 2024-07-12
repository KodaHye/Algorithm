import java.io.*;
import java.util.*;

/*
동전 0
 */

public class BOJ11047 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int arr[] = new int[N];
        for(int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(br.readLine());

        int cnt = 0;
        for(int i = arr.length - 1; i >= 0; i--) {
            cnt += K / arr[i];
            K %= arr[i];
        }

        System.out.println(cnt);
    }
}
