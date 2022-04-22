package com.younghwani.a220420;
import java.io.*;
import java.util.*;
/*
구슬 탈출은 직사각형 보드에 빨간 구슬과 파란 구슬을 하나씩 넣은 다음, 빨간 구슬을 구멍을 통해 빼내는 게임이다.
세로 크기는 N, 가로 크기는 M
가장 바깥 행과 열은 모두 막혀져 있고, 보드에는 구멍이 하나 있다.
상우하좌로 싸악 이동! 공은 동시에 움직임. 빨간 구슬이 구멍에 빠지면 성공, 파란 구슬이 구멍에 빠지면 실패. 빨간 구슬과 파란 구슬이 동시에 빠져도 실패.
기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지이다.
이 문자열은 '.', '#', 'O', 'R', 'B' 로 이루어져 있다.
'.'은 빈 칸, '#'은 장애물 또는 벽, 'O'는 구멍의 위치, 'R'은 빨간 구슬의 위치, 'B'는 파란 구슬의 위치.
파란 구슬을 구멍에 넣지 않으면서 빨간 구슬을 10번 이하로 움직여서 빼낼 수 있으면 1을 없으면 0을 출력한다.
 */
public class Main_bj_13459_구슬탈출 {
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
        System.out.print(min<=10? 1 : 0);
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
