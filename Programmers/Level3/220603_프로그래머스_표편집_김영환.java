package com.younghwani.a220613;

import java.util.Arrays;
import java.util.Stack;

public class Solution_pro_표편집 {
    public static void main(String[] args) {
        System.out.println(solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"}));
    }

    public static String solution(int n, int k, String[] cmd) {
        String answer = "";

//        System.out.println(n);
//        System.out.println(k);
//        System.out.println(Arrays.toString(cmd));

        Stack<Integer> S = new Stack<>();

        for (String c : cmd) {
            String[] arr = c.split(" ");
//            System.out.println(Arrays.toString(arr));
            if (arr[0].equals("U")) {
                k -= Integer.parseInt(arr[1]);
            } else if (arr[0].equals("D")) {
                k += Integer.parseInt(arr[1]);
            } else if (arr[0].equals("C")) {
                S.push(k);
                n--;
                if (k == n) k--;
            } else if (arr[0].equals("Z")) {
                if(S.peek() <= k) k++;
                S.pop();
                n++;
            }
//            System.out.println(S.toString());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append('O');
//        System.out.println(S.toString());
        while (!S.isEmpty()) sb.insert(S.pop().intValue(), 'X');
        answer = sb.toString();

        return answer;
    }
}
