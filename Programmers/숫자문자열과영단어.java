import java.util.*;

public class 숫자문자열과영단어 {
    public static long solution(String s) {
        String answer = "";

        HashMap<String, Integer> hashMap = new HashMap<>();

        hashMap.put("zero", 0);
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);
        hashMap.put("four", 4);
        hashMap.put("five", 5);
        hashMap.put("six", 6);
        hashMap.put("seven", 7);
        hashMap.put("eight", 8);
        hashMap.put("nine", 9);

        String str = "";

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int number = c;

            // 숫자
            if(number >= 48 && number <= 48 + 9) {
                answer += String.valueOf(c);

                // 알파벳
            } else {
                str += String.valueOf(c);
                // 만약 str이 hashMap의 키에 있으면
                if(hashMap.containsKey(str)) {
                    answer += hashMap.get(str);
                    str = "";
                }
            }
        }
        return Integer.parseInt(answer);
    }

    public static long solution2(String s) {
        String strArr[] = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        for(int i = 0; i < strArr.length; i++) {
            s = s.replaceAll(strArr[i], Integer.toString(i));
        }

        return Long.parseLong(s);
    }
}