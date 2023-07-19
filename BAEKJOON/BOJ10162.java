import java.util.Scanner;

/*
전자레인지
 */
public class BOJ10162 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();

        if(T % 10 != 0) sb.append(-1);
        else {
            for(int i = 0; i < 3; i++) {
                if(i == 0) {
                    sb.append((T / 300) + " ");
                    T %= 300;
                }
                if(i == 1) {
                    sb.append((T / 60) + " ");
                    T %= 60;
                }
                if(i == 2) {
                    sb.append((T / 10) + " ");
                    T %= 10;
                }
            }
        }

        System.out.println(sb.toString());
    }
}
