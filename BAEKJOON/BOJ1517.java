import java.io.*;
import java.util.*;

/*
버블소트
병합정렬 시간 복잡도: O(nlogn)
 */

public class BOJ1517 {
    static int arr[], tmp[];
    static long result;

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }
    public static void solution() {
        mergeSort(0, arr.length - 1);
        System.out.println(result);
    }

    private static void mergeSort(int s, int e) {
        if(e - s < 1) return;
        int m = s + (e - s) / 2;

        mergeSort(s, m);
        mergeSort(m + 1, e);
        System.arraycopy(arr, s, tmp, s, e - s + 1);

        int k = s;
        int idx1 = s;
        int idx2 = m + 1;

        while(idx1 <= m && idx2 <= e) {
            if(tmp[idx1] <= tmp[idx2]) {
                arr[k] = tmp[idx1];
                idx1++;
            } else {
                arr[k] = tmp[idx2];
                idx2++;
                result += m - idx1 + 1;
            }
            k++;
        }

        while(idx1 <= m) {
            arr[k] = tmp[idx1];
            k++;
            idx1++;
        }

        while(idx2 <= e) {
            arr[k] = tmp[idx2];
            k++;
            idx2++;
        }
    }

    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        tmp = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
    }
}
