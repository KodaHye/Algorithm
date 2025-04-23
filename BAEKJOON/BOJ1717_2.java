import java.io.*;
import java.util.*;

/*
 * 집합의 표현
 * - 잊으면 안되는 거!
 *   * union할 때, a = find(a), b = find(b)
 *   * find할 때, 경로압축 
 */

public class BOJ1717_2 {
	
	static int[] p;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		p = new int[n + 1];
		for(int i = 0; i < p.length; i++) p[i] = i;
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int o = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(o == 0) union(a, b);
			if(o == 1) sb.append(find(a) == find(b) ? "YES" : "NO").append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) p[b] = a;
	}
	
	static int find(int a) {
		return p[a] = a == p[a] ? a : find(p[a]);
	}
}
