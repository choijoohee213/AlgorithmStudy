package com.younghwani.a220406;

import java.io.*;
import java.util.*;
/*
9명으로 이뤄진 공격 수비 번갈아 하는 게임이다.
하나의 이닝은 공격 및 수비로 이뤄짐
총 N 이닝 동안 게임함.
한 이닝에 3 아웃 발생 시 이닝 종료, 두 팀의 공수교체
9번타자까지 쳤지만 3아웃 발생하지 않은 상태면 다시 1번 타자 순서.
2이닝을 6번 타자까지 쳤으면, 3이닝은 7번 타자부터 침
공격일 때 1,2,3루를 거쳐 홈으로 들어오면 +1점
안타 : 1, 2루타 : 2, 3루타: 3, 홈런 : 4, 아웃 : 0
 */
public class Main_bj_17281_야구 {
    static int N, ord[], hit[][], res;
    static boolean[] V;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        hit = new int[N+1][10];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= 9; j++) hit[i][j]=Integer.parseInt(st.nextToken());
        }
        V=new boolean[10]; ord=new int[10];  // 순열 위한 배열 초기화
        V[4]=true; ord[4]=1;                 // 4번타자는 1번 선수로 지명함
        order(2);                       // 1번 선수는 순서 지정 끝났으니, 2번 선수부터 ordering
        System.out.print(res);
        br.close();
    }
    static void order(int cnt) {
        if(cnt==10) {
            game();
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if(V[i]) continue;
            V[i]=true;
            ord[i]=cnt;
            order(cnt+1);
            V[i]=false;
        }
    }
    static void game() {
        int score=0, player=1, base[];
        for (int i = 1; i <= N; i++) {
            int out=0;
            base = new int[] {0,0,0,0};
            while (out<3) {
                int ball = hit[i][ord[player]];
                if(ball==0) { // 아웃
                    out++;
                } else if(ball==1) {
                    for (int j = 3; j >= 1; j--) {
                        if(base[j]>0) {
                            if(j==3) { // 이제 홈으로 들어옴
                                score++;
                                base[j]--;
                            } else {
                                base[j]--;
                                base[j+1]++;
                            }
                        }
                    }
                    base[1]++;
                } else if(ball==2) {
                    for (int j = 3; j >= 1; j--) {
                        if(base[j]>0) {
                            if(j==2||j==3) { // 이제 홈으로 들어옴
                                score++;
                                base[j]--;
                            } else {
                                base[j]--;
                                base[j+2]++;
                            }
                        }
                    }
                    base[2]++;
                } else if(ball==3) {
                    for (int j = 3; j >= 1; j--) {
                        if(base[j]>0) {
                            score++;
                            base[j]--;
                        }
                    }
                    base[3]++;
                } else if(ball==4) { // 홈런
                    score++;
                    for(int j = 1; j <= 3; j++) if(base[j]>0) { score++; base[j]--; }
                }
                player = player+1>9 ? 1 : player+1;
            }
        }
        if(res<score) res=score;
    }
}
