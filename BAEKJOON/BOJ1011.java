import java.io.*;
import java.util.*;

/*
 * Fly me to the Alpha Centauri
 */

public class BOJ1011 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int length = Math.abs(Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken()));
			int cnt = 0;
			
			int tmp = (int) Math.pow(length, 1.0 / 2);
			if(tmp == 0) tmp = 1;
			
			cnt = tmp + (tmp - 1);
			
			length -= tmp * tmp;
			
			if(length == 0) cnt += 0;
			else if(length <= tmp) cnt += 1;
			else cnt += 2;
			
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb);
	}
}
