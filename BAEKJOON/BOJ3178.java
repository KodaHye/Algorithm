import java.io.*;
import java.util.*;

/*
코코스
String + 연산할 때 메모리 초과 주의하기
StringBuilder를 사용하자
 */

public class BOJ3178 {
    static class tNode {
        tNode[] next = new tNode[26];
    }
    static tNode leftRoot, rightRoot, leftCurrent, rightCurrent;
    static int N, K, cnt;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void solution() throws Exception {
        for(int k = 0; k < N; k++) {
            String str = br.readLine();

            leftCurrent = leftRoot;
            rightCurrent = rightRoot;

            for(int i = 0; i < K; i++) {
                char left = str.charAt(i);
                char right = str.charAt(str.length() - 1 - i);

                if(leftCurrent.next[left - 'A'] == null) {
                    leftCurrent.next[left - 'A'] = new tNode();
                    cnt++;
                }

                if(rightCurrent.next[right - 'A'] == null) {
                    rightCurrent.next[right - 'A'] = new tNode();
                    cnt++;
                }

                leftCurrent = leftCurrent.next[left - 'A'];
                rightCurrent = rightCurrent.next[right - 'A'];
            }
        }

        System.out.println(cnt);
    }
    static void initInput() throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        leftRoot = new tNode();
        rightRoot = new tNode();
    }
}