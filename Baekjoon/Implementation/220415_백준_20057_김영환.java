package com.younghwani.a220417;

import java.io.*;
import java.util.*;

public class Main_bj_20057_마법사상어와토네이도 {
    public static int N, map[][],  di[] = {-1, 0, 1, 0}, dj[] = {0, 1, 0, -1}; // 상우하좌
    public static long res = 0; // 격자 밖으로 나간 모래의 양
    // di,dj(상우하좌) 별 모래를 이동시킬 좌표 dr,dc
    public static int[][] dr = {{1, 1, 0, 0, 0, 0, -1, -1, -2, -1}, {-1, 1, -2, 2, -1, 1, -1, 1, 0, 0},
                                {-1, -1, 0, 0, 0, 0, 1, 1, 2, 1}, {-1, 1, -2, 2, -1, 1, -1, 1, 0, 0}};
    public static int[][] dc = {{-1, 1, -2, 2, -1, 1, -1, 1, 0, 0}, {-1, -1, 0, 0, 0, 0, 1, 1, 2, 1},
                                {-1, 1, -2, 2, -1, 1, -1, 1, 0, 0}, {1, 1, 0, 0, 0, 0, -1, -1, -2, -1}};
    public static int[] per = {1, 1, 2, 2, 7, 7, 10, 10, 5}; // di,dj별 > dr,dc별 > 퍼센티지
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        // inputs
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        int init_r = (N+1)/2, init_c = (N+1)/2; // 처음 이동할 좌표(N은 홀수)
        tornado(init_r, init_c, 3);
        System.out.print(res);
        br.close();
    }
    // 같은 길이만큼 2번 방향 바꿔 이동하면 이동하는 길이가 증가한다.
    public static void tornado(int r, int c, int d) {
        int length = 1; // 처음 방향 별 이동거리
        loop:for (int i = 1; i <= N; i++) { // d인 방향 N/2개, -d인 방향 N/2개 있으므로 N번 돈다.
            for (int j = 1; j <= 2; j++) { // 2번 돌고, 방향 변경
                for (int l = 1; l <= length; l++) { // 토네이도가 같은 방향으로 이동하는 길이만큼 이동
                    int ni=r+di[d], nj=c+dj[d];
                    if (ni==1 && nj==0) break loop;   // (1,1)에 위치했을 때 종료
                    // 이동 및 비율 계산
                    int sand = map[ni][nj]; // y(이동후시점) 좌표의 모래의 양
                    int sum = 0; // 퍼센티지에 이동시킨 모래의 양
                    for (int k = 0; k < 10; k++) { // 퍼센티지 있는 위치와 알파 위치 포함해서 10번의 퍼짐
                        if (k == 9) { // 알파로 이동하는 경우
                            if (ni+dr[d][k]<1 || ni+dr[d][k]>N || nj+dc[d][k]<1 || nj+dc[d][k]>N) {
                                res += sand - sum; // 격자 밖으로 나간 모래
                            } else map[ni+dr[d][k]][nj+dc[d][k]] += sand-sum; // 전체 모래 양에서 퍼센티지로 이동한 경우를 제외
                        } else { // 퍼센티지로 이동하는 경우
                            if (ni+dr[d][k]<1 || ni+dr[d][k]>N || nj+dc[d][k]<1 || nj+dc[d][k]>N) {
                                res += sand * per[k] / 100; // 격자 밖으로 나간 모래
                            } else map[ni+dr[d][k]][nj+dc[d][k]] += sand*per[k]/100; // 비율 적용하여 계산한다.
                            sum += sand * per[k] / 100;
                        }
                    }
                    r = ni; c = nj; // 이동한 좌표로 다음 연산에 사용할 행, 열 저장
                }
                d = d-1<0?3:d-1; // 방향 변경
            }
            length++;
        }
    }
}
