import java.io.*;
import java.util.*;

/*
알고리즘 수업 - 병합 정렬 3
 */

public class BOJ24062 {
    static int sameCnt;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int A[], B[], tmp[], N;

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    public static void solution() {
        mergeSort(0, N - 1);
        System.out.println(0);
    }

    public static void checkSame(int origin, int k) {
        if(origin != A[k] && A[k] == B[k]) sameCnt++;
        if(origin == B[k] && origin != A[k]) sameCnt--;
        if(sameCnt == B.length) {
            System.out.println(1);
            System.exit(0);
        }
    }
    public static void mergeSort(int s, int e) {
        if(e - s < 1) return;

        int m = (s + e) / 2;
        mergeSort(s, m);
        mergeSort(m + 1, e);

        int k = s;
        int idx1 = s;
        int idx2 = m + 1;

        System.arraycopy(A, s, tmp, s, e - s + 1);
        while(idx1 <= m && idx2 <= e) {
            int origin = A[k];

            if(tmp[idx1] <= tmp[idx2]) {
                A[k] = tmp[idx1];
                checkSame(origin, k);
                idx1++;
            } else {
                A[k] = tmp[idx2];
                checkSame(origin, k);
                idx2++;
            }
            k++;
        }

        while(idx1 <= m) {
            int origin = A[k];
            A[k] = tmp[idx1];
            checkSame(origin, k);
            k++;
            idx1++;
        }
        while(idx2 <= e) {
            int origin = A[k];
            A[k] = tmp[idx2];
            checkSame(origin, k);
            k++;
            idx2++;
        }
    }
    public static void initInput() throws Exception {

        N = Integer.parseInt(br.readLine());

        A = new int[N];
        B = new int[N];
        tmp = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
            if(A[i] == B[i]) sameCnt++;
        }

        if(sameCnt == B.length) {
            System.out.println(1);
            System.exit(0);
        }
    }
}