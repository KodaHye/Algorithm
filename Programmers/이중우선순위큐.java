import java.util.*;

class 이중우선순위큐 {
    static public int[] solution(String[] operations) {
        StringTokenizer st;
        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();

        for(int i = 0; i < operations.length; i++) {
            st = new StringTokenizer(operations[i]);

            char order = st.nextToken().charAt(0);
            int num = Integer.parseInt(st.nextToken());

            if(order == 'I') {
                if(treeMap.containsKey(num)) treeMap.put(num, treeMap.get(num) + 1);
                else treeMap.put(num, 1);
            } else {
                if(treeMap.size() == 0) continue;

                if(num == -1) treeMap.pollFirstEntry();
                else treeMap.pollLastEntry();
            }
        }


        int[] answer = new int[2];

        if(treeMap.size() != 0) {
            answer[0] = treeMap.lastKey();
            answer[1] = treeMap.firstKey();

        }

        return answer;
    }
}