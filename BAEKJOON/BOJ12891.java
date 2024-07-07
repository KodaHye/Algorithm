import java.io.*;
import java.util.*;

/*
DNA 비밀번호
 */

public class BOJ12891 {
    static int S, P, result;
    static String str;
    static int dna[], check[];

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    public static void solution() {

        for(int i = 0; i < P; i++) addDna(i);
        checkStr();

        for(int i = 0; i < S - P; i++) {
            subDna(i);
            addDna(i + P);
            checkStr();
        }
        System.out.println(result);
    }

    private static void subDna(int i) {
        if(str.charAt(i) == 'A') check[0]--;
        if(str.charAt(i) == 'C') check[1]--;
        if(str.charAt(i) == 'G') check[2]--;
        if(str.charAt(i) == 'T') check[3]--;
    }

    private static void addDna(int i) {
        if(str.charAt(i) == 'A') check[0]++;
        if(str.charAt(i) == 'C') check[1]++;
        if(str.charAt(i) == 'G') check[2]++;
        if(str.charAt(i) == 'T') check[3]++;
    }

    private static void checkStr() {
        for(int i = 0; i < 4; i++) if(dna[i] > check[i]) return;
        result++;
    }

    public static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        str = br.readLine();
        st = new StringTokenizer(br.readLine());

        dna = new int[4];
        check = new int[4];

        for(int i = 0; i < 4; i++) dna[i] = Integer.parseInt(st.nextToken());
    }
}
