import java.io.*;
import java.util.*;

/*
싸이버개강총회
 */

public class BOJ19583 {
    static class Time {
        int hour, min;
        public Time(int hour, int min) {
            this.hour = hour;
            this.min = min;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Time S, E, Q;
    static HashSet<String> set;

    static void setStreamingTime(String s) {
        st = new StringTokenizer(s);

        S = setTime(st.nextToken());
        E = setTime(st.nextToken());
        Q = setTime(st.nextToken());
   }

   static Time setTime(String s) {
       String time[] = s.split(":");
       return new Time(Integer.parseInt(time[0]), Integer.parseInt(time[1]));
   }

   static boolean validateTime(Time t1, Time t2) {
        return 60 * (t1.hour - t2.hour) + (t1.min - t2.min) >= 0;
   }
   static boolean checkStart(Time t) {
        return validateTime(S, t);
   }

   static boolean checkEnd(Time t) {
        return validateTime(t, E) && validateTime(Q, t);
   }

   public static void main(String[] args) throws Exception {

        setStreamingTime(br.readLine());
        set = new HashSet<>();

        String line;
        int result = 0;

        while((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);

            Time t = setTime(st.nextToken());
            String name = st.nextToken();

            if(checkStart(t)) set.add(name);
            if(checkEnd(t)) {
                if(set.contains(name)) {
                    set.remove(name);
                    result++;
                }
            }
        }

       System.out.println(result);
    }
}
