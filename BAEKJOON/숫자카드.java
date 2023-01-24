import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class 숫자카드 {
	// 상근: N개의 카드를 가지고 있음
	// 정수 M개가 주어졌을 때, 상근이가 가지고 있는 카드인지 확인
	// 입력: N(상근이가 가지고 있는 카드의 개수)
	// 		숫자 카드에 적혀있는 정수
	//		M
	//		상근이가 가지고 있는 숫자 카드인지 아닌지 구해야 되는 M개의 정수
	// 출력: 상근이한테 있으면 1, 아니면 0
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		HashSet<Integer> set1 = new HashSet<>();

		int N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			set1.add(Integer.parseInt(st.nextToken()));
		}

		int M = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			if(set1.contains(Integer.parseInt(st.nextToken()))) sb.append("1 ");
			else sb.append("0 ");
		}
		
		System.out.println(sb);
		
	}
}