import java.util.*;
import java.io.*;

public class Softeer408 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int arr[];

    public static void main(String args[]) throws Exception {
        st = new StringTokenizer(br.readLine());

        arr = new int[8];

        for(int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
        
        int diff = arr[1] - arr[0];
        boolean flag = true;

        String result = "";

        if(diff < 0) {
            for(int i = 2; i < arr.length; i++) {
                if(arr[i] > arr[i - 1]) {
                    flag = false;
                    result = "mixed";
                    break;
                }
            }
            if(flag) result = "descending";
        }

        if(diff > 0) {
            for(int i = 2; i < arr.length; i++) {
                if(arr[i] < arr[i - 1]) {
                    flag = false;
                    result = "mixed";
                    break;
                }
            }
            if(flag) result = "ascending";
        }

        System.out.println(result);
    }
}