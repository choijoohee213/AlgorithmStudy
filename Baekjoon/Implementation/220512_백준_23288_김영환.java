package com.younghwani.a220512;
import java.io.*;
import java.util.*;
public class Main_bj_23288_주사위굴리기2 {
    static class Dice{
        int top, bottom, left, right, front, back;
        Dice() { top = 1; bottom = 6; left = 4; right = 3; front = 5; back = 2; } // 초기 주사위 상태 반영
        void changeStatus(int direction) { // 상우하좌
            int tmp=top;
            if (direction == 0) { top = front; front = bottom; bottom = back; back = tmp; } // 북
            else if(direction == 1) { top = left; left = bottom; bottom = right; right = tmp; } // 동
            else if(direction == 2) { top = back; back = bottom; bottom = front; front = tmp; } // 남
            else { top = right; right = bottom; bottom = left; left = tmp; } // 서
        }
    }
    static int N, M, K, map[][], di[]={-1,0,1,0}, dj[]={0,1,0,-1}, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        K = Integer.parseInt(st.nextToken()); // 이동 횟수
        map = new int[N][M]; // 지도
        for (int i = 0; i < N; i++) { // 입력
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) map[i][j]=Integer.parseInt(st.nextToken());
        }
        Dice dice = new Dice(); // 주사위 객체 초기화
        int r=0, c=0, d=1; // (1, 1)에서 시작하고, 초기 이동 방향은 동쪽이다. 인덱스는 -1씩 고려했다.
        while(K-->0) {
            int ni=r+di[d], nj=c+dj[d];
            if(ni<0||ni>=N||nj<0||nj>=M) d=(d+2)%4; // 이동방향에 칸이 없다면 이동방향을 반대로 변경한다.
            r+=di[d]; c+=dj[d]; // 이동 좌표 구하기
            dice.changeStatus(d); // 주사위 상태 변경하기
            res+=getScore(r, c); // 도착한 칸에 대한 점수를 획득한다. 각 이동에서 획득하는 점수의 합을 구해보자.
            // 주사위 아랫면과 지도의 정수를 비교해 이동방향을 결정한다.
            if(dice.bottom>map[r][c]) d=(d+1)%4; // 시계방향
            else if(dice.bottom<map[r][c]) d=(d+3)%4; // 반시계방향
        }
        System.out.print(res);
        br.close();
    }
    static int getScore(int r, int c) { // 칸 (x, y)에 대한 점수는 다음과 같이 구할 수 있다.
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] V = new boolean[N][M];
        int C=1; // (x, y)에서 동서남북 방향으로 연속해서 이동할 수 있는 칸의 수 C
        V[r][c] = true; q.offer(new int[]{r, c}); // 초기 상태 지정
        int B = map[r][c]; // (x, y)에 있는 정수 B
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d=0; d<4; d++) {
                int ni=cur[0]+di[d], nj=cur[1]+dj[d];
                if(ni<0||ni>=N||nj<0||nj>=M||V[ni][nj]||map[ni][nj]!=B) continue; // 이동할 수 있는 칸에는 모두 정수 B가 있다.
                C++; // 이동 가능 칸 수 카운팅
                V[ni][nj] = true;
                q.offer(new int[]{ni, nj});
            }
        }
        return B*C; // 점수는 B와 C를 곱한 값이다.
    }
}
