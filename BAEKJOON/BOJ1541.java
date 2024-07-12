import java.io.*;

/*
잃어버린 괄호
 */

public class BOJ1541 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String str[] = s.split("-");

        for(int i = 0; i < str.length; i++) {
            if(str[i].contains("+")) {

                String num[] = str[i].split("\\+");

                int result = 0;
                for(String n: num) result += Integer.parseInt(n);
                str[i] = String.valueOf(result);
            }
        }

        int result = Integer.parseInt(str[0]);

        for(int i = 1; i < str.length; i++) {
            result -= Integer.parseInt(str[i]);
        }
        System.out.println(result);
    }
}
