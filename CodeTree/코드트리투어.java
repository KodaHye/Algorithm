import java.util.*;
import java.io.*;

/*
 * 코드트리 투어
 * 자료 구조 잘 생각해서 문제 풀것 !!
 */

public class 코드트리투어 {
	static class Node implements Comparable<Node> {
		int e, c;

		public Node(int e, int c) {
			this.e = e;
			this.c = c;
		}

		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.c, o.c);
		}
	}
	
	static class TravelInfo implements Comparable<TravelInfo> {
		int id, 매출, 도착지, 거리;
		public TravelInfo(int id, int 매출, int 도착지, int 거리) {
			this.id = id;
			this.매출 = 매출;
			this.도착지 = 도착지;
			this.거리 = 거리;
		}
		
		@Override
		public int compareTo(TravelInfo o) {
			if(this.매출 - this.거리 == o.매출 - o.거리) 
				return Integer.compare(this.id, o.id);
			return -1 * Integer.compare(this.매출 - this.거리, o.매출 - o.거리);
		}
	}

	static StringBuilder sb = new StringBuilder();
	static int dis[];
	static PriorityQueue<Node> queue = new PriorityQueue<Node>();
	static ArrayList<Node> adj[];
	static boolean isCancel[], isMade[];
	static PriorityQueue<TravelInfo> travelPackage;
    public static void main(String[] args) throws Exception {
        initInput();
        System.out.println(sb);
    }
    
    private static void dijkstra(int start) {
		Arrays.fill(dis, Integer.MAX_VALUE);
	
		dis[start] = 0;
		queue.add(new Node(start, 0));
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			for(Node next: adj[current.e]) {
				if(dis[next.e] > dis[current.e] + next.c) {
					dis[next.e] = dis[current.e] + next.c;
					
					queue.add(new Node(next.e, dis[next.e]));
				}
			}
		}
    }
	private static void step5(StringTokenizer st) {
		int start = Integer.parseInt(st.nextToken());
		dijkstra(start);
		List<TravelInfo> packages = new LinkedList<TravelInfo>();
		while(!travelPackage.isEmpty()) {
			packages.add(travelPackage.poll());
		}
		
		for(TravelInfo t: packages) {
			travelPackage.add(new TravelInfo(t.id, t.매출, t.도착지, dis[t.도착지]));
		}
	}

	private static void step4() {
		int id = -1;
		
		while(!travelPackage.isEmpty()) {
			TravelInfo p = travelPackage.peek();
			if(p.매출 - p.거리 < 0) break;
			
			travelPackage.poll();
			if(!isCancel[p.id]) {
				id = p.id;
				break;
			}
		}
		
		sb.append(id + "\n");
	}

	private static void step3(StringTokenizer st) {
		int id = Integer.parseInt(st.nextToken());
		if(isMade[id]) isCancel[id] = true;
	}

	private static void step2(StringTokenizer st) {
		int id = Integer.parseInt(st.nextToken());
		int revenue = Integer.parseInt(st.nextToken()); // 매출
		int dest = Integer.parseInt(st.nextToken()); // 도착지
		isMade[id] = true;
		travelPackage.add(new TravelInfo(id, revenue, dest, dis[dest]));
	}

	private static void step1(StringTokenizer st) {
		
		int n = Integer.parseInt(st.nextToken()); 
		int m = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[n];
		dis = new int[n];
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Node>();
		for(int i = 0; i < m; i++) {
			int v = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[v].add(new Node(u, w));
			adj[u].add(new Node(v, w));
		}
		dijkstra(0);
	}

    public static void initInput() throws Exception {
    	
    	System.setIn(new FileInputStream("./input/코드트리투어.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        isCancel = new boolean[30_001];
        isMade = new boolean[30_001];
        travelPackage = new PriorityQueue<TravelInfo>();

        int Q = Integer.parseInt(br.readLine());

        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            int order = Integer.parseInt(st.nextToken());
            if(order == 100) step1(st);
            if(order == 200) step2(st); // 여행 상품 생성
            if(order == 300) step3(st); // 여행 상품 취소
            if(order == 400) step4();   // 취적의 여행 상품 판매
            if(order == 500) step5(st); // 출발지 변경
        }
    }


}