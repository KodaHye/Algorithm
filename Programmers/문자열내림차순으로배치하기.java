import java.util.*;

class 문자열내림차순으로배치하기 {
    static public String solution(String s) {

        char arr[] = s.toCharArray();
        Arrays.sort(arr);

        return new StringBuilder(String.valueOf(arr)).reverse().toString();
    }
}