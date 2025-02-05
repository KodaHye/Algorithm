import java.io.*;
import java.util.*;

/*
 * 중복 제거
 */

public class BOJ13701 {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] bitmask = new int[(33554432 + 1) / 30 + 1];

		StringBuilder sb = new StringBuilder();
		
		while(st.hasMoreTokens()) {
			int num = Integer.parseInt(st.nextToken());
			
			int idx = num / 30;
			int mod = num % 30;
			
			if((bitmask[idx] & (1 << mod)) > 0) continue;
			bitmask[idx] |= (1 << mod);
			sb.append(num).append(" ");
		}
		
		System.out.println(sb);
	}
}
