import java.io.*;

/*
사다리 타기
 */

public class BOJ2469 {
    static char map[][];
    static int k, n, guessIdx;
    static char top[], result[];

    static void topToBottom(int r) {
        for(int c = 0; c < top.length - 1; c++) {
            char ch = map[r][c];
            if(ch == '-') {
                char prev = top[c + 1];
                top[c + 1] = top[c];
                top[c] = prev;
            }
        }
    }

    static void bottomToTop(int r) {
        for(int c = 0; c < result.length - 1; c++) {
            char ch = map[r][c];
            if(ch == '-') {
                char prev = result[c];
                result[c] = result[c + 1];
                result[c + 1] = prev;
            }
        }
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());

        result = br.readLine().toCharArray();
        map = new char[n][k - 1];

        for(int r = 0; r < map.length; r++) {
            String input = br.readLine();
            for(int c = 0; c < map[0].length; c++) {
                map[r][c] = input.charAt(c);
                if(map[r][c] == '?') guessIdx = r;
            }
        }

        top = new char[k];
        for(int i = 0; i < top.length; i++) top[i] = (char) (i + 'A');

        for(int r = 0; r < guessIdx; r++) topToBottom(r);
        for(int r = map.length - 1; r > guessIdx; r--) bottomToTop(r);

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < top.length - 1; i++) {
            if(top[i] != result[i]) {
                if(top[i] == result[i + 1]) {
                    if(i == top.length - 2) sb.append("-");
                    else {
                        sb.append("-*");
                        i++;
                    }
                } else {
                    sb = new StringBuilder();
                    for(int k = 0; k < top.length - 1; k++) sb.append('x');
                    break;
                }
            } else sb.append('*');
        }
        System.out.println(sb);
    }
}

