import java.io.*;
import java.util.*;

/*
 * 여행가자
 */

public class BOJ1976_2 {
	
	static int[] p;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		p = new int[N + 1];
		for(int i = 0; i < N; i++) p[i] = i;
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int k = 1; k < N + 1; k++) {
				int status = Integer.parseInt(st.nextToken());
				
				if(status == 0) continue;
				union(i, k);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		boolean flag= true;
		int parent = p[Integer.parseInt(st.nextToken())];
		
		for(int i = 1; i < M; i++) {
			
			// p[i]가 아니라 find(i)를 해야 됨!
			if(parent != find(Integer.parseInt(st.nextToken()))) {
				flag = false;
				break;
			}
		}
		
		System.out.println(flag ? "YES" : "NO");
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
