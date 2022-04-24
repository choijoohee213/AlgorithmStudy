package com.younghwani.a220424;

import java.io.*;
import java.util.stream.Stream;

/*
+는 더하기, -는 빼기, 공백은 숫자를 이어 붙이는 것
결과가 0이 될 수 있는지
ASCII 순서에 따라 결과가 0이 되는 모든 수식을 출력
 */
public class Main_bj_7490_0만들기 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-->0) {
            int N = Integer.parseInt(br.readLine());
            dfs(N, 1, "1");
            sb.append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }
    static void dfs(int N, int depth, String str) {
        if(depth==N) {
            if(solve(str)) sb.append(str).append("\n");
            return;
        }
        dfs(N, depth+1, str+" "+(depth+1));
        dfs(N, depth+1, str+"+"+(depth+1));
        dfs(N, depth+1, str+"-"+(depth+1));
    }
    static boolean solve(String str) {
        str = str.replaceAll(" ", ""); // 공백은 붙여야하니 없애버림
        int[] numbers = Stream.of(str.split("[+,-]")).mapToInt(Integer::parseInt).toArray();
        int res = numbers[0], idx=1;
        for (int i = 1; i < str.length(); i++) { // +, -를 찾게 되면 numbers 배열의 다음 값을 찾아서 반환
            if (str.charAt(i) == '+') res+=numbers[idx++];
            else if (str.charAt(i) == '-') res-=numbers[idx++];
        }
        return res==0? true:false;
    }
}
