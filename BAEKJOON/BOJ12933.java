import java.io.*;
import java.util.*;

/*
오리
 */

public class BOJ12933 {
    static class Duck {
        char ch;

        public Duck(char ch) {
            this.ch = ch;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Duck> list = new LinkedList<>();
        String s = br.readLine();

        for(int i = 0; i < s.length(); i++) {
            boolean existDuck = false;
            char currentCh = s.charAt(i);
            for(Duck d: list) {
                if(currentCh == 'q' && d.ch == 'k') {
                    d.ch = 'q';
                    existDuck = true;
                    break;
                }

                if(currentCh == 'u' && d.ch == 'q') {
                    d.ch = 'u';
                    existDuck = true;
                    break;
                }

                if(currentCh =='a' && d.ch == 'u') {
                    d.ch = 'a';
                    existDuck = true;
                    break;
                }

                if(currentCh == 'c' && d.ch == 'a') {
                    d.ch = 'c';
                    existDuck = true;
                    break;
                }

                if(currentCh == 'k' && d.ch == 'c') {
                    d.ch = 'k';
                    existDuck = true;
                    break;
                }
            }

            if(existDuck) continue;
            if(currentCh != 'q') {
                System.out.println(-1);
                return;
            }
            if(currentCh == 'q') {
                list.add(new Duck(currentCh));
                continue;
            }

            System.out.println(-1);
            return;
        }

        for(Duck d: list) {
            if(d.ch != 'k') {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(list.size());
    }
}
