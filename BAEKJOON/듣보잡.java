import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 듣보잡 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int count = 0;
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		TreeSet<String> set1 = new TreeSet<>();
		TreeSet<String> set2 = new TreeSet<>();
		
		for(int i = 0; i < N; i++) {
			set1.add(br.readLine());
		}
		for(int i = 0; i < M; i++) {
			set2.add(br.readLine());
		}
		
		
		for(String str :set1) {
			if(set2.contains(str)) {
				count++;
				sb.append(str + "\n");
			}
		}
		System.out.println(count + "\n" + sb);
	}

}
