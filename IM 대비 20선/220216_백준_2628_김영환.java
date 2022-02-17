package com.younghwani.a220213;

import java.io.*;
import java.util.*;

/*
가위로 종이를 자른다.
잘린 종이들 중 가장 큰 종이의 넓이가 얼마인지 구하라.
한번 종이 자르면 처음부터 끝까지 다 자른다.
 */
public class Main_bj_2628_종이자르기 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_2628.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int W = Integer.parseInt(st.nextToken()); // 종이 가로 길이
        int H = Integer.parseInt(st.nextToken()); // 종이 세로 길이
        int N = Integer.parseInt(br.readLine()); // 자르는 횟수
        List<Integer> vertical = new ArrayList<>(), horizontal = new ArrayList<>(); // 수직, 수평 자를 인덱스
        for (int i = 0; i < N; i++) { // 수직, 수평 방향으로 자를 부분 각각 저장
            st = new StringTokenizer(br.readLine(), " ");
            int dir = Integer.parseInt(st.nextToken()); // 방향
            int num = Integer.parseInt(st.nextToken()); // 자를 인덱스
            if (dir == 0) horizontal.add(num);
            else vertical.add(num);
        }
        // 정렬한다. 이유? 정렬 후 잘라나가면 비교 범위가 줄어든다.
        vertical.sort(Comparator.naturalOrder());
        horizontal.sort(Comparator.naturalOrder());
        //System.out.println(vertical.toString());
        //System.out.println(horizontal.toString());

        int width=0, height=0, wTmp=0, hTmp=0;
        // 최대 너비 구하기
        for (int i = 0; i < vertical.size(); i++) { // 자른 부분 비교
            if (vertical.get(i) - wTmp > width) width = vertical.get(i) - wTmp;
            wTmp = vertical.get(i);
        }
        if (W - wTmp > width) width = W-wTmp; // 자르고 남은 부분 비교(우측)
        // 최대 높이 구하기
        for (int i = 0; i < horizontal.size(); i++) { // 자른 부분 비교
            if (horizontal.get(i) - hTmp > height) height = horizontal.get(i) - hTmp;
            hTmp = horizontal.get(i);
        }
        if (H - hTmp > height) height = H-hTmp; // 자르고 남은 부분 비교(아래)
        // 결과 출력
        System.out.println(width * height);
        br.close();
    }
}
