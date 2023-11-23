import java.util.*;

public class 개인정보수집유효기간 {
    static StringTokenizer st;

    public static int[] solution(String today, String[] terms, String[] privacies) {
        HashMap<Character, Integer> termDetail = new HashMap<>();
        TreeSet<Integer> result = new TreeSet();

        int today_date = date_to_days(today);

        System.out.println(today_date);

        for(int i = 0; i < terms.length; i++) {
            st = new StringTokenizer(terms[i]);
            char type = st.nextToken().charAt(0);
            int term = Integer.parseInt(st.nextToken());

            termDetail.put(type, term);
        }

        for(int i = 0; i < privacies.length; i++) {
            st = new StringTokenizer(privacies[i]);
            String date = st.nextToken();
            char type = st.nextToken().charAt(0);

            int privaciesDays = date_to_days(date) + termDetail.get(type) * 28;

            if(privaciesDays <= today_date) result.add(i + 1);
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    static int date_to_days(String inputDate) {
        String date[] = inputDate.split("\\.");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);

        return year * 12 * 28 + month * 28 + day;
    }
}