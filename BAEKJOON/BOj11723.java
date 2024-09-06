import java.io.*;
import java.util.*;

/*
집합
 */

public class BOj11723 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());
        int bit = 0;

        for(int tc = 0; tc < M; tc++) {
            st = new StringTokenizer(br.readLine());
            String order = st.nextToken();

            int num = 0;
            if(!order.equals("all") && !order.equals("empty"))
                num = Integer.parseInt(st.nextToken());
            switch (order) {
                case "add":
                    bit |= (1 << num);
                    break;
                case "remove":
                    bit &= ~(1 << num);
                    break;
                case "check":
                    sb.append((bit & (1 << num)) != 0 ? "1\n" : "0\n");
                    break;
                case "toggle":
                    bit ^= (1 << num);
                    break;
                case "all":
                    bit |= (~0);
                    break;
                case "empty":
                    bit = 0;
                    break;
            }
        }

        System.out.print(sb);
    }
}
