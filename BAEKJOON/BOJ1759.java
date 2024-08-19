import java.io.*;
import java.util.*;

/*
암호 만들기
 */

public class BOJ1759 {
    static int L, C;
    static char arr[], sel[];
    static StringBuilder sb = new StringBuilder();

    static void func(int depth, int idx, int mo, int ja) {
        if(depth == L) {
            if(mo < 1 || ja < 2) return;
            for(int i = 0; i < sel.length; i++) sb.append(sel[i]);
            sb.append("\n");
            return;
        }

        for(int i = idx; i < C; i++) {
            sel[depth] = arr[i];
            boolean trueMo = false;
            if(arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
                trueMo = true;
                mo++;
            } else ja++;
            func(depth + 1, i + 1, mo, ja);
            if(trueMo) mo--;
            else ja--;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        sel = new char[L];

        String s = br.readLine();
        for(int i = 0; i < C; i++) arr[i] = s.charAt(i * 2);
        Arrays.sort(arr);

        func(0, 0,0, 0);
        System.out.print(sb);
    }
}
