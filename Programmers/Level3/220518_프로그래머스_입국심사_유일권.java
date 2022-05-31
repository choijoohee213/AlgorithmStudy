//https://programmers.co.kr/learn/courses/30/lessons/43238
//Solved :

import java.util.*;

class Main{
    public static void main(String[] args){
        System.out.println(solution(6, new int[]{7,10}));
    }
    public static long solution(int n, int[] times) {
        long answer = 0;

        Arrays.sort(times);
        long start = 1;// 최선의 경우
        long end = (long)times[times.length-1] * n;//최악의 경우

        while(start <= end){
            long sum = 0;
            long mid = (start+end)/2;
            for(int time : times){
                sum += mid/time;// 심사관마다 중간시간을 기준으로 심사할 수 있는 사람의 수의 합
            }

            if(sum >= n){ // 심사하는 사람의 수가 더 많거나 같으므로 시간을 줄일 수 있다.
                end = mid-1;
                answer = mid;
            }else{// 심사하는 사람의 수가 적으므로 시간을 늘려야한다.
                start = mid+1;
            }
        }

        return answer;
    }
}