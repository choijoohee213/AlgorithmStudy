package question2;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) {
        System.out.println(solution(437674, 3));
        System.out.println(solution(1100011, 10));
    }
    public static int solution(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int answer = 0;
        while(n/k>0){
            sb.append(n%k);
            n = n/k;
        }
        if(n%k!=0){
            sb.append(n%k);
        }
        String sum = sb.reverse().toString();
        System.out.println(sum);
        StringTokenizer st = new StringTokenizer(sum,"0");
        next:while(st.hasMoreElements()){
            long num = Long.parseLong(st.nextToken());
            if(num<2){
                continue;
            }
            for(long i=2; i*i<=num; i++){
                if (num % i == 0) {
                    continue next;
                }
            }
            answer++;
        }
        return answer;
    }
}
