import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5086 {
	// 두 수가 주어졌을 때 3가지 관계 중 어떤 관계인지
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(a == 0 && b== 0) break;
			
			if(b % a == 0) sb.append("factor" + "\n");
			else if(a % b == 0) sb.append("multiple" + "\n");
			else sb.append("neither" + "\n");
		}
		System.out.println(sb);
	}

}
