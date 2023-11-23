import java.util.Arrays;

public class 비밀지도 {
    static char map[][];

    public static String[] solution(int n, int[] arr1, int[] arr2) {
        map = new char[n][n];

        decipher(n, arr1);
        decipher(n, arr2);

        String[] answer = new String[n];

        for(int i = 0; i < map.length; i++) {
            String result = "";

            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == '#') result += "#";
                else result += " ";
            }

            answer[i] = result;
        }

        for(char[] m: map) {
            System.out.println(Arrays.toString(m));
        }
        return answer;
    }

    static void decipher(int n, int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            String binary = toBinary(n, arr[i]);

            for(int j = 0; j < binary.length(); j++) {
                if(binary.charAt(j) == '1') map[i][j] = '#';
            }
        }
    }

    static String toBinary(int n, int a) {
        String result = "";

        while(a > 0) {
            result = (a % 2) + result;
            a /= 2;
        }

        int resultSize = result.length();
        for(int i = 0; i < n - resultSize; i++) {
            result = "0" + result;
        }

        return result;
    }

    public static String[] solution2(int n, int[] arr1, int[] arr2) {
        String[] result = new String[n];


        for(int i = 0; i < n; i++) {
            // 두 비트 중 하나라도 1이면 1
            result[i] = Integer.toBinaryString(arr1[i] | arr2[i]);
        }

        for(int i = 0; i < n; i++) {
            result[i] = result[i].replaceAll("1", "#");
            result[i] = result[i].replaceAll("0", " ");
        }
        return result;
    }
}