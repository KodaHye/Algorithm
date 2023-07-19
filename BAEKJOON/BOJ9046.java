import java.util.Arrays;
import java.util.Scanner;

/*
복호화
 */
public class BOJ9046 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char arr[] = new char[] {'w', 'g', 'h', 'u', 'v', 'i', 'j', 'x', 'p', 'q', 'r', 's', 't', 'a', 'c', 'd', 'e', 'b', 'f', 'k', 'l', 'm', 'n', 'o', 'y', 'z'};

        int T = Integer.parseInt(sc.nextLine());
        char result = ' ';

        for(int i = 0; i < T; i++) {
            int count[] = new int[arr.length];

            String input = sc.nextLine();
            for(int j = 0; j < input.length(); j++) {
                for(int k = 0; k < arr.length; k++) {
                    if(input.charAt(j) == ' ') break;
                    if(input.charAt(j) == arr[k]) {
                        count[k]++;
                        break;
                    }
                }
            }

            int max = 0;
            int maxCount = 0;

            for(int j = 0; j < count.length; j++) {
                if(count[j] == max) {
                    maxCount++;
                }
                if(count[j] > max) {
                    max = count[j];
                    maxCount = 1;
                    result = arr[j];
                }
            }

            if(maxCount > 1) result = '?';
            System.out.println(result);
        }
    }
}
