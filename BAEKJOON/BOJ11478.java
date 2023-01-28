

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ11478 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashSet<String> set = new HashSet<>();
		
		String input = br.readLine();
		for(int i = 0; i < input.length(); i++) {
			for (int j = i; j < input.length(); j++) {
				String tmp = input.substring(i, j + 1);
				set.add(tmp);
			}
		}
		System.out.println(set.size());
	}
}
