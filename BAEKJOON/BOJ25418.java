import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
정수 a를 k로 만들기
 */
public class BOJ25418 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean v[] = new boolean[1000001];

        Queue<int []> queue = new LinkedList<>();

        queue.add(new int[] {A, 0});
        v[A] = true;

        int result = 0;

        while (!queue.isEmpty()) {
            int current[] = queue.poll();
            if(current[0] == K) {
                result = current[1];
                break;
            }
            if(current[0] + 1 < 1000001 && !v[current[0] + 1]) {
                v[current[0] + 1] = true;
                queue.add(new int[] {current[0] + 1, current[1] + 1});
            }
            if(current[0] * 2 < 1000001 && !v[current[0] * 2]) {
                v[current[0] * 2] = true;
                queue.add(new int[] {current[0] * 2, current[1] + 1});
            }
        }

        System.out.println(result);
    }
}
