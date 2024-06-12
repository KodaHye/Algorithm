import java.util.*;
import java.io.*;

/*
바이러스 검사
int, long 범위 잘 파악하기!!
*/

public class 바이러스검사 {
    static int N, customer[], leaderMax, memberMax;
    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    public static void solution() {
        long result = 0;

        for(int i = 0; i < N; i++) {
            int current = customer[i];

            current -= leaderMax;
            result += 1;

            if(current <= 0) continue;
            result += current / memberMax;
            if((current % memberMax) != 0) result += 1;
        }

        System.out.println(result);
    }
    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        customer = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            customer[i] = Integer.parseInt(st.nextToken()); 
        }

        st = new StringTokenizer(br.readLine());
        leaderMax = Integer.parseInt(st.nextToken());
        memberMax = Integer.parseInt(st.nextToken());

    }
}
