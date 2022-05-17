package com.younghwani.a220517;

import java.util.Stack;

/*
알맞는 괄호가 되는 경우 세기
stack 활용
 */
public class Solution_pro_괄호회전하기 {
    public static void main(String[] args) {
        System.out.println(solution("[](){}")); // 3
        System.out.println(solution("}]()[{")); // 2
        System.out.println(solution("[)(]")); // 0
        System.out.println(solution("}}}")); // 0
    }

    public static int solution(String s) {
        char[] arr = s.toCharArray();

        // 괄호 여닫는 개수조차 맞지 않는 경우
        int open = 0, close = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]=='('||arr[i]=='{'||arr[i]=='[') open++;
            if(arr[i]==')'||arr[i]=='}'||arr[i]==']') close++;
        }
        if (open != close) return 0;

        // 괄호 맞는지 판단
        int answer = 0;
        for (int i = 0; i < arr.length; i++) {
            Stack<Character> stack = new Stack<>();
            for (int j = i; j < arr.length+i; j++) {
                char cur = arr[j % arr.length];
                if(cur=='('||cur=='{'||cur=='[') stack.add(cur);
                else if (cur == ')') {
                    if(!stack.isEmpty()&&stack.peek()=='(') stack.pop();
                    else stack.add(cur);
                } else if (cur == '}') {
                    if(!stack.isEmpty()&&stack.peek()=='{') stack.pop();
                    else stack.add(cur);
                } else if (cur == ']') {
                    if(!stack.isEmpty()&&stack.peek()=='[') stack.pop();
                    else stack.add(cur);
                }
            }
            if(stack.isEmpty()) answer++;
        }

        return answer;
    }
}
