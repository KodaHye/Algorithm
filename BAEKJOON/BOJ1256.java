import java.io.*;
import java.math.*;
import java.util.*;

/*
사전
문자가 2개이기 때문에 사전 순서를 구할 때, 조합을 이용하면 효율적이게 문제를 풀이할 수 있음
DP로 풀어볼 것!
 */

public class BOJ1256 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        BigInteger totalCnt = getTotalCnt(N, M);
        if(totalCnt.subtract(BigInteger.valueOf(K)).compareTo(BigInteger.ZERO) < 0) {
            System.out.println(-1);
            return;
        }

        int length = N + M;
        BigInteger bigK = BigInteger.valueOf(K);

        for(int i = 0; i < length; i++) {
            int cntA = N - 1;

            BigInteger selectACnt = getTotalCnt(cntA, M);
            if(selectACnt.subtract(bigK).compareTo(BigInteger.ZERO) >= 0 && N > 0) {
                // a 선택
                sb.append("a");
                N--;
            } else {
                // z 선택
                bigK = bigK.subtract(selectACnt);
                sb.append("z");
                M--;
            }
        }
        System.out.println(sb);
    }
    static BigInteger getTotalCnt(int N, int M) {
        return getFactorial(N + M).divide(getFactorial(N)).divide(getFactorial(M));
    }

    static BigInteger getFactorial(int n) {
        BigInteger result = new BigInteger(String.valueOf(1));
        while(n > 0) {
            result = result.multiply(BigInteger.valueOf(n--));
        }
        return result;
    }
}
