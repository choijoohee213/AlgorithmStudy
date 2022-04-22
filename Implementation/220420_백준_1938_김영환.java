package com.younghwani.a220420;
import java.io.*;
import java.util.*;
/*
통나무 BBB를 이동 또는 회전시켜 EEE로 이동시키는 문제

U : 위로 한 칸
D : 아래로 한 칸
L : 왼쪽으로 한 칸
R : 오른쪽으로 한 칸
T : 중심점 기준 90도 회전(가로->세로 or 세로->가로)

움직일 위치에 다른 나무(1)가 없어야만 이동 가능
대각선 방향으로 통나무 놓는 것은 불가능함.
항상 통나무 길이는 3이다.

최소 횟수의 단위 동작을 구하라
 */
public class Main_bj_1938_통나무옮기기 {
    static class Tree {
        int r, c, dir, cnt; // 좌표, 통나무 방향(세로:1, 가로:2), 실행 횟수
        public Tree(int r, int c, int dir, int cnt) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
    static final int[] di={0, 0, 1, -1, 1, 1, -1, -1}, dj={1, -1, 0, 0, -1, 1, 1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 지형 입력
        char[][] map = new char[N+1][N+1];
        for(int i=1; i<=N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for(int j=1; j<=N; j++) map[i][j]=tmp[j-1];
        }

        Tree BBB = null; // 초기 BBB 촤표, 방향, 카운트 가진 객체
        int ec =0, er=0, eDir=0; // EEE의 중간 좌표 및 방향
        for(int i=1 ; i<=N ; i++) {
            for(int j=1 ; j<=N; j++) {
                if(map[i][j] =='B') { // 시작 위치 통나무
                    if(i-1>0 && i+1<=N) { // 세로로 위치한 경우
                        if(map[i-1][j]=='B' && map[i+1][j]=='B') {
                            map[i-1][j]='0'; map[i][j]='0'; map[i+1][j]='0'; // 지형에서 지워준다.
                            BBB = new Tree(i, j, 1, 0);
                        }
                    }
                    if(j-1>0 && j+1<=N) { // 가로로 위치한 경우
                        if(map[i][j-1]=='B' && map[i][j+1]=='B') {
                            map[i][j-1]='0'; map[i][j]='0'; map[i][j+1]='0'; // 지형에서 지워준다.
                            BBB = new Tree(i, j, 2, 0);
                        }
                    }
                } else if(map[i][j]=='E') { // 이동 완료 통나무 위치
                    if(i-1>0 && i+1<=N) { // 세로로 위치한 경우
                        if(map[i-1][j]=='E' && map[i+1][j]=='E') {
                            map[i-1][j]='0'; map[i][j]='0'; map[i+1][j]='0'; // 지형에서 지워준다.
                            er=i; ec=j; eDir=1; // EEE의 중간 좌표 및 방향 초기화
                        }
                    }
                    if (j-1>0 && j+1<=N) {
                        if(map[i][j-1]=='E' && map[i][j+1]=='E') {
                            map[i][j-1]='0'; map[i][j]='0'; map[i][j+1]='0';  // 지형에서 지워준다.
                            er=i; ec=j; eDir=2; // EEE의 중간 좌표 및 방향 초기화
                        }
                    }
                }
            }
        }

        int res =0; // 최소 횟수
        boolean[][][] V = new boolean[3][N+1][N+1]; // 통나무 방향에 따른 위치좌표 방문 체크, 같은 뱡향으로 같은 곳 두번 가지 않게함.
        Queue<Tree> q = new ArrayDeque<>();

        // 초기 이동 좌표 적용
        V[BBB.dir][BBB.r][BBB.c]=true;
        q.offer(BBB);

        while(!q.isEmpty()) {
            Tree cur = q.poll();
            if(cur.r==er && cur.c==ec && cur.dir==eDir) { // EEE가 놓여진 위치, 방향과 동일한 경우
                res = cur.cnt; // 최소 횟수를 적용
                break;
            }
            // R, L, D, U
            for(int i = 0; i < 4; i++) {
                if(cur.dir==1) { // 세로 방향
                    int ni=cur.r+di[i], nj=cur.c+dj[i];
                    if(ni<1||ni>N||nj<1||nj>N||ni+1>N||ni-1<1) continue; // 범위 체크
                    if(map[ni][nj]=='0' && map[ni+1][nj]=='0' && map[ni-1][nj]=='0') { // 나무 없어야 이동 가능
                        if(!V[cur.dir][ni][nj]) {
                            V[cur.dir][ni][nj]=true;
                            q.offer(new Tree(ni,nj,cur.dir,cur.cnt+1));
                        }
                    }
                } else if(cur.dir==2){ // 가로 방향
                    int ni=cur.r+di[i], nj=cur.c+dj[i];
                    if(ni<1||ni>N||nj<1||nj>N||nj+1>N||nj-1<1) continue; // 범위 체크
                    if(map[ni][nj]=='0' && map[ni][nj+1]=='0' && map[ni][nj-1]=='0') { // 나무 없어야 이동 가능
                        if(!V[cur.dir][ni][nj]) {
                            V[cur.dir][ni][nj]=true;
                            q.offer(new Tree(ni,nj,cur.dir,cur.cnt+1));
                        }
                    }
                }
            }

            // Turn
            int trees=1; // cur.r, cur.c 좌표도 포함
            for(int i = 0 ; i < 8 ; i++) {
                int ni=cur.r+di[i], nj=cur.c+dj[i];
                if(ni<1||ni>N||nj<1||nj>N||map[ni][nj]!='0') continue; // 범위 체크, 나무가 있을 때
                trees++;
            }
            if(trees==9) { // 3*3 좌표에 나무가 없다면, 회전 가능
                if(cur.dir==1 && !V[2][cur.r][cur.c]) { // 세로 방향
                    V[2][cur.r][cur.c]=true;
                    q.offer(new Tree(cur.r,cur.c,2,cur.cnt+1)); // 가로 방향으로 회전 완료
                } else if(cur.dir==2 && !V[1][cur.r][cur.c]) { // 가로 방향
                    V[1][cur.r][cur.c]=true;
                    q.offer(new Tree(cur.r,cur.c,1,cur.cnt+1)); // 세로 방향으로 회전 완료
                }
            }
        }

        System.out.print(res); // 최소 동작 횟수 출력
        br.close();
    }
}
