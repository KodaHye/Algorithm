import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
헌내기는 친구가 필요해
 */
public class BOJ21736 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static char map[][];
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        int count = 0;

        Queue<int []> queue = new LinkedList<>();
        boolean v[][] = new boolean[N][M];

        for(int r = 0; r < map.length; r++) {
            String input = br.readLine();
            for (int c = 0; c < map[r].length; c++) {
                map[r][c] = input.charAt(c);
                if(map[r][c] == 'I'){
                    queue.add(new int[]{r, c});
                    v[r][c] = true;
                }
            }
        }

        boolean flag = false;

        while (!queue.isEmpty()) {
            int current[] = queue.poll();

            if(map[current[0]][current[1]] == 'P') count++;

            for (int d = 0; d < 4; d++) {
                int nr = current[0] + dr[d];
                int nc = current[1] + dc[d];

                if(!check(nr, nc)) continue;
                if(map[nr][nc] != 'X' && !v[nr][nc]) {
                    queue.add(new int[] {nr, nc});
                    v[nr][nc] = true;
                }
            }
        }

        if(count == 0) System.out.println("TT");
        else System.out.println(count);
    }

    private static boolean check(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }
}
