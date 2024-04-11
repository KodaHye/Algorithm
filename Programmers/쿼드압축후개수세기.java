class 쿼드압축후개수세기 {
    public int[] solution(int[][] arr) {
        int[] answer = new int[2];

        for(int r = 0; r < arr.length; r++) {
            for(int c = 0; c < arr[0].length; c++) answer[arr[r][c]]++;
        }

        int size = arr.length;
        int tmp = size;

        while(tmp != 0) {
            for(int r = 0; r < size; r += tmp) {
                for(int c = 0; c < size; c += tmp) {
                    if(checkQuadTree(arr, r, c, tmp) == -1 || arr[r][c] == -1) continue;

                    int num = arr[r][c];
                    answer[num] -= (tmp * tmp - 1);

                    for(int rr = r; rr < r + tmp; rr++) {
                        for(int cc = c; cc < c + tmp; cc++) {
                            arr[rr][cc] = -1;
                        }
                    }
                }
            }
            tmp /= 2;
        }
        return answer;
    }

    public static int checkQuadTree(int[][] arr, int r, int c, int tmp) {
        int num = arr[r][c];
        boolean flag = true;

        L: for(int rr = r; rr < r + tmp; rr++) {
            for(int cc = c; cc < c + tmp; cc++) {
                if(arr[rr][cc] != num) {
                    flag = false;
                    break L;
                }
            }
        }

        if(!flag) return -1;
        return num;
    }
}