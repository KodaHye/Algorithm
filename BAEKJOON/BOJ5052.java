import java.io.*;

/*
전화번호 목록
 */

public class BOJ5052 {
    static class tNode {
        tNode next[] = new tNode[10];
        boolean isLeaf;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < t; testCase++) {
            boolean consistency = true;

            int n = Integer.parseInt(br.readLine());

            tNode root = new tNode();
            for (int cnt = 0; cnt < n; cnt++) {
                String str = br.readLine();

                tNode now = root;

                for (int i = 0; i < str.length(); i++) {
                    int c = str.charAt(i) - '0';

                    if (now.next[c] == null) now.next[c] = new tNode();
                    else {
                        if(i == str.length() - 1) {
                            consistency = false;
                            break;
                        }
                    }
                    now = now.next[c];

                    if (i == str.length() - 1) now.isLeaf = true;
                    else {
                        if (now.isLeaf) {
                            consistency = false;
                            break;
                        }
                    }
                }
            }

            if (consistency) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.print(sb);
    }
}
