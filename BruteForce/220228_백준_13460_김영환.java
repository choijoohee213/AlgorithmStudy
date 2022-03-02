package com.younghwani.a220301;

import java.io.*;
import java.util.*;
/*
- 빨, 파 공이 있는 위치를 동시에 파악하기 위해 4차원 boolean 배열을 사용해본다.
- 최단거리 탐색이므로 BFS를 사용한다.
빨, 파 공의 위치를 파악하고, 이를 기준으로 bfs 탐색을 시작한다.
  -> 빨간 공을 이동한다 -> 파란 공을 이동한다. -> 빨간 공이 구멍에 들어갔는지 파악한다.(파란 공이 들어갔는지도 파악한다. 이 경우 continue)
  -> 두 공이 이동한 결과 좌표가 같은 경우도 비교한다.(이 경우 먼저 도착한 공이 해당 결과 좌표값을 가진다.)
 */
public class Main_bj_13460_구슬탈출2 {
    static int N, M, min=Integer.MAX_VALUE, di[]={-1, 0, 1, 0}, dj[]={0, 1, 0, -1};
    static char[][] arr;
    static boolean[][][][] V;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); // 행, 열의 수
        arr = new char[N][M]; V = new boolean[N][M][N][M]; // 그래프 및 방문 배열 초기화
        int rR=0, rC=0, bR=0, bC=0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j);
                if(arr[i][j]=='B') {bR=i; bC=j;} // B
                else if(arr[i][j]=='R') {rR=i; rC=j;} // R
            }
        }
        bfs(rR, rC, bR, bC);
        System.out.println(min==Integer.MAX_VALUE?-1:min);
        br.close();
    }
    static void bfs(int rR, int rC, int bR, int bC) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, rR, rC, bR, bC});
        V[rR][rC][bR][bC] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if(cur[0]>=10) return;
            for (int i = 0; i < 4; i++) {
                int ri=cur[1], rj=cur[2], bi=cur[3], bj=cur[4];
                boolean isR=false, isB=false;
                while (arr[bi+di[i]][bj+dj[i]]!='#') { // B - 벽이 아닌 경우 방향 지속해서 진행
                    bi+=di[i]; bj+=dj[i]; // 계속해서 이동
                    if(arr[bi][bj]=='O') {isB=true; break;} // 구멍 만나면 빠져나옴
                }
                if (isB) continue; // 파란 공이 먼저 들어가면 그 경우 무시
                while (arr[ri+di[i]][rj+dj[i]]!='#') { // R - 벽이 아닌 경우 방향 지속해서 진행
                    ri+=di[i]; rj+=dj[i]; // 계속해서 이동
                    if(arr[ri][rj]=='O') {isR=true; break;} // 구멍 만나면 빠져나옴
                }
                if (isR) {if(min>cur[0]+1) min=cur[0]+1; return;} // 빨간 공 들어가면 최소 카운트 리턴
                if(ri==bi && rj==bj) {
                    if(i==0)      {if(cur[1]>cur[3]) ri++; else bi++;}
                    else if(i==1) {if(cur[2]>cur[4]) bj--; else rj--;}
                    else if(i==2) {if(cur[1]>cur[3]) bi--; else ri--;}
                    else if(i==3) {if(cur[2]>cur[4]) rj++; else bj++;}
                }
                if(!V[ri][rj][bi][bj]) {
                    V[ri][rj][bi][bj] = true;
                    q.offer(new int[]{cur[0]+1, ri, rj, bi, bj});
                }
            }
        }
    }
}