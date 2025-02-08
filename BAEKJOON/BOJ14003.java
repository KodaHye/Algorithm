import java.io.*;
import java.util.*;

/*
 * 가장 긴 증가하는 부분수열 5
 * 
 * 오답 ------------------------
 * ArrayList<Integer>[]를 만들고 자리 마다 올 수 있는 숫자를 넣고
 * 맨 뒤에서부터 증가 순서가 될 수 있게 골랐음
 * 원인: LIS만들면서 순서가 뒤죽박죽 되기 때문에 이렇게 하면 안됨
 * 
 * 정답 ------------------------
 * memo배열과 arr 배열 사용
 * memo배열의 arr[i]가 들어갈 idx 저장함
 * 그리고 memo 배열을 거꾸로 확인하면서 answer을 채워줌
 * 여기에서 또 memo를 앞에서부터 만들어주면 순서가 보장되지 않음
 * 뒤에서부터 확인하면서 LIS 구해줘야 함!!!
 */

public class BOJ14003 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/BOJ14003.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N], lis = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		lis[0] = arr[0];
		
		int length = 1;
		
		int[] memo = new int[N];
		
		for(int i = 1; i < arr.length; i++) {
			int idx = Arrays.binarySearch(lis, 0, length, arr[i]);
			
			if(idx < 0) idx = ~idx;
			
			memo[i] = idx;
			lis[idx] = arr[i];
			
			if(idx == length) {
				length++;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(length).append("\n");
		
		int idx = length - 1;
		
		int[] answer = new int[length];
		
		for(int i = N - 1; i >= 0; i--) {
			if(memo[i] != idx) continue;
			answer[idx--] = arr[i];
		}
		
		for(int a: answer) sb.append(a).append(" ");
		System.out.println(sb);
	}
}
