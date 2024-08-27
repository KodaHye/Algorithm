import java.io.*;
import java.util.*;

/*
전구와 스위치
 */

public class BOJ2138 {
    static boolean input[], target[], tmp[];
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        input = new boolean[N];
        target = new boolean[N];
        tmp = new boolean[N];
        String s = br.readLine();
        for(int i = 0; i < s.length(); i++) if(s.charAt(i) == '1') input[i] = true;

        s = br.readLine();
        for(int i = 0; i < s.length(); i++) if(s.charAt(i) == '1') target[i] = true;

        tmp = Arrays.copyOf(input, input.length);
        clickSwitch();
        check();

        tmp = Arrays.copyOf(input, input.length);
        result = 0;

        result++;
        change(tmp, 0);
        change(tmp, 1);

        clickSwitch();
        check();

        System.out.println(-1);
    }

    static void clickSwitch() {
        for(int i = 1; i < tmp.length; i++) {
            if(tmp[i - 1] != target[i - 1]) {
                result++;
                change(tmp, i - 1);
                change(tmp, i);
                if(i != tmp.length - 1)
                    change(tmp, i + 1);
            }
        }
    }

    static void change(boolean tmp[], int idx) {
        tmp[idx] = !tmp[idx];
    }

    static void check() {
        boolean flag = true;
        for(int i = 0; i < tmp.length; i++) {
            if(tmp[i] != target[i]) {
                flag = false;
                break;
            }
        }

        if(flag) {
            System.out.println(result);
            System.exit(0);
        }
    }
}
