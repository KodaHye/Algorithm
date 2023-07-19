import java.io.BufferedReader;
import java.io.InputStreamReader;

/*

 */
public class BOJ3460 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        String output = "";

        for (int test = 0; test < T; test++) {
            int n = Integer.parseInt(br.readLine());
            int index =0;

            while (n > 0) {
                if(n % 2 == 1) System.out.print(index+ " ");
                n /= 2;
                index++;
            }

            System.out.println();
        }

    }
}
