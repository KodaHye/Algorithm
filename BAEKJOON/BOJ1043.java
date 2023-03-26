import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 거짓말
 */
public class BOJ1043 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static int jP[]; // kP: 지민이가 거짓말 쟁이인 것을 아는 사람, jP: 파티에 참여하는 사람
	static boolean kP[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		kP = new boolean[N + 1];
		jP = new int[N + 1];
		
		int kPcount = Integer.parseInt(st.nextToken());
		for (int i = 0; i < kPcount; i++) {
			kP[Integer.parseInt(st.nextToken())] = true;
		}
		
		// 초기화
		for (int i = 0; i < jP.length; i++) {
			jP[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int jPnum = Integer.parseInt(st.nextToken());
			
			int start = Integer.parseInt(st.nextToken());
			
			for(int j = 0; j < jPnum - 1; j++) {
				
			}
		}
	}
}
