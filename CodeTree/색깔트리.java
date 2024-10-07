import java.io.*;
import java.util.*;

/*
 * 색깔트리
 */

public class 색깔트리 {
	static class Node {
		int p, color, depth, type;
		public Node(int p, int color, int depth, int type) {
			this.p = p;
			this.color = color;
			this.depth = depth;
			this.type = type;
		}
	}
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int Q;
	static Node nodes[];
	static ArrayList<Integer> adj[];
	
	static void solution() throws Exception {
		for(int tc = 0; tc < Q; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int o = Integer.parseInt(st.nextToken());
			if(o == 100) addNode(st);
			if(o == 200) changeColor(st);
			if(o == 300) searchColor(st);
			if(o == 400) searchScore();
		}
		System.out.println(sb);
	}
	
	static void searchScore() {
		int result = 0;
		
		for(int i = 1; i < nodes.length; i++) {
			if(nodes[i] == null) continue;
			int binaryCnt = Integer.bitCount(nodes[i].type);
			result += binaryCnt * binaryCnt;
		}
		
		sb.append(result + "\n");
	}
	
	static void searchColor(StringTokenizer st) {
		int mId = Integer.parseInt(st.nextToken());
		sb.append(nodes[mId].color + "\n");
	}
	
	static void changeColor(StringTokenizer st) {
		int mId = Integer.parseInt(st.nextToken());
		int color = Integer.parseInt(st.nextToken());
		
		changeColor(mId, color);
		
		int pId = nodes[mId].p;
		
		updateNodeType(pId);
	}
	
	static void updateNodeType(int pId) {
		while(pId != 0) {
			updateNode(pId);
			pId = nodes[pId].p;
		}
	}
	
	static void updateNode(int pId) {
		
		nodes[pId].type = (1 << nodes[pId].color - 1);
		
		for(int next: adj[pId]) {
			nodes[pId].type |= nodes[next].type;
		}
	}
	
	static void changeColor(int id, int color) {
		nodes[id].color = color;
		nodes[id].type = 1 << (color - 1);
		
		for(int next: adj[id]) {
			changeColor(next, color);
		}
	}
	
	static void addNode(StringTokenizer st) {
		int mId = Integer.parseInt(st.nextToken());
		int pId = Integer.parseInt(st.nextToken());
		int color = Integer.parseInt(st.nextToken());
		int maxDepth = Integer.parseInt(st.nextToken());
		
		int type = 1 << (color - 1);

		int depth = 0;
		if(pId == -1) {
			pId = 0;
			depth = maxDepth;
		} else {
			depth = Math.min(nodes[pId].depth - 1, maxDepth);
		}
		
		if(pId != 0 && nodes[pId].depth == 1) return;
		adj[pId].add(mId);
		
		nodes[mId] = new Node(pId, color, depth, type);
		
		if(pId != 0) {
			int pType = 1 << (nodes[pId].color - 1);
			for(int next: adj[pId]) {
				pType |= nodes[next].type;
			}
			
			nodes[pId].type = pType;
			updateNodeType(nodes[pId].p);
		}
	}
	
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
	}
	
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/색깔트리.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		Q = Integer.parseInt(br.readLine());
		adj = new ArrayList[100_001];
		nodes = new Node[100_001];
		
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Integer>();
	}
}
