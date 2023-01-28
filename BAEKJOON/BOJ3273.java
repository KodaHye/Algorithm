import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ3273 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> arr = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(arr);
		
		int x = Integer.parseInt(br.readLine());
		int count = 0;
		int left = 0, right = N - 1;
		
		while(left < right) {
			int sum = arr.get(left) + arr.get(right);
			if(sum == x) {
				count++;
				left++;
				right--;
			} else if (sum > x) {
				right--;
			} else {
				left++;
			}
		}
		
		System.out.println(count);
	}

}
