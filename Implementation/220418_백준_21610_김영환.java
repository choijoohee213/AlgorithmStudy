package com.younghwani.a220418;

import java.io.*;
import java.util.*;
/*
https://www.acmicpc.net/problem/21610
마법사 상어가 자꾸 뭘 배운다.....
이번엔 비구름을 만든다고 한다.
격자 각 칸에 바구니가 하나 있고, 바구니에 저장할 수 있는 물의 양에 제한은 없다.
배열은 마치 원형처럼 양 끝이 서로 이어져 있다고 한다.
비구름은 (좌, 좌상, 상, 우상, 우, 우하, 하, 좌하) 순서로 8방향으로 이동한다. (d방향으로 속력s만큼 이동)

모든 구름이 di 방향으로 si칸 이동한다.
각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
구름이 모두 사라진다.
2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.

M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 구해보자.

5 4
0 0 1 0 2
2 3 2 1 0
4 3 2 9 0
1 0 2 9 0
8 8 2 1 0
1 3
3 4
8 1
4 8
>>>77
 */
public class Main_bj_21610_마법사상어와비바라기 {
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, M, map[][], di[]={0,-1,-1,-1,0,1,1,1}, dj[]={-1,-1,0,1,1,1,0,-1};
    static boolean[][] V;
    static Queue<Point> q = new ArrayDeque<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 가로, 세로 길이
        M = Integer.parseInt(st.nextToken()); // 이동 정보 개수

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        // 초기 구름 위치 (비바라기를 시전하면 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름이 생긴다) - 배열 크기 고려 후, 큐에 추가
        q.offer(new Point(N - 1, 0));
        q.offer(new Point(N - 1, 1));
        q.offer(new Point(N - 2, 0));
        q.offer(new Point(N - 2, 1));

        // 이동 횟수 M 만큼 이동
        while (M-->0) {
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken()) -1;
            int s = Integer.parseInt(st.nextToken());

            V = new boolean[N][N];

            // 이동 (1. 모든 구름이 di 방향으로 si칸 이동한다.)
            for(Point p : q) {
                p.r = (N + p.r + di[d] * (s % N)) % N; // d 방향으로 s만큼 이동하고, 배열 범위 큐칙 적용
                p.c = (N + p.c + dj[d] * (s % N)) % N;
                map[p.r][p.c] += 1; // 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
            }

            // 3. 구름이 모두 사라진다.
            while (!q.isEmpty()) {
                Point cur = q.poll();
                V[cur.r][cur.c] = true; // 구름이 사라진 칸에서 새 구름이 생기지 않게 관리한다. (5번 조건)

                int b = 0; // 물이 있는 바구니의 수
                for (int i = 0; i < 4; i++) {
                    int idx = 2 * i + 1; // 대각선 인덱스
                    int ni = cur.r + di[idx], nj = cur.c + dj[idx]; // 대각선 좌표
                    // 4.1. 이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
                    if(ni<0||ni>=N||nj<0||nj>=N) continue;
                    if(map[ni][nj] > 0) b++; // 대각선 방향에 물이 있으니, 바구니 수 증가
                }
                // 4. 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
                map[cur.r][cur.c] += b;
            }

            // 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다.
            // 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다. (boolean 배열 V를 이용함.)
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(!V[i][j] && map[i][j]>=2) {
                        q.offer(new Point(i, j)); // 구름 생성
                        map[i][j] -= 2; // 물의 양 줄이기
                    }
                }
            }
        }

        // M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 출력한다.
        int res = 0;
        for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) res += map[i][j];
        System.out.print(res);
        br.close();
    }
}
