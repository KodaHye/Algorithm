import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
차집합
 */
public class BOJ1822 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        TreeSet<Integer> A = new TreeSet<>();

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if(A.contains(tmp)) A.remove(tmp);
        }

        int size = A.size();
        sb.append(size + "\n");
        if(size != 0) {
            while (!A.isEmpty()) {
                sb.append(A.pollFirst() + " ");
            }
        }

        System.out.println(sb);
    }
}
