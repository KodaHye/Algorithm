import java.io.*;
import java.util.*;

/*
세 수의 합
* sumArr[k] + arr[i] = arr[j]
    * sumArr[k] = arr[j] - arr[i]
    * arr[j] - arr[i]가 sumArr 배열 안에 있는지 찾으면 됨

* Arrays.binarySearch를 사용해도 됨
    if(Arrays.binarySearch(sumArr, 0, idx - 1, arr[i] - arr[j]) < 0) continue;
    result = arr[i];
    break L;
 */

public class BOJ2295 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int arr[] = new int[N];
        int sumArr[] = new int[N * N];

        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);

        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                sumArr[idx++] = arr[i] + arr[j];
            }
        }
        Arrays.sort(sumArr, 0, idx - 1);
        int result = 0;

        L: for(int i = N - 1; i >= 0; i--) {
            for(int j = i; j >= 0; j--) {

                int start = 0;
                int end = idx - 1;

                while(start < end) {
                    int mid = (start + end) / 2;

                    if(sumArr[mid] == arr[i] - arr[j]) {
                        result = arr[i];
                        break L;
                    } else if(sumArr[mid] < arr[i] - arr[j]) start = mid + 1;
                    else end = mid;
                }
            }
        }
        System.out.println(result);
    }
}
