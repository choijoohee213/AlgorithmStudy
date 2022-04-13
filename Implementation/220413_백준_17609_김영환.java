package com.younghwani.a220413;

import java.io.*;

/*
앞 뒤가 똑같은 .. 문자열
abba
한 문자만 삭제해 회문이 되는 경우. 유사회문
회문 0, 유사회문 1, 그 외 2
 */
public class Main_bj_17609_회문 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            String str = br.readLine();

            if(isPalindrome(str, 0, str.length()-1)) {
                sb.append(0).append("\n");
                continue;
            }

            if(isPseudo(str, 0, str.length()-1)) {
                sb.append(1).append("\n");
                continue;
            }

            sb.append(2).append("\n");
        }

        System.out.print(sb.toString());
        br.close();
    }

    static boolean isPseudo(String str, int left, int right) {
        while (left <= right) { // 양 끝을 하나씩 비교한다.
            if(str.charAt(left) != str.charAt(right)) { // 양 끝이 다른 경우, 하나씩 빼본다.
                boolean a = isPalindrome(str, left+1, right); // 왼쪽 문자 하나 뺀 회문
                boolean b = isPalindrome(str, left, right-1); // 오른쪽 문자 하나 뺀 회문
                if(!(a || b)) return false; // 두 경우 모두 회문이 아닌 경우
                return true;
            }
            left++;
            right--;
        }
        return true;
    }

    static boolean isPalindrome(String str, int left, int right) {
        while (left <= right) {
            if(str.charAt(left) != str.charAt(right)) return false; // 유사 회문 아님
            left++;
            right--;
        }
        return true; // 다 통과했으면 유사 회문임.
    }
}


/* 시간초과 -> 투 포인터 적용해 풀어야 하는가 보다.
public class Main_bj_17609_회문 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            String str = br.readLine();

            if(isPalindrome(str)) {
                sb.append(0).append("\n");
                continue;
            }

            boolean pseudo = false;
            for (int i = 0; i < str.length(); i++) {
                String s;
                if(i==0) {
                    s = str.substring(i+1, str.length());
                } else if(i==str.length()-1) {
                    s = str.substring(0, i);
                } else {
                    s = str.substring(0, i) + str.substring(i+1, str.length());
                }
                if(isPalindrome(s)) {
                    pseudo = true;
                    break;
                }
            }

            sb.append(pseudo ? 1: 2).append("\n");
        }

        System.out.print(sb.toString());
        br.close();
    }

    static boolean isPalindrome(String str) {
        char[] c = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) != c[c.length-1-i]) return false;
        }
        return true;
    }
}
 */