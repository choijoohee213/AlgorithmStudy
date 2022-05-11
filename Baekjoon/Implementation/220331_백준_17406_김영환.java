package com.younghwani.a220331;

import java.io.*;
import java.util.*;

public class Main_bj_17406_배열돌리기4 {
    static int N, M, K, A[][], ord[], cmd[][], min=Integer.MAX_VALUE;
    static boolean[] V;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        K = Integer.parseInt(st.nextToken()); // 회전 수
        A = new int[N][M];      // 배열
        cmd = new int[K][3];    // 회전 명령
        ord = new int[K];       // 회전 순서
        V = new boolean[K];     // 순열 방문 여부 파악에 사용
        // 배열 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) A[i][j] = Integer.parseInt(st.nextToken());
        }
        // 회전 명령 입력받기
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            cmd[i][0] = Integer.parseInt(st.nextToken())-1;
            cmd[i][1] = Integer.parseInt(st.nextToken())-1;
            cmd[i][2] = Integer.parseInt(st.nextToken());
        }
        order(0); // 순서에 따라 회전 후 최소값 계산
        System.out.print(min); // 최소값 출력
        br.close();
    }
    // 회전 명령의 순서 구하기
    static void order(int n) {
        if(n==K){
            // 원본 배열 유지하기 위해 deep copy
            int[][] arr = new int[N][M];
            for(int i=0; i<N; i++) for(int j=0; j<M; j++) arr[i][j]=A[i][j];
            // 순서에 따라 회전시키기
            for (int i = 0; i < K; i++) {
                int r = cmd[ord[i]][0], c = cmd[ord[i]][1], s = cmd[ord[i]][2];
                spin(r-s, c-s, r+s, c+s, arr);  // 회전
            }
            // 행별 최소값 계산하기
            for (int i = 0; i < N; i++) {
                int tmp = 0;
                for (int j = 0; j < M; j++) tmp+=arr[i][j];
                if(min>tmp) min=tmp;
            }
            return;
        }
        // 순열구하기
        for (int i = 0; i < K; i++) {
            if(V[i]) continue;
            V[i]=true;
            ord[n]=i;
            order(n+1);
            V[i]=false;
        }
    }
    // 회전
    static void spin(int sr, int sc, int er, int ec, int[][] arr) {
        if(sr==er&&sc==ec) return; // 안쪽까지 회전 모두 끝냈으면 종료
        int[] tmp=new int[3];
        tmp[0]=arr[sr][ec]; tmp[1]=arr[er][ec]; tmp[2]=arr[er][sc];
        // 우하좌상 순서로 돌림
        for (int c = ec; c > sc; c--) { // 오른쪽으로 이동
            arr[sr][c] = arr[sr][c-1];
        }
        for (int r = er; r > sr; r--) { // 아래쪽으로 이동
            if(r==sr+1) arr[r][ec] = tmp[0];
            else arr[r][ec]=arr[r-1][ec];
        }
        for (int c = sc; c < ec; c++) { // 왼쪽으로 이동
            if(c==ec-1) arr[er][c]=tmp[1];
            else arr[er][c]=arr[er][c+1];
        }
        for (int r = sr; r < er; r++) { // 위쪽으로 이동
            if(r==er-1) arr[r][sc]=tmp[2];
            else arr[r][sc]=arr[r+1][sc];
        }
        spin(sr+1, sc+1, er-1, ec-1, arr); // lv을 낮춰 안쪽으로 들어감.
    }
}
