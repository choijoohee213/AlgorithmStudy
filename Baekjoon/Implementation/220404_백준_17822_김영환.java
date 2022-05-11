package com.younghwani.a220404;

import java.io.*;
import java.util.*;
public class Main_bj_17822_원판돌리기 {
    static int N, M, T, pan[][], res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        pan = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= M; j++) pan[i][j]=Integer.parseInt(st.nextToken());
        }
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            // 1. 회전
            for(int h=x; h<=N; h+=x) {
                int[] tmp = new int[M+1];
                if(d==0) { // 시계
                    for (int i = 1; i <= M; i++) {
                        tmp[(i - 1 + k) % M + 1] = pan[h][i];
                    }
                } else { // 반시계
                    for (int i = 1; i <= M; i++) {
                        tmp[(i - 1 - (k % M) + M) % M + 1] = pan[h][i];
                    }
                }
                pan[h]=tmp;
            }
            // 2. 인접하는 같은 수 찾아 처리
            // 좌, 우, 안쪽, 바깥쪽 인접한 곳 탐색 -> 같은 수 있다면 지우고, flag=false, 없다면 flag=true로 다음 단계 실행
            Set<int[]> set = new HashSet<>();
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if(pan[i][j]<=0) continue;
                    // 좌
                    if(pan[i][j-1>=1?j-1:M]==pan[i][j]) {
                        set.add(new int[]{i, j-1>=1?j-1:M});
                    }
                    // 우
                    if(pan[i][(j + 1) <= M ? j + 1 : 1]==pan[i][j]) {
                        set.add(new int[]{i, (j + 1) <= M ? j + 1 : 1});
                    }
                    // 안쪽
                    if(i-1>=1 && pan[i-1][j]==pan[i][j]) {
                        set.add(new int[]{i-1, j});
                    }
                    // 바깥쪽
                    if(i+1<=N && pan[i+1][j]==pan[i][j]) {
                        set.add(new int[]{i+1, j});
                    }
                }
            }
            // 같은 수가 없다면 평균을 구한 후, 각 원소들 비교해 큰 수면 1빼고, 작은 수면 1 더한다.
            if(set.size()==0) {
                int cnt=0, sum=0;
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= M; j++) {
                        if(pan[i][j]>0) {
                            cnt++;
                            sum+=pan[i][j];
                        }
                    }
                }
                double avg = sum*1.0 / cnt;
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= M; j++) {
                        if(pan[i][j]>0) {
                            if(pan[i][j] > avg) {
                                pan[i][j]--;
                            } else if(pan[i][j] < avg) {
                                pan[i][j]++;
                            }
                        }
                    }
                }
            } else { // set에 담긴 r,c 기준 0으로 변경
                Iterator<int[]> it = set.iterator();
                while (it.hasNext()) {
                    int[] tmps = it.next();
                    pan[tmps[0]][tmps[1]]=0;
                }
            }
        }
        // sum 계산
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                res += pan[i][j];
            }
        }
        System.out.print(res);
        br.close();
    }
}
