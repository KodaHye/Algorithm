import java.io.*;
import java.util.*;

/*
평범한 배낭
 */

public class BOJ12865 {
    static class Item {
        int w, v;
        public Item(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }

    static int bag[][];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        bag = new int[N + 1][K + 1];
        Item items[] = new Item[N + 1];

        for(int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            items[i] = new Item(w, v);
        }

        for(int i = 1; i < bag.length; i++) {
            for(int w = 1; w < K + 1; w++) {
                if(w >= items[i].w) {
                    bag[i][w] = Math.max(items[i].v + bag[i - 1][w - items[i].w], bag[i - 1][w]);
                } else {
                    bag[i][w] = bag[i - 1][w];
                }
            }
        }

        System.out.println(bag[bag.length - 1][bag[0].length - 1]);
    }
}
