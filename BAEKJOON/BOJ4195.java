import java.io.*;
import java.util.*;

/*
친구 네트워크
 */

public class BOJ4195 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Map<String, Integer> map;
    static int p[], cntArr[];

    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < T; testCase++) {
            int F = Integer.parseInt(br.readLine());
            p = new int[F * 2]; // 각각의 친구 관계마다 두 명이 존재하므로 최대 F * 2 명까지 가능
            for(int i = 0; i < p.length; i++) p[i] = i;

            cntArr = new int[F * 2]; // 친구 네트워크에 몇 명이 있는지 세는 배열
            Arrays.fill(cntArr, 1);

            map = new HashMap<>(); // 사람 이름을 정수로 바꾸기 위해 사용함
            int idx = 0;

            for(int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                String name1 = st.nextToken();
                String name2 = st.nextToken();

                if(!map.containsKey(name1)) map.put(name1, idx++);
                if(!map.containsKey(name2)) map.put(name2, idx++);

                int pa = find(map.get(name1));
                int pb = find(map.get(name2));

                if(pa != pb) {
                    cntArr[pa] += cntArr[pb];
                    union(pa, pb);
                }
                sb.append(cntArr[pa] +"\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int find(int a) {
        return a == p[a] ? a : find(p[a]);
    }
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) p[b] = a;
    }
}
