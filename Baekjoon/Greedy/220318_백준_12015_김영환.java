package com.younghwani.a220318;

import java.io.*;
import java.util.*;

public class Main_bj_12015_가장긴증가하는부분수열2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0); // 값 비교로 문제 푸니, 초기 값으로 의미없는 값인 0 대입
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        while (N-->0) {
            int val = Integer.parseInt(st.nextToken());
            if (val > list.get(list.size()-1)) list.add(val); // 더 큰 값이 들어왔을 때
            else { // 들어온 값이 작거나 같을 때, binary search 후 알맞는 위치에 넣어줌.- 더 작은 값으로 update 해서 LIS에 포함될 수 있는 원소의 하한을 낮춤.(lower bound)
                int s=0, e=list.size()-1;
                while (s<e) { // s>=e가 되면 넣을 위치 찾은 경우
                    int m = (s+e) / 2;
                    if(list.get(m)>=val) e=m; // 앞 부분인 경우
                    else s=m+1; // 뒷 부분인 경우
                }
                list.set(e, val); // 새로 입력된 값으로 변경
            }
        }
        System.out.println(list.size()-1);
        br.close();
    }
}
