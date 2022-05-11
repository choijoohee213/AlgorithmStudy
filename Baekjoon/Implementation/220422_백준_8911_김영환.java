package com.younghwani.a220422;
import java.io.*;
import java.util.*;
/*
이동한 가장 좌측, 우측 컬럼 구하기, 상단, 하단 로우 구하기, 행과 열의 차이만큼을 곱해주면 넓이가 나온다. 이를 출력한다.
아래 그림에서 거북이는 가장 처음에 (0, 0)에 있고, 북쪽을 쳐다보고 있다.
 */
public class Main_bj_8911_거북이 {
    static final int[] di = {-1, 0, 1, 0}, dj = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int sr=0, sc=0, er=0, ec=0; // 이동한 가장 좌측, 우측 컬럼 구하기, 상단, 하단 로우 구하기
            int r=0, c=0, d=0; // 현재 거북이의 좌표와 방향
            String str = br.readLine();
            for (int i = 0; i < str.length(); i++) {
                char cmd = str.charAt(i);
                if(cmd=='F') { // 앞으로
                    r += di[d]; c += dj[d]; // 이동
                    sr=Math.min(sr, r); sc=Math.min(sc, c);
                    er=Math.max(er, r); ec=Math.max(ec, c);
                } else if(cmd=='B') { // 뒤로
                    r -= di[d]; c -= dj[d]; // 이동
                    sr=Math.min(sr, r); sc=Math.min(sc, c);
                    er=Math.max(er, r); ec=Math.max(ec, c);
                } else if(cmd=='L') { // 왼쪽으로 회전
                    d = d==0 ? 3 : d-1;
                } else if(cmd=='R') { // 오른쪽으로 회전
                    d = d==3 ? 0 : d+1;
                }
            }
            sb.append((er - sr) * (ec - sc)).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }
}
