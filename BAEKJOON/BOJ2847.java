import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
게임을 만든 동준이
 */
public class BOJ2847 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];
        int sum = 0;

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());

        int tmp = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            if(tmp - 1 < arr[i]) {
                sum += (arr[i] - (tmp - 1));
                tmp = tmp - 1;
            } else {
                tmp = arr[i];
            }
        }

        System.out.println(sum);
    }
}
