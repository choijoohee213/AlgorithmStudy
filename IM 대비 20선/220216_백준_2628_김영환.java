package com.younghwani.a220213;

import java.io.*;
import java.util.*;

public class Main_bj_2628_종이자르기 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_2628.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(br.readLine());
        List<Integer> vertical = new ArrayList<>(), horizontal = new ArrayList<>(); // 수직, 수평 자를 인덱스
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int dir = Integer.parseInt(st.nextToken()); // 방향
            int num = Integer.parseInt(st.nextToken()); // 자를 인덱스
            if (dir == 0) horizontal.add(num);
            else vertical.add(num);
        }
        vertical.sort(Comparator.naturalOrder());
        horizontal.sort(Comparator.naturalOrder());
        //System.out.println(vertical.toString());
        //System.out.println(horizontal.toString());

        int width=0, height=0, wTmp=0, hTmp=0;
        // 최대 너비 구하기
        for (int i = 0; i < vertical.size(); i++) {
            if (vertical.get(i) - wTmp > width) width = vertical.get(i) - wTmp;
            wTmp = vertical.get(i);
        }
        if (W - wTmp > width) width = W-wTmp;
        // 최대 높이 구하기
        for (int i = 0; i < horizontal.size(); i++) {
            if (horizontal.get(i) - hTmp > height) height = horizontal.get(i) - hTmp;
            hTmp = horizontal.get(i);
        }
        if (H - hTmp > height) height = H-hTmp;
        // 결과 출력
        System.out.println(width * height);
        br.close();
    }
}
