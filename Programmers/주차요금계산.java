import java.util.*;

class 주차요금계산 {
    static class Status {
        boolean isIn;
        int inHour, inMin, totalTime, fee;

        Status(boolean isIn, int inHour, int inMin) {
            this.isIn = isIn;
            this.inHour = inHour;
            this.inMin = inMin;
        }
    }
    static TreeMap<String, Status> cars = new TreeMap<String, Status>();

    public int[] solution(int[] fees, String[] records) {
        StringTokenizer st;

        for(int i = 0; i < records.length; i++) {
            st = new StringTokenizer(records[i]);

            String time = st.nextToken();
            String sHour = time.substring(0, 2);
            String sMin = time.substring(3, 5);
            int hour = 0;
            int min = 0;

            if(sHour == "00") hour = 0;
            else if(sHour.charAt(0) == '0') hour = sHour.charAt(1) - '0';
            else hour = Integer.parseInt(sHour);

            if(sMin == "00") min = 0;
            else if(sMin.charAt(0) == '0') min = sMin.charAt(1) - '0';
            else min = Integer.parseInt(sMin);

            String num = st.nextToken();
            String status = st.nextToken();

            if(status.equals("IN")) {
                if(cars.get(num) == null) cars.put(num, new Status(true, hour, min));
                else {
                    Status car = cars.get(num);
                    car.isIn = true;
                    car.inHour = hour;
                    car.inMin = min;
                }
                // 차가 나가는 경우
            } else {
                Status car = cars.get(num);
                car.totalTime += (60 * (hour - car.inHour) + (min - car.inMin));
                car.isIn = false;
            }
        }

        int[] answer = new int[cars.size()];
        int idx = 0;

        for(Status s: cars.values()) {
            if(s.isIn) s.totalTime +=
                    (60 * (23 - s.inHour) + (59 - s.inMin));
            if(s.totalTime <= fees[0]) {
                s.fee += fees[1];
            } else {
                s.fee +=
                        (fees[1] + Math.ceil((s.totalTime - fees[0]) * 1.0 / fees[2]) * fees[3]);
            }
            answer[idx++] = s.fee;
        }

        return answer;
    }
}