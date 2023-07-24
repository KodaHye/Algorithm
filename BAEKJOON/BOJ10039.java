import java.util.Scanner;
/*
평균 점수
 */
public class BOJ10039 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int sum = 0;

        for (int i = 0; i < 5; i++) {
            int n = Integer.parseInt(sc.nextLine());

            if(n < 40) n = 40;

            sum += n;
        }
        System.out.println(sum / 5);
    }
}
