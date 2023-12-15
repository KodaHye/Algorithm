import java.util.Arrays;
import java.util.StringTokenizer;

class kakao_1 {
    // 선물지수: 이번 달까지 자신이 친구들에게 준 선물의 수에서 받은 선물의 수를 뺀 값

    static StringTokenizer st;

    static int solution(String[] friends, String[] gifts) {
        int answer = 0;

        int giftArr[][] = new int[friends.length][friends.length];
        int giftFactor[][] = new int[friends.length][2];
        int result[] = new int[friends.length];

        for (String gift: gifts) {
            st = new StringTokenizer(gift);
            int sender = findIdx(friends, st.nextToken());
            int receiver = findIdx(friends, st.nextToken());

            giftArr[sender][receiver]++;
            giftFactor[sender][0]++; //준 선물++
            giftFactor[receiver][1]++; // 받은 선물++;
        }

        for(int i = 0; i < friends.length; i++) {

            for(int j = i + 1; j < friends.length; j++) {
                if(i == j) continue;
                if(giftArr[i][j] > giftArr[j][i]) {
                    result[i]++;
                }
                if(giftArr[i][j] < giftArr[j][i]) {
                    result[j]++;
                }
                if((giftArr[i][j] == 0 && giftArr[j][i] == 0) || (giftArr[i][j] == giftArr[j][i])) {
                    // 선물지수가 큰 사람이 선물 받음
                    int senderGiftFactor = calGiftFactor(giftFactor, i);
                    int receiverGiftFactor = calGiftFactor(giftFactor, j);

                    if(senderGiftFactor == receiverGiftFactor) continue;
                    if(senderGiftFactor > receiverGiftFactor) {
                        result[i]++;
                    }
                    if(senderGiftFactor < receiverGiftFactor) {
                        result[j]++;
                    }
                }
            }
        }

        for(int resultArr: result) {
            answer = Math.max(resultArr, answer);
        }
        return answer;
    }

    private static int calGiftFactor(int[][] giftFactor, int num) {
        return giftFactor[num][0] - giftFactor[num][1];
    }

    static int findIdx(String[] friends, String name) {
        return Arrays.asList(friends).indexOf(name);
    }
}