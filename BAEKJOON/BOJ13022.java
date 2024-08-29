import java.io.*;
import java.util.*;

/*
늑대와 올바른 단어
 */

public class BOJ13022 {
    public static void main(String[] args) throws Exception {
        int cnt[] = new int[4];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        boolean check = true;

        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int idx = ctoi(c);

            cnt[idx]++;
            if(i != input.length() - 1 && input.charAt(i) == input.charAt(i + 1)) continue;
            for(int k = 0; k < idx; k++) {
                if(cnt[k] != cnt[idx]) {
                    check = false;
                    break;
                }
            }

            if(i != input.length() - 1 && input.charAt(i) != input.charAt(i + 1) && input.charAt(i + 1) == 'w') {
                Arrays.fill(cnt, 0);
            }
        }

        for(int i = 0; i < cnt.length; i++) {
            if(cnt[i] == 0) {
                System.out.println(0);
                return;
            }
        }

        if(!check) System.out.println(0);
        else System.out.println(1);
    }

    static int ctoi(char c) {
        if(c == 'w') return 0;
        if(c == 'o') return 1;
        if(c == 'l') return 2;
        return 3;
    }
}
