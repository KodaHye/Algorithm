import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13398 {
    /*
    연속합 2
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, arr[], L[], R[], result;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        arr = new int[n];
        L = new int[n];
        R = new int[n];
        result = Integer.MIN_VALUE;

        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        L[0] = arr[0];
        R[n - 1] = arr[n - 1];

        for(int i = 1; i < L.length; i++) {
            L[i] = Math.max(arr[i], L[i - 1] + arr[i]);
            result = Math.max(result, L[i]);
        }

        for(int i = n - 2; i >= 0; i--) {
            R[i] = Math.max(arr[i], R[i + 1] + arr[i]);
            result = Math.max(result, L[i]);
        }

        if(arr.length == 1) {
            System.out.println(arr[0]);
            return;
        }

        for(int i = 0; i < n; i++) {
            if(i == 0) result = Math.max(result, R[i + 1]);
            else if(i == n - 1) result = Math.max(result, L[i - 1]);
            else result = Math.max(result, L[i - 1] + R[i + 1]);
        }

        System.out.println(result);
    }
}
