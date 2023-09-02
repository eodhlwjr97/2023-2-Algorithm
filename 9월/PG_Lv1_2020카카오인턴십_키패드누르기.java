import java.io.*;
import java.util.*;

class Pos {
    int r;
    int c;
    public Pos(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
class Solution {
    public String solution(int[] numbers, String hand) {
        HashMap<Integer, Pos> hm = new HashMap<Integer, Pos>(){{
            put(1,new Pos(1,1));
            put(2,new Pos(1,2));
            put(3,new Pos(1,3));
            put(4,new Pos(2,1));
            put(5,new Pos(2,2));
            put(6,new Pos(2,3));
            put(7,new Pos(3,1));
            put(8,new Pos(3,2));
            put(9,new Pos(3,3));
            put(0,new Pos(4,2));
            put(10,new Pos(4,1)); // *
            put(12,new Pos(4,3)); // #
        }};
        
        // 현재 왼손, 오른손 위치 
        int left = 0;
        int right = 1;
        int[] handNow = new int[2];
        handNow[left] = 10;
        handNow[right] = 12;
        
        String answer = "";
        for(int i=0; i<numbers.length; i++) {
            // 1, 4, 7은 왼손
            if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
                answer += "L";
                handNow[left] = numbers[i];
                continue;
            }
            
            // 3, 6, 9는 오른손
            if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
                answer += "R";
                handNow[right] = numbers[i];
                continue;
            }
            
            // 2, 5, 8, 0은 가까운손 : 왼손/오른손잡이 구별
            // 왼손과의 거리
            int fromLeft = Math.abs(hm.get(numbers[i]).r - hm.get(handNow[left]).r) + Math.abs(hm.get(numbers[i]).c - hm.get(handNow[left]).c);
            // 오른손과의 거리
            int fromRight = Math.abs(hm.get(numbers[i]).r - hm.get(handNow[right]).r) + Math.abs(hm.get(numbers[i]).c - hm.get(handNow[right]).c);
            
            if(fromLeft > fromRight) { // 오른손이 더 가까우면
                answer += "R";
                handNow[right] = numbers[i];
            } else if(fromLeft < fromRight) { // 왼손이 더 가까우면
                answer += "L";
                handNow[left] = numbers[i];
            } else { // 왼손, 오른손 거리가 같다면
                if(hand.equals("left")) { // 왼손잡이
                    answer += "L";
                    handNow[left] = numbers[i];
                } else if(hand.equals("right")) { // 오른손잡이
                    answer += "R";
                    handNow[right] = numbers[i];
                }
            }
            continue;
        }
        return answer;
    }
}
