import java.io.*;

/*
거북이
 */

public class BOJ8911 {
    static int dr[] = {-1, 0, 1, 0};
    static int dc[] = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            String s = br.readLine();

            int r = 0, c = 0, maxR = 0, maxC = 0, minR = 0, minC = 0;
            int dir = 0;

            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == 'L') {
                    dir = (dir + 1) % 4;
                    continue;
                }
                if(s.charAt(i) == 'R') {
                    dir = dir - 1 < 0 ? dir + 3: dir - 1;
                    continue;
                }

                if(s.charAt(i) == 'F') {
                    r += dr[dir];
                    c += dc[dir];
                }
                if(s.charAt(i) == 'B') {
                    r -= dr[dir];
                    c -= dc[dir];
                }

                minR = Math.min(r, minR);
                minC = Math.min(c, minC);
                maxR = Math.max(r, maxR);
                maxC = Math.max(c, maxC);

            }
            sb.append((Math.abs((maxR - minR)) * Math.abs(maxC - minC)) + "\n");
        }

        System.out.print(sb);
    }
}
