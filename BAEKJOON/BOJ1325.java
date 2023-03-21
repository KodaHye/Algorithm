/*
 * 효율적인 해킹
 * A가 B를 신뢰하는 경우에 A를 해킹하면 B도 해킹할 수 있음
 * 회사의 컴퓨터 신뢰 관계가 주어졌을 때, 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호 출력
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1325 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, arr[];
	static ArrayList<Integer>[] adjList;
	static boolean v[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[N + 1];
		v = new boolean[N + 1];
		arr = new int[N + 1];
		
		for(int i = 0; i < N + 1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjList[a].add(b);
		}

		for(int i = 1; i < N + 1; i++) {
			v = new boolean[N + 1];
			bfs(i);
		}
		
		int tmp = 0; 
		
		for(int i = 1; i < arr.length; i++) {
			tmp = tmp < arr[i] ? arr[i] : tmp;
		}
		
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] == tmp) System.out.print(i + " ");
		}
	}

	private static void bfs(int i) {
		Queue<Integer> queue = new LinkedList<>();
	
		queue.add(i);
		v[i] = true;		
		
		while(!queue.isEmpty()) {
			int currentIdx = queue.poll();

			for(int nextIdx: adjList[currentIdx]) {
				if(!v[nextIdx]) {
					v[nextIdx] = true;
					arr[nextIdx]++;
					queue.add(nextIdx);
				}
			}
		}
	}
}
