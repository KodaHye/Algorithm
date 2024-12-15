import java.io.*;
import java.util.*;

public class BOJ7785 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		TreeSet<String> set=  new TreeSet<>(Collections.reverseOrder());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			String name = st.nextToken();
			String type = st.nextToken();
			
			if(set.contains(name) && type.equals("leave")) {
				set.remove(name);
			} 
			if(type.equals("enter")) set.add(name);
		}
		
		StringBuilder sb = new StringBuilder();
		for(String s: set) sb.append(s).append("\n");
		
		System.out.println(sb);
	}
}
