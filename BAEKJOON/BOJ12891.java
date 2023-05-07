import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * DNA 비밀번호
 */
public class BOJ12891 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int S, P, count;
	static String input;
	static int min[], dna[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		dna = new int[4]; // A, C, G, T 저장
		min = new int[4];
		
		input = br.readLine();
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < 4; i++) {
			min[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < P; i++) {
			addDna(i); // i번째 DNA 더하기
		}
		
		if(check(dna)) count++;

		for(int i = 0; i < S - P; i++) {
			subDna(i); // i번째 DNA 빼기
			addDna(P + i); // P + i + 1번째 DNA 더하기
			
			if(check(dna)) count++;
		}
		
		System.out.println(count);
	}

	private static void subDna(int i) {
		if(input.charAt(i) == 'A') dna[0]--;
		if(input.charAt(i) == 'C') dna[1]--;
		if(input.charAt(i) == 'G') dna[2]--;
		if(input.charAt(i) == 'T') dna[3]--;
	}
	
	private static void addDna(int i) {
		if(input.charAt(i) == 'A') dna[0]++;
		if(input.charAt(i) == 'C') dna[1]++;
		if(input.charAt(i) == 'G') dna[2]++;
		if(input.charAt(i) == 'T') dna[3]++;
	}

	private static boolean check(int[] dna) {
		for(int i = 0; i < 4; i++) {
			if(dna[i] < min[i]) return false;
		}
		
		return true;
	}
}
