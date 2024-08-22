import java.io.*;
import java.util.*;

/*
ZOAC 3
 */

public class BOJ20436 {
    static class Point {
        int r, c;
        public Point (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static String keyboard[] = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
    static HashSet<Character> set = new HashSet<>(Arrays.asList('q', 'w', 'e', 'r', 't', 'a', 's', 'd', 'f', 'g', 'z', 'x', 'c', 'v'));
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Point l = getPoint(st.nextToken().charAt(0));
        Point r = getPoint(st.nextToken().charAt(0));

        String s = br.readLine();

        int result = 0;
        for(int i = 0; i < s.length(); i++) {
            Point target = getPoint(s.charAt(i));

            int dis1 = getDis(l, target);
            int dis2 = getDis(r, target);

            if(set.contains(s.charAt(i))) {
                result += dis1 + 1;
                l = target;
            } else {
                result += dis2 + 1;
                r = target;
            }
        }

        System.out.println(result);
    }

    static int getDis(Point p1, Point p2) {
        return Math.abs(p1.r - p2.r) + Math.abs(p1.c - p2.c);
    }
    static Point getPoint(char ch) {
        for(int r = 0; r < keyboard.length; r++) {
            for(int c = 0; c < keyboard[r].length(); c++) {
                if(keyboard[r].charAt(c) == ch) {
                    return new Point(r, c);
                }
            }
        }

        return null;
    }
}
