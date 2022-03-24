package com.younghwani.a220324;

import java.io.*;

public class Main_bj_12904_A와B {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        if(S==T) {
            System.out.print(1);
            return;
        }

        boolean flag = false; // false : 뒤에서부터 계산
        char[] arr = T.toCharArray();
        int idx=arr.length-1, len=arr.length;

        while (len-->S.length()) {
            if(flag) { // 앞에서부터
                if(arr[idx]=='A') idx++;
                else { // 'B'
                    flag=false;
                    idx+=len;
                }
            } else { // 뒤에서부터
                if(arr[idx]=='A') idx--;
                else { // 'B'
                    flag=true;
                    idx-=len;
                }
            }
        }

        // 값 비교
        String res = "";
        if(flag) { // 뒤집어진 상태이므로 역순 결과
            StringBuilder tmp = new StringBuilder();
            for (int i = idx+len; i >= idx; i--) tmp.append(arr[i]);
            res = tmp.toString();
        } else res = T.substring(idx-len, idx+1); // 정방향 결과

        if(res.equals(S)) System.out.print(1);
        else System.out.print(0);
        br.close();
    }
}
