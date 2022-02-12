package com.younghwani.a220212;

import java.util.*;
import java.io.*;
import java.util.stream.Stream;

public class Main_bj_2477_참외밭 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_2477.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 면적당 참외개수
        int[][] arr = new int[6][2]; // 입력 - 참외밭의 모양은 육각형 모양이라 함(항상 ㄱ 자 모양의 틀을 유지하며 회전한 형태)
        for (int i = 0; i < 6; i++) arr[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //System.out.println(Arrays.deepToString(arr));
        //System.out.println();
        // 1, 2번이 평행(동, 서)
        // 3, 4번이 평행(남, 북)

        // 과정을 하나로 합쳐 계산해도 됨, 설명하기 좋게 모든 과정 풀어놓음.

        // 1. (동, 서, 남, 북)의 가장 긴 길이를 구한다. 인덱스도 함께 저장한다.
        int m1=0, m2=0, m3=0, m4=0;
        int m1Idx=0, m2Idx=0, m3Idx=0, m4Idx=0;
        for (int i = 0; i < 6; i++) {
            if (arr[i][0]==1) if(m1<arr[i][1]) {m1=arr[i][1]; m1Idx = i;}
            if (arr[i][0]==2) if(m2<arr[i][1]) {m2=arr[i][1]; m2Idx = i;}
            if (arr[i][0]==3) if(m3<arr[i][1]) {m3=arr[i][1]; m3Idx = i;}
            if (arr[i][0]==4) if(m4<arr[i][1]) {m4=arr[i][1]; m4Idx = i;}
        }
        //System.out.println(m1 + " " + m2 + " " +m3 + " " +m4);
        //System.out.println(m1Idx + " " + m2Idx + " " +m3Idx + " " +m4Idx);
        //System.out.println();

        // 2. (m1, m2), (m3, m4)를 비교하여 가장 긴 길이를 찾는다. -> 임이로 밑변 w, 높이 h라 설정하고 진행한다.(회전하면 위치는 바뀔 수 있음)
        int w, h, wIdx, hIdx;
        if(m1>m2) {w = m1; wIdx=m1Idx;} else {w = m2; wIdx=m2Idx;}
        if(m3>m4) {h = m3; hIdx=m3Idx;} else {h = m4; hIdx=m4Idx;}
        //System.out.println(w + " " + h);
        //System.out.println(wIdx + " " + hIdx);
        //System.out.println();

        // 3. w와 인접한 두 변은 육각형을 이루는 w, w(튀어나온 부분) -> wi = w-w(튀어나온 부분)
        //    h와 인접한 두 변은 육각형을 이루는 h, h(튀어나온 부분) -> hi = h-h(튀어나온 부분)
        int wi, hi;
        wi = Math.abs(arr[hIdx+1>5?0:hIdx+1][1] - arr[hIdx-1<0?5:hIdx-1][1]);
        hi = Math.abs(arr[wIdx+1>5?0:wIdx+1][1] - arr[wIdx-1<0?5:wIdx-1][1]);
        //System.out.println(wi + " " + hi);

        // 4. 밭의 넓이 = (w*h)-(wi*hi)
        int res= w*h - wi*hi;
        //System.out.println(res);

        // 5. 밭에서 자랄 수 있는 참외 개수 = 밭의 넓이 * 면적 당 참외 개수
        int cnt = res * N;
        System.out.println(cnt);

        br.close();
    }
}
