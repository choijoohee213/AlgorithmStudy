package com.younghwani.a220511;
import java.util.Scanner;
class Solution_pro_lv2_K진수에서소수개수구하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        System.out.print(solution(n, k)); // 소수의 개수를 출력한다.
        sc.close();
    }
    static int solution(int n, int k) {
        int answer = 0;
        // 입력된 n을 k 진수로 변환한다.
        String num = "";
        while (n > 0) {
            num = n % k + num;
            n /= k;
        }
        // 변환된 진수에서 P 소수를 찾는다. (소수 판정은 10진수 값을 기준으로 한다.)
        int i=0, j;
        for (; i < num.length(); i=j) {
            // 0을 기준으로 split 해가며 순회한다.
            // 0P, 0P0, P0, P (101처럼 중간에 0이 들어간 구조는 P가 될 수 없다.)
            for (j = i + 1; j < num.length() && num.charAt(j) != '0'; j++) continue;
            // 소수 여부 확인
            boolean flag = true;
            long sub = Long.parseLong(num.substring(i, j));
            if(sub<=1) flag = false;
            // 2~sub까지 반복하니 시간초과가 난다...
            // 2~Math.sqrt(sub)까지 돌리면 된다고 한다.
            for (int h = 2; h <= Math.sqrt(sub); h++) {
                if(sub%h==0) flag=false;
            }
            if(flag) answer++;
        }
        return answer;
    }
}
