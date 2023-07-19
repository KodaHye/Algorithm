import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
누울 자리를 찾아라
 */
public class BOJ1652 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char map[][] = new char[N][N];

        for (int r = 0; r < map.length; r++) {
            String input = br.readLine();
            for (int c = 0; c < map.length; c++) {
                map[r][c] = input.charAt(c);
            }
        }

        int hor = 0;
        int ver = 0;

        for (int r = 0; r < N; r++) {
            int len = 0;
            for (int c = 0; c < N; c++) {
                if (map[r][c] == '.') {
                    len++;
                }
                if (map[r][c] == 'X') {
                    if(len >= 2) hor++;
                    len = 0;
                }
            }

            if(len >= 2) hor++;
        }

        for (int c = 0; c < N; c++) {
            int len = 0;
            for (int r = 0; r < N; r++) {
                if (map[r][c] == '.') {
                    len++;
                }
                if (map[r][c] == 'X') {
                    if(len >= 2) ver++;
                    len = 0;
                }
            }
            if(len >= 2) ver++;
        }

        System.out.println(hor + " " + ver);
    }
}
