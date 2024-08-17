import java.text.*;
import java.util.*;

public class test {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        date.setTimeZone(TimeZone.getTimeZone("UTC"));

        String s[] = date.format(Calendar.getInstance().getTime()).split("-");

        System.out.println(s[0]);
        System.out.println(s[1]);
        System.out.println(s[2]);
    }
}
