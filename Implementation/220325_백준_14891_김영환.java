package com.younghwani.a220327;

import java.io.*;
import java.util.*;
import java.util.stream.*;
/*
바퀴의 2, 6번이 다른 바퀴와 만나는 부분이다.
극이 서로 다르다면 회전 방향과 반대 방향으로 회전한다.
극이 서로 같다면 그대로 둔다.
최종 결과는 바퀴 당 12시 방향 원소 값으로 계산한다.
 */
public class Main_bj_14891_톱니바퀴 {
    static int isOk[], wheel[][] = new int[4][8]; // 4개바퀴, 8개톱니
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력받기
        for (int i=0; i<4; i++) {
            wheel[i]= Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        // 회전
        int cnt = Integer.parseInt(br.readLine()); // 회전횟수
        for (int i = 0; i < cnt; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            isOk = new int[4];
            int num = Integer.parseInt(st.nextToken()) - 1; // 바퀴 번호
            int d = Integer.parseInt(st.nextToken()); // 회전방향(1:시계, -1:반시계)
            isRotate(num, d);
            rotate(isOk);
        }
        // 12시 방향 원소가 S극일 때, 결과값 계산
        int res=0;
        for(int i=0; i<4; i++) if(wheel[i][0]==1) res+=Math.pow(2, i);
        System.out.print(res);
        br.close();
    }
    // 2,6번 원소가 같으면 회전 안시키고, 다르면 회전시킴(회전 시킨 경우, 인접 바퀴에도 재귀 적용)
    static void isRotate(int num, int d) {
        isOk[num] = d; // 바퀴 방향 지정
        int prev = num-1, next = num+1;
        // left
        if (prev>=0 && isOk[prev]==0) { // prev 범위 내이고, 이전에 체크 안한 바퀴일 때
            if(wheel[prev][2] != wheel[num][6]) isRotate(prev, d*-1);
        }
        // right
        if (next<=3 && isOk[next]==0) { // next 범위 내이고, 이전에 체크 안한 바퀴일 때
            if(wheel[num][2] != wheel[next][6]) isRotate(next, d*-1);
        }
    }
    // 구한 회전 방향을 기준으로 회전
    static void rotate(int[] isOk) {
        for (int i=0; i<4; i++) { // 바퀴 4개
            if (isOk[i]==0) continue; // 회전 필요 없으면 건너 뜀
            int[] tmp = new int[8];
            for (int j=0; j<8; j++) { // 각 원소에 대해
                if (j+isOk[i]==-1) tmp[7]=wheel[i][j];
                else if (j+isOk[i]==8) tmp[0]=wheel[i][j];
                else tmp[j+isOk[i]]=wheel[i][j];
            }
            wheel[i]=tmp; // i번째 바퀴 회전 결과 반환
        }
    }
}
