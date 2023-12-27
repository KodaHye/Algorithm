class 저주의숫자3 {
    public static int solution(int n) {

        int arr[] = new int[101];

        arr[1] = 1;
        arr[2] = 2;
        for(int i = 3; i < arr.length; i++) {
            arr[i] = arr[i - 1] + 1;

            while(arr[i] % 3 == 0 || String.valueOf(arr[i]).contains("3")) arr[i]++;
        }

        return arr[n];
    }
}