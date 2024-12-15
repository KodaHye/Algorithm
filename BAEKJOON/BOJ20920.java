import java.io.*;
import java.util.*;

public class BOJ20920 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		TreeMap<String, Integer> treeMap = new TreeMap<>();

		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			
			if(s.length() < M) continue;
			treeMap.put(s, treeMap.getOrDefault(s, 1) + 1);
		}

		ArrayList<String> list = new ArrayList<>(treeMap.keySet());

		Collections.sort(list, (o1, o2) -> {
            int cnt1 = treeMap.get(o1);
            int cnt2 = treeMap.get(o2);

            if(cnt1 != cnt2)
                return -1 * Integer.compare(cnt1, cnt2);
            if(o1.length() != o2.length())
                return -1 * Integer.compare(o1.length(), o2.length());
            return o1.compareTo(o2);
		});

		StringBuilder sb = new StringBuilder();

		for(String s: list) sb.append(s).append("\n");

		System.out.print(sb);
	}
}
