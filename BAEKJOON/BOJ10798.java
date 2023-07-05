import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
세로읽기
 */
public class BOJ10798 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static String arr[], result;

    public static void main(String[] args) throws Exception {
        arr = new String[5];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = br.readLine();
        }
        for (int j = 0; j < 15; j++) {
            for(int i = 0; i < 5; i++) {
                if (j < arr[i].length()) {
                    System.out.print(arr[i].charAt(j));
                }
            }
        }
    }
}
