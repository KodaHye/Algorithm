import java.util.Arrays;

class 정수내림차순으로배치하기 {
    public static long solution(long n) {
        String str = String.valueOf(n);
        StringBuilder sb = new StringBuilder();

        int arr[] = new int[str.length()];

        for(int i = 0; i < arr.length; i++) {
            arr[i] = str.charAt(i) - '0';
        }

        Arrays.sort(arr);

        for(int i = arr.length - 1; i >= 0; i--) {
            sb.append(arr[i]);
        }

        return Long.parseLong(sb.toString());
    }
}