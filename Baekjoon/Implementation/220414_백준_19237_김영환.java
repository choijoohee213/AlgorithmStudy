package com.younghwani.a220414;

import java.io.*;
import java.util.*;
public class Main_bj_19237_어른상어 {
    static class Smell {
        int num, time;
        public Smell(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }
    // 우선순위 1,2,3,4는 각각 위, 아래, 왼쪽, 오른쪽
    static int N, M, K, map[][], dirs[], deadSharks, di[] = {-1,1,0,0}, dj[] = {0,0,-1,1}, sharks[][][];
    static Smell[][] smells;
    static boolean[] isDead;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N]; // 상어의 위치 표시
        smells = new Smell[N][N]; // 상어의 영역 표시(상어의 번호, 냄세 유효 시간)
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                smells[i][j] = new Smell(0, 0);
            }
        }

        dirs = new int[M + 1]; // 상어의 방향
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= M; i++) dirs[i] = Integer.parseInt(st.nextToken()) - 1;

        // 상어, 방향별 우선순위
        sharks = new int[M + 1][4][4];
        for (int i = 1; i <= M; i++) { // 상어 별
            for (int d = 0; d < 4; d++) { // 방향 별
                st = new StringTokenizer(br.readLine(), " ");
                for (int p = 0; p < 4; p++) { // 우선 순위
                    sharks[i][d][p] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        isDead = new boolean[M+1]; // 상어 생존 여부

        int res = 1000; // 1000초 초과하면 -1 출력
        while (res-->0) {
            removeSmell(); // 냄세가 있으면 유효 시간을 감소시킨다.
            updateSmell(); // 상어가 살아 있는 곳에 냄세를 최신화해준다.
            map = move(); // 상어를 이동시킨다.
            if(deadSharks == M-1) break; // 1번 상어 빼고 다 죽은 경우
        }
        System.out.print(res>=0 ? 1000-res : -1);
        br.close();
    }
    static void removeSmell() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(smells[i][j].num!=0) {
                    smells[i][j].time--;
                    if(smells[i][j].time==0) smells[i][j].num=0; // 상어 냄세 유효 기간 끝나면 제거
                }
            }
        }
    }
    static void updateSmell() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j]!=0) smells[i][j] = new Smell(map[i][j], K); // 상어의 냄세 적용하기
            }
        }
    }
    static int[][] move() {
        int[][] ans=new int[N][N]; // 반환할 배열(새로운 상어 위치)
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if(map[r][c]!=0 && !isDead[map[r][c]]) {
                    int n=map[r][c], d= dirs[map[r][c]]; // 상어의 번호, 방향
                    int ni=0, nj=0; // 이동할 좌표
                    boolean canGo=false;
                    // 상어 이동 시작
                    for (int p = 0; p <4; p++) {
                        int nd=sharks[n][d][p]; // 상어별, 방향별, 우선 순위별 방향
                        ni=r+di[nd]; nj=c+dj[nd]; // 이동할 좌표
                        // 범위 체크, 다른 상어 냄세 있는 경우
                        if(ni<0 || nj<0 || nj>= N || ni>= N) continue;
                        if(smells[ni][nj].num==0) {
                            canGo=true;
                            dirs[n]=nd;
                            break;
                        }
                    }
                    // 진행 못했으면 자신의 냄세가 있는 방향으로 이동한다.
                    if(!canGo) {
                        for (int p = 0; p <4; p++) {
                            int nd=sharks[n][d][p]; // 상어별, 방향별, 우선 순위별 방향
                            ni=r+di[nd]; nj=c+dj[nd]; // 이동할 좌표
                            // 범위 체크, 자신의 냄세가 아닌 경우
                            if(ni<0 || nj<0 || nj>= N || ni>= N) continue;
                            if(smells[ni][nj].num==n) {
                                dirs[n]=nd;
                                break;
                            }
                        }

                    }
                    // 한 칸에 여러 상어가 남으면, 가장 작은 번호를 가진 상어를 제외하고 모두 격자 밖으로 쫓아낸다.(죽인다..)
                    if(ans[ni][nj]==0) ans[ni][nj]=n; // 이동할 좌표에 상어가 없다면
                    else { // 여러 마리인 경우
                        if (ans[ni][nj]<n) { // 기존 상어가 더 작다면
                            isDead[n]=true; // 현재 상어 죽임
                            deadSharks +=1;
                        } else { // 현재 상어가 더 작다면
                            isDead[ans[ni][nj]]=true;
                            ans[ni][nj]=n;
                            deadSharks +=1;
                        }
                    }
                }
            }
        }
        return ans;
    }
}
