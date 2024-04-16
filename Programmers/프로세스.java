import java.util.*;

class 프로세스 {
    static class Point {
        int priority, location;

        Point(int priority, int location) {
            this.priority = priority;
            this.location = location;
        }
    }
    public int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Point> queue = new LinkedList<>();

        for(int i = 0; i < priorities.length; i++) {
            queue.add(new Point(priorities[i], i));
        }


        L: while(!queue.isEmpty()) {
            Point current = queue.poll();

            int tmp = current.priority;

            int size = queue.size();

            for(int i = 0; i < size; i++) {
                Point p = queue.poll();
                tmp = Math.max(tmp, p.priority);

                queue.add(p);
            }

            if(current.priority < tmp) queue.add(current);
            else {
                answer++;
                if(current.location == location) {
                    break L;
                }
            }
        }

        return answer;
    }
}