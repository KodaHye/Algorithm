import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ14425 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int count = 0;
		
		HashSet<String> set1 = new HashSet<>();
		
		for(int i = 0; i < N; i++) {
			set1.add(br.readLine());
		}
		for(int i = 0; i < M; i++) {
			if(set1.contains(br.readLine())) count++;
		}
		System.out.println(count);
	}

}
