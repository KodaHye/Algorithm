import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
CCW
 */

public class BOJ11758 {
    static class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Point[] points;

    public static void main(String[] args) throws Exception {
        points = new Point[3];

        for(int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());

            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            points[i] = new Point(x, y);
        }

        System.out.println(CCW(points));
    }

    private static int CCW(Point[] points) {
        int result = 0;

        long ccw1 = points[0].x * points[1].y + points[1].x * points[2].y + points[2].x * points[0].y;
        long ccw2 = points[1].x * points[0].y + points[2].x * points[1].y + points[0].x * points[2].y;

        if(ccw1 - ccw2 > 0) result = 1;
        else if(ccw1 - ccw2 < 0) result = -1;

        return result;
    }
}
