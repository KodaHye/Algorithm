import java.util.Scanner;
/*
!밀비 급일
 */
public class BOJ11365 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        while(true) {
            String input = sc.nextLine();

            if(input.equals("END")) break;
            for(int i = 0; i < input.length(); i++) sb.append(input.charAt(input.length() - 1 - i));
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
