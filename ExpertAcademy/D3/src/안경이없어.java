import java.util.Scanner;

public class 안경이없어 {
	// 입력: 알파벳 대문자 두 문자열
	// 출력: 같은 문자로 판결하는가 아닌가
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		// 허걱 배열로 저장할 필요가 없었따
		String charString1 = "CEFGHIJKLMNSTUVWXYZ";
		String charString2 = "ADOPQR";
		String charString3 = "B";

		for (int test_case = 1; test_case < T + 1; test_case++) {
			String str1 = sc.next();
			String str2 = sc.next();

			char[] str1Char = new char[str1.length()];
			char[] str2Char = new char[str2.length()];

			if (str1.length() != str2.length()) {
				System.out.println("#" + test_case + " " + "DIFF");
			} else {
				for (int i = 0; i < str1.length(); i++) {
					if (charString1.contains(String.valueOf(str1.charAt(i)))) str1Char[i] = '0';
					if (charString1.contains(String.valueOf(str2.charAt(i)))) str2Char[i] = '0';
					if (charString2.contains(String.valueOf(str1.charAt(i)))) str1Char[i] = '1';
					if (charString2.contains(String.valueOf(str2.charAt(i)))) str2Char[i] = '1';
					if (charString3.contains(String.valueOf(str1.charAt(i)))) str1Char[i] = '2';
					if (charString3.contains(String.valueOf(str2.charAt(i)))) str2Char[i] = '2';
				}
				if (String.valueOf(str1Char).equals(String.valueOf(str2Char))) {
					System.out.println("#" + test_case + " " + "SAME");
				} else {
					System.out.println("#" + test_case + " " + "DIFF");
				}
			}
		}
	}
}
