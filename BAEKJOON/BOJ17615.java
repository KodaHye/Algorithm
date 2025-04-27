import java.io.*;
import java.util.*;

/*
 * 볼 모으기
 */

public class BOJ17615 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		String s = br.readLine();
		
		HashSet<Integer> R = new HashSet<>();
		HashSet<Integer> B = new HashSet<>();
		
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == 'R') R.add(i);
			if(s.charAt(i) == 'B') B.add(i);
		}
		
		HashSet<Integer> Bcopy = (HashSet<Integer>) B.clone();
		
		// 파 - 빨 ---------------------------
		// 빨간색을 옮기는 경우
		int tmp1 = 0;
		for(int i = 0; i < s.length(); i++) {
			
			if(s.charAt(i) == 'R') tmp1++;
			else Bcopy.remove(i);

			if(Bcopy.size() == 0) break;
		}
		
		int tmp2 = 0;
		
		// 파란색을 옮기는 경우
		Bcopy = (HashSet<Integer>) B.clone();
		boolean hasRed = false;
		
		for(int i = 0; i < s.length(); i++) {
			
			if(s.charAt(i) == 'R') hasRed = true;
			else {
				Bcopy.remove(i);
				if(hasRed) tmp2++;
			}
			
			if(Bcopy.size() == 0) break;
		}
		
		// 빨 - 파 ---------------------------
		// 빨간색을 옮기는 경우
		HashSet<Integer> Rcopy = (HashSet<Integer>) R.clone();
		
		int tmp3 = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == 'B') tmp3++;
			else Rcopy.remove(i);
			
			if(Rcopy.size() == 0) break;
		}
		
		int tmp4 = 0;
		
		Rcopy = (HashSet<Integer>) R.clone();
		boolean hasBlue = false;
		
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == 'B') hasBlue = true;
			
			else {
				Rcopy.remove(i);
				if(hasBlue) tmp4++;
			}
			
			if(Rcopy.size() == 0) break;
		}
		
		System.out.println(Math.min(Math.min(tmp1, tmp2), Math.min(tmp3, tmp4)));
	}
}
