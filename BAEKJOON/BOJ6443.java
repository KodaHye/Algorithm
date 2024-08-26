import java.io.*;
import java.util.*;

/*
애너그램
TreeSet을 쓰지 않아도 풀림
알파벳순이라고 해서 무조건 TreeSet 쓰자! 라는 생각 버리기
 */

public class BOJ6443 {
    static char sel[];
    static int cnt[];
    static StringBuilder sb;

    static void func(int k) {
        if(k == sel.length) {
            sb.append(String.valueOf(sel) + "\n");
            return;
        }

        for(int i = 0; i < cnt.length; i++) {
            if(cnt[i] == 0) continue;
            cnt[i]--;
            sel[k] = (char) ('a' + i);
            func(k + 1);
            cnt[i]++;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        cnt = new int[26];

        for(int i = 0; i < N; i++ ) {
            String s = br.readLine();
            sel = new char[s.length()];

            for(int k = 0; k < s.length(); k++) cnt[s.charAt(k) - 'a']++;

            func(0);
            Arrays.fill(cnt, 0);
        }

        System.out.print(sb);
    }
}
