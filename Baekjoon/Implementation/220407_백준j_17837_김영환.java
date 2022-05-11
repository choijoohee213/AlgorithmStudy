package com.younghwani.a220407;

import java.io.*;
import java.util.*;
// 방향을 바꿔주는 공식으로 (d + 2) % 4를 사용했기에, 이동 편차 di, dj를 이에 맞춰 구성한다.
public class Main_bj_17837_새로운게임2 {
    static int N, K, C[][], H[][], di[]={0,1,0,-1}, dj[]={1,0,-1,0};
    static LinkedList<Integer>[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // 말의 개수
        C = new int[N][N]; // 색상 정보
        H = new int[K][3]; // 말의 정보
        map = new LinkedList[N][N];
        for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) map[i][j] = new LinkedList<>();
        // input
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                C[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            H[i][0] = Integer.parseInt(st.nextToken()) - 1; // row
            H[i][1] = Integer.parseInt(st.nextToken()) - 1; // col
            H[i][2] = Integer.parseInt(st.nextToken()); // dir
            if(H[i][2]==1) H[i][2] = 0;
            else if(H[i][2]==4) H[i][2] = 1;
            map[H[i][0]][H[i][1]].add(i);
        }

        for (int t = 1; t <= 1000; t++) { // 1000보다 크면 종료
            // 말을 순서대로 이동시킨다.
            for (int i = 0; i < K; i++) {
                int r=H[i][0], c=H[i][1], d=H[i][2];
                // 쌓인 말 중에서 몇 번째인지 찾는다.
                int num = -1;
                for (int j = 0; j < map[r][c].size(); j++) {
                    if(map[r][c].get(j) == i) num = j;
                }
                // 말을 이동한다.
                int ni=r+di[d], nj=c+dj[d];
                if(ni<0||ni>=N||nj<0||nj>=N||C[ni][nj]==2) { // 안되는 경우, 파란색
                    // 말의 방향을 바꾼다.
                    H[i][2] = d = (d + 2) % 4;
                    ni = r + di[d]; nj = c + dj[d]; // 위치 반대방향으로
                    if(ni<0||ni>=N||nj<0||nj>=N||C[ni][nj]==2) continue;
                }
                // 새로운 좌표로 이동시킨다.
                while (map[r][c].size() > num) { // 새 위치로 이동이 끝날 때까지
                    int cur = -1;
                    if(C[ni][nj]==0) { // 흰색 - 이미 말이 쌓인 경우 가장 위에 놓는다.
                        cur = map[r][c].remove(num);
                    } else { // 빨간색 - 모든 말의 쌓인 순서 반대로
                        cur = map[r][c].removeLast();
                    }
                    H[cur][0]=ni;
                    H[cur][1]=nj;
                    map[ni][nj].add(cur);
                }
                if (map[ni][nj].size() >= 4) {
                    System.out.print(t);
                    return;
                }
            }
        }

        System.out.print(-1);
        br.close();
    }
}
