import java.io.*;

public class BOJ1342 {
	
	static String s;
	static int[] cnt;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		s = br.readLine();
		
		cnt = new int['z' - 'a' + 1];
		
		// 각 문자마다 개수
		for(int i = 0; i < s.length(); i++) {
			cnt[s.charAt(i) - 'a']++;
		}
		
		// 행운 문자열 개수 세기
		dfs(0, -1);
		
		System.out.println(answer);
	}
	
	static void dfs(int depth, int prev) {
		if(depth == s.length()) {
			answer++;
			return;
		}
		
		for(int i = 0; i < 26; i++) {
			if(cnt[i] == 0 || i == prev) continue;
			
			cnt[i]--;
			dfs(depth + 1, i);
			cnt[i]++;
		}
	}
}
