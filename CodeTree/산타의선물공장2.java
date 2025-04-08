import java.io.*;
import java.util.*;

public class 산타의선물공장2 {
	
	static class Item {
		int prev, next;
	}
	
	static class Belt {
		int head, tail, cnt;
		
		public Belt() {}
		public void update(int h, int t, int c) {
			this.head = h;
			this.tail = t;
			this.cnt = c;
		}
	}
	
	static Item[] items;
	static ArrayList<ArrayList<Integer>> group;
	static Belt[] belt; // group의 idx 저장
	static ArrayList<Integer>[] beltList;
	static int[] beltSize;
	static int[] arrangeIdx;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/산타의선물공장2.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int q = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(q-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int o = Integer.parseInt(st.nextToken());
			if(o == 100) step1(st);
			if(o == 200) sb.append(step2(st)).append("\n");
			if(o == 300) sb.append(step3(st)).append("\n");
			if(o == 400) sb.append(step4(st)).append("\n");
			if(o == 500) sb.append(step5(st)).append("\n");
			if(o == 600) sb.append(step6(st)).append("\n");
		}
		
		System.out.println(sb);
	}
 
	static int step6(StringTokenizer st) {
		int bNum = Integer.parseInt(st.nextToken());
		
		int a = belt[bNum].head != 0 ? belt[bNum].head : -1;
		int b = belt[bNum].tail != 0 ? belt[bNum].tail : -1;
		int c = belt[bNum].cnt;
		
		return a + 2 * b + 3 * c;
	}
	
	static int step5(StringTokenizer st) {

		int pNum = Integer.parseInt(st.nextToken());
		
		int a = items[pNum].prev != 0 ? items[pNum].prev : -1;
		int b = items[pNum].next != 0 ? items[pNum].next : -1;
		
		return a + 2 * b;
	}
	
	static int step4(StringTokenizer st) {
		int mSrc = Integer.parseInt(st.nextToken());
		int mDst = Integer.parseInt(st.nextToken());
		
		int cnt = belt[mSrc].cnt;
		
		ArrayList<Integer> boxIdList = new ArrayList<Integer>();
		
		for(int i = 0; i < cnt / 2; i++) {
			boxIdList.add(removeHead(mSrc));
		}
		
		for(int i = boxIdList.size() - 1; i >= 0; i--) {
			pushHead(mDst, boxIdList.get(i));
		}
		
		return belt[mDst].cnt;
	}
	
	// 앞 물건 교체
	static int step3(StringTokenizer st) {
		int mSrc = Integer.parseInt(st.nextToken());
		int mDst = Integer.parseInt(st.nextToken());
		
		int srcHead = removeHead(mSrc);
		int mDstHead = removeHead(mDst);
		
		pushHead(mDst, srcHead);
		pushHead(mSrc, mDstHead);

		return belt[mDst].cnt;
	}
	
	static void pushHead(int bIdx, int headIdx) {
		
		// 불가능한 경우 X
		if(headIdx == 0) return;
		
		// 비어있었다면
		if(belt[bIdx].cnt == 0) {
			belt[bIdx].head = belt[bIdx].tail = headIdx;
		} else {
			int originHead = belt[bIdx].head;
			
			belt[bIdx].head = headIdx;
			
			items[headIdx].next = originHead;
			items[originHead].prev = headIdx;
		}
		
		belt[bIdx].cnt++;
	}
	
	// 해당 벨트의 head 제거
	static int removeHead(int idx) {
		if(belt[idx].cnt == 0) return 0;
		
		// 노드가 한 개라면
		int headId = belt[idx].head;
		
		if(belt[idx].cnt == 1) {
			belt[idx].head = belt[idx].tail = 0;
		} else {
			int nextHeadId = items[headId].next;
			items[headId].next = items[nextHeadId].prev = 0;
			belt[idx].head = nextHeadId;
		}
		
		belt[idx].cnt--;
		
		return headId;
	}
	
	// 물건 모두 옮기기
	static int step2(StringTokenizer st) {
		
		int mSrc = Integer.parseInt(st.nextToken());
		int mDst = Integer.parseInt(st.nextToken());
		
		if(belt[mSrc].cnt == 0) return belt[mDst].cnt;
		
		if(belt[mDst].cnt == 0) {
			belt[mDst].head = belt[mSrc].head;
			belt[mDst].tail = belt[mSrc].tail;
		} else {
			int tmp = belt[mDst].head;
			
			belt[mDst].head = belt[mSrc].head;
			
			// mSrc의 tail과 기존 mDst의 head 연결
			int srcTail = belt[mSrc].tail;
			items[srcTail].next = tmp;
			items[tmp].prev = srcTail;
		}
		
		// head, tail을 비워줌
		belt[mSrc].head = belt[mSrc].tail = 0;
		
		// 선물 상자 수 갱신하기
		belt[mDst].cnt += belt[mSrc].cnt;
		belt[mSrc].cnt = 0;
		
		return belt[mDst].cnt;
	}
	
	// 공장 설립
	// n개의 벨트와 m개의 선물로 이루어진 공장
	static void step1(StringTokenizer st) {
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		beltList = new ArrayList[n + 1];
		
		for(int i = 0; i < beltList.length; i++) beltList[i] = new ArrayList<Integer>();
		
		for(int i = 1; i < m + 1; i++) {
			int bIdx = Integer.parseInt(st.nextToken()); // 벨트 인덱스
			beltList[bIdx].add(i);
		}
		
		belt = new Belt[n + 1];
		items = new Item[m + 1];
		
		for(int i = 0; i < n + 1; i++) belt[i] = new Belt();
		for(int i = 0; i < items.length; i++) items[i] = new Item();

		// 초기 벨트의 head, tail, nxt, prev 값 설정
		for(int i = 0; i < beltList.length; i++) {
			if(beltList[i].size() == 0) continue;
			
			int lastIdx = beltList[i].size() - 1;
			
			belt[i].update(beltList[i].get(0), beltList[i].get(lastIdx), beltList[i].size());
			
			// next, prev 설정
			for(int k = 0; k < beltList[i].size() - 1; k++) {
				items[beltList[i].get(k)].next = beltList[i].get(k + 1);
				items[beltList[i].get(k + 1)].prev = beltList[i].get(k);
			}
		}
		
	}
}
