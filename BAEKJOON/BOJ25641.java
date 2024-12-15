import java.io.*;

public class BOJ25641 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		int cntS = 0, cntT = 0, idx = str.length() - 1;
		
		for(int i = str.length() - 1; i >= 0; i--) {
			if(str.charAt(i) == 's') cntS++;
			else cntT++;
			
			if(cntS == cntT) idx = i;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = idx; i < str.length(); i++) sb.append(str.charAt(i));
		System.out.println(sb);
	}
}
