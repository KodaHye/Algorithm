import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
 
public class SWEA1289 {
    /*
    메모리 bit 중 하나를 골라 0인지 1인지 결정하면 해당 값이 메모리 끝까지 덮어씌움
    0100 -> 3번쨰 비트 1 설정 -> 0111
    모든 비트가 0일 때 원래 상태로 돌아가기까지 수정 횟수
     */
    static String input;
    static char[] originArr;
    static char[] inputArr;
    static int count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
         
        for(int test_case = 1; test_case < T + 1; test_case++) {
            count = 0;
            input = br.readLine();
            inputArr = new char[input.length()];
            inputArr = input.toCharArray();
            originArr = new char[input.length()];
            // originArr 초기화 상태로
            Arrays.fill(originArr, '0');
            func(0);
            sb.append("#" + test_case + " " + count + "\n");
        }
        System.out.println(sb);
 
    }
     
    // idx: 입력된 문자열의 인덱스(왼쪽에서부터 0)
    public static void func(int idx) {
        if(idx == inputArr.length) return;
         
        for(int i = 0; i < inputArr.length; i++) {
            if(inputArr[i] != originArr[i]) {
                for(int j = i; j < inputArr.length; j++) {
                    if(inputArr[j] == '0') inputArr[j] = '1';
                    else if(inputArr[j] == '1') inputArr[j] = '0';
                }
                count++;
            }
        }
         
        func(idx + 1);
    }
 
}