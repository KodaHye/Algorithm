import java.io.*;
import java.util.*;

/*
 * 친구 네트워크
 */

public class BOJ4195_2 {
	
	static HashMap<String, Integer> nameToIdx;
	static int[] parent, cnt;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(tc-- > 0) {
			int f = Integer.parseInt(br.readLine()); // 친구의 수
			
			nameToIdx = new HashMap<>();
			
			parent = new int[f * 2];
			cnt = new int[f * 2];
			
			for(int i = 0; i < f * 2; i++) {
				parent[i] = i;
				cnt[i] = 1;
			}
			
			for(int i = 0; i < f; i++) {
				st = new StringTokenizer(br.readLine());
					
				String a = st.nextToken();
				String b = st.nextToken();
				
				if(!nameToIdx.containsKey(a)) nameToIdx.put(a, nameToIdx.size());
				if(!nameToIdx.containsKey(b)) nameToIdx.put(b, nameToIdx.size());
				
				int pa = find(nameToIdx.get(a));
				int pb = find(nameToIdx.get(b));
				
				if(pa != pb) {
					cnt[pa] += cnt[pb];
					union(pa, pb);
				}
				
				sb.append(cnt[pa]).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static void union(int a, int b) {
		if(a != b) parent[b] = a;
	}
	
	static int find(int a) {
		return parent[a] = a == parent[a] ? a : find(parent[a]);
	}
}
