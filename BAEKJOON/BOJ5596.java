import java.util.Scanner;

/*
 * 시험점수
 */
public class BOJ5596 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A=0, B=0;
        for(int i = 0; i < 4; i++) A += sc.nextInt();
        for(int i = 0; i < 4; i++) B += sc.nextInt();
        System.out.println(Math.max(A, B));        
    }
}
