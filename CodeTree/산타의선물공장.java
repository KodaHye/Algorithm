import java.io.*;
import java.util.*;

/*
 * beltOnBoxInfo를 비트마스킹으로 하려고 했는데
 * 물건 id 값의 범위가 너무 크기 때문에 HashSet을 통해 관리하는게 나음
 */

public class 산타의선물공장 {
	static int N, M;
	
	static class Belt {
		int head, tail;
		boolean isNotWork;
		HashSet<Integer> beltOnBoxInfo;
		
		public Belt(int head) {
			this.head = head;
		}
	}
	
	static class Gift {
		int w, prev, next;
		boolean isOut;
		
		public Gift() {}

		public void setNext(int next) {
			this.next = next;
		}
		
		public void setPrev(int prev) {
			this.prev = prev;
		}

		public void setWeight(int w) {
			this.w = w;
		}
	}
	
	
	static LinkedHashMap<Integer, Gift> gifts;
	static Belt[] belts;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int q = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(q-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int order = Integer.parseInt(st.nextToken());
			
			if(order == 100) {
				step1(st);
				continue;
			}
			if(order == 200) sb.append(step2(st));
			if(order == 300) sb.append(step3(st));
			if(order == 400) sb.append(step4(st));
			if(order == 500) sb.append(step5(st));
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int step5(StringTokenizer st) {
		int bNum = Integer.parseInt(st.nextToken());
		
		if(belts[bNum].isNotWork) return -1;
		
		belts[bNum].isNotWork = true;

		int next = belts[bNum].head; // head는 어떤 벨트의 끝에 다시 붙음
		
		for(int i = 1; i < M; i++) {
			int idx = bNum + i;
			if(idx > M) idx = (idx % M);
			
			if(belts[idx].isNotWork) continue;
			
			int prev = belts[idx].tail;
			
			gifts.get(prev).next = next;
			gifts.get(next).prev = prev;
			
			belts[idx].tail = belts[bNum].tail;
			
			belts[idx].beltOnBoxInfo.addAll(belts[bNum].beltOnBoxInfo);
			belts[bNum].beltOnBoxInfo.clear();
			
			break;
		}
		
		return bNum;
	}
	
	static int getBoxIdx(int fId) {
		
		int boxIdx = 0;
		
		for(int i = 1; i < belts.length; i++) {
			if(belts[i].isNotWork) continue;
			
			if(belts[i].beltOnBoxInfo.contains(fId)) {
				boxIdx = i;
				break;
			}
		}
		return boxIdx;
	}
	
	static int step4(StringTokenizer st) {
		int fId = Integer.parseInt(st.nextToken());
		
		if(!gifts.containsKey(fId) || gifts.get(fId).isOut) return -1;

		// 벨트 인덱스 어떻게 구함 ????????????????
		int bIdx = getBoxIdx(fId);
		
		// 해당 상자 위에 있는 상자들을 모두 앞으로 가져옴
		
		int currentHead = belts[bIdx].head;
		gifts.get(belts[bIdx].tail).next = currentHead;
		gifts.get(currentHead).prev = belts[bIdx].tail;
		
		// 직전노드: 이걸 tail로 만들어줘야 됨
		int prev = gifts.get(fId).prev;
		
		belts[bIdx].head = fId;
		belts[bIdx].tail = prev;
		
		gifts.get(fId).prev = 0;
		gifts.get(prev).next = 0;
		
		return bIdx;
	}
	
	// 물건제거
	static int step3(StringTokenizer st) {
		int rId = Integer.parseInt(st.nextToken());
		
		if(!gifts.containsKey(rId) || gifts.get(rId).isOut) return -1;
		
		int bIdx = getBoxIdx(rId);
		
		// 해당 벨트에서 상자를 제거
		gifts.get(rId).isOut = true;
		
		// head이자 tail이면 tail 정보도 없애기
		if(belts[bIdx].tail == rId && belts[bIdx].head == rId) {
			belts[bIdx].tail = 0;
			belts[bIdx].head = 0;
		} else {

			// bIdx가 tail일 때
			if(belts[bIdx].tail == rId) {
				int prev = gifts.get(rId).prev;
				belts[bIdx].tail = prev;
				gifts.get(prev).next = 0;
			} 
			
			// bIdx가 head일 때
			else if(belts[bIdx].head == rId) {
				int next = gifts.get(rId).next;
				
				// next가 head가 됨
				belts[bIdx].head = next;
				gifts.get(next).prev = 0;
			}
			
			// 중간에 있는 어떤 거라면
			else {
				int next = gifts.get(rId).next;
				int prev = gifts.get(rId).prev;

				gifts.get(prev).next = next;
				gifts.get(next).prev = prev;
			}
		}
		
		return rId;
	}
	
	// wMax가 주어짐
	// 1 ~ m번 벨트
	// - 맨 앞에 있는 선물의 무게가 wMax 이하라면 하차
	// - 아니라면 맨 뒤로 보내기
	static long step2(StringTokenizer st) {
		int wMax =Integer.parseInt(st.nextToken());
		
		long result = 0;
		
		// 맨 앞에 있는 선물 중 해당 선물의 무게가 wMax이하라면 하차
		// 그렇지 않다면 해당 선물을 벨트 맨 뒤로 보냄
		for(Belt b: belts) {
			
			if(b == null || b.isNotWork) continue;
			
			int gIdx = b.head; // 맨 앞에 있는 선물 idx
			
			if(gifts.get(gIdx).w <= wMax) {
				
				result += gifts.get(gIdx).w;
				gifts.get(gIdx).isOut = true;
				
				// 물건 내보내기
				// head이자 tail이면 tail 정보도 없애기
				// 레일에 gIdx 한 개 밖에 없는 경우
				if(b.tail == gIdx) {
					
					// 비어있는 벨트 상태로
					b.tail = 0;
					b.head = 0;
				}
				
				// 다른 것들도 있는 경우
				else {
					int next = gifts.get(gIdx).next;
					b.head = next;
					gifts.get(next).prev = 0;
				}
			} else {
				
				int tail = b.tail;

				// gIdx가 바로 tail이라면
				if(tail == gIdx) continue;
				
				int next = gifts.get(gIdx).next;
				
				// 그게 아니라면 gIdx를 맨 마지막으로 보내기
				gifts.get(tail).next = gIdx;

				gifts.get(gIdx).prev = tail;
				gifts.get(gIdx).next = 0;
				
				b.tail = gIdx; // tail값 수정하기
				
				b.head = next; // head값 수정하기
				gifts.get(next).prev = 0;
			}
		}
		
		return result;
	}
	
	static void step1(StringTokenizer st) {
		N = Integer.parseInt(st.nextToken()); // 선물의 개수
		M = Integer.parseInt(st.nextToken()); // 벨트의 개수
		
		belts = new Belt[M + 1];
		
		gifts = new LinkedHashMap<>();
		
		int[] tmpIdx = new int[N];
		
		int idx = 0;
		
		// 1번 벨트부터 m번 벨트까지 올려주기
		for(int i = 1; i < M + 1; i++) {
			
			HashSet<Integer> beltOnBoxInfo = new HashSet<>();

			int prev = Integer.parseInt(st.nextToken());
			gifts.put(prev, new Gift());
			
			// 각 벨트에 물건이 하나씩 있을 때 처리
			beltOnBoxInfo.add(prev);
			
			belts[i] = new Belt(prev); // head 설정해주기
			
			tmpIdx[idx++] = prev;
			

			for(int k = 1; k < (N / M); k++) {
				
				int next = Integer.parseInt(st.nextToken());
				gifts.put(next, new Gift());

				beltOnBoxInfo.add(next);
				
				if(k == (N / M) - 1) belts[i].tail = next;
				
				tmpIdx[idx++] = next;
				
				gifts.get(prev).setNext(next);
				gifts.get(next).setPrev(prev);
				
				prev = next;
			}
			
			if(belts[i].tail == 0) belts[i].tail = belts[i].head;
			belts[i].beltOnBoxInfo = beltOnBoxInfo;
		}
		for(int k = 0; k < N; k++) {
			int w = Integer.parseInt(st.nextToken());
			gifts.get(tmpIdx[k]).setWeight(w);
		}
	}
}

