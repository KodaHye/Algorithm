import java.io.*;
import java.util.*;

/*
계란으로 계란치기
 */

public class BOJ16987 {
    static class Egg {
        int s, w;
        public Egg(int s, int w) {
            this.s = s;
            this.w = w;
        }
    }
    static int N, maxBreakCnt;
    static ArrayList<Egg> eggs;
    public static void main(String[] args) throws Exception {
        initInput();
        func(0, eggs, 0);

        System.out.println(maxBreakCnt);
    }

    static void func(int idx, ArrayList<Egg> eggs, int result) {
        if(idx == N) {
            maxBreakCnt = Math.max(maxBreakCnt, result);
            return;
        }

        if(eggs.get(idx).s <= 0) {
            func(idx + 1, eggs, result);
            return;
        }

        boolean flag = false;
        for(int i = 0; i < N; i++) {

            if(i == idx || eggs.get(i).s <= 0) continue;
            flag = true;

            int getIS = eggs.get(i).s;
            int getIdxS = eggs.get(idx).s;

            eggs.get(i).s -= eggs.get(idx).w;
            eggs.get(idx).s -= eggs.get(i).w;

            if(eggs.get(i).s <= 0) result++;
            if(eggs.get(idx).s <= 0) result++;

            func(idx + 1, eggs, result);


            if(getIdxS * eggs.get(idx).s <= 0) result--;
            if(getIS * eggs.get(i).s <= 0) result--;

            eggs.get(i).s = getIS;
            eggs.get(idx).s = getIdxS;

        }
        if(!flag) func(idx + 1, eggs, result);
    }
    static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        eggs = new ArrayList<>();
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            eggs.add(new Egg(s, w));
        }
    }
}
