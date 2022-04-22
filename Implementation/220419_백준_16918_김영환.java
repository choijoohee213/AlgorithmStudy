package com.younghwani.a220419;

import java.io.*;
import java.util.*;
/*
봄버맨은 크기가 R×C인 직사각형 격자판 위에서 살고 있다. 격자의 각 칸은 비어있거나 폭탄이 들어있다.
폭탄이 있는 칸은 3초가 지난 후에 폭발하고, 폭탄이 폭발한 이후에는 폭탄이 있던 칸이 파괴되어 빈 칸이 되며, 인접한 네 칸도 함께 파괴된다.
즉, 폭탄이 있던 칸이 (i, j)인 경우에 (i+1, j), (i-1, j), (i, j+1), (i, j-1)도 함께 파괴된다.
만약, 폭탄이 폭발했을 때, 인접한 칸에 폭탄이 있는 경우에는 인접한 폭탄은 폭발 없이 파괴된다. 따라서, 연쇄 반응은 없다.

봄버맨은 폭탄에 면역력을 가지고 있어서, 격자판의 모든 칸을 자유롭게 이동할 수 있다. 봄버맨은 다음과 같이 행동한다.
    가장 처음에 봄버맨은 일부 칸에 폭탄을 설치해 놓는다. 모든 폭탄이 설치된 시간은 같다.
    다음 1초 동안 봄버맨은 아무것도 하지 않는다.
    다음 1초 동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치한다. 즉, 모든 칸은 폭탄을 가지고 있게 된다. 폭탄은 모두 동시에 설치했다고 가정한다.
    1초가 지난 후에 3초 전에 설치된 폭탄이 모두 폭발한다.
    3과 4를 반복한다.
폭탄을 설치해놓은 초기 상태가 주어졌을 때, N초가 흐른 후의 격자판 상태를 구하려고 한다.
 */
public class Main_bj_16918_봄버맨 {
    static class Node {
        int r, c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int R, C, N, map[][], di[]={-1, 0, 1, 0}, dj[]={0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken()); // 행 수
        C = Integer.parseInt(st.nextToken()); // 열 수
        N = Integer.parseInt(st.nextToken()); // 시간

        map = new int[R][C]; // 격자판

        for (int i = 0; i < R; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (tmp[j] == '.') { // 빈 칸
                    map[i][j] = 0;
                } else { // 폭탄 '0'
                    map[i][j] = 1;
                }
            }
        }

        Queue<Node> willBoom = new ArrayDeque<>();

        // 1~N 만큼을 시간으로 사용한다. 1의 경우, 이미 초기 상태가 있으니 건너뛴다.
        for (int i = 2; i <= N; i++) {
            if (i % 2 == 0) { // 빈칸에 폭탄을 설치한다.
                for (int r = 0; r < R; r++) {
                    for (int c = 0; c < C; c++) {
                        if(map[r][c]==0) map[r][c] = i; // 빈칸에 폭탄 설치(i 시간에 설치했음을 명시)
                    }
                }
            } else { // 아무 일도 벌어지지 않는 1초
                for (int r = 0; r < R; r++) {
                    for (int c = 0; c < C; c++) {
                        if(map[r][c] <= i-2) willBoom.offer(new Node(r, c)); // 폭탄의 시한이 다 되었을 때, 터질 폭탄 큐에 추가
                    }
                }

                while (!willBoom.isEmpty()) {
                    Node cur = willBoom.poll();
                    map[cur.r][cur.c] = 0; // 빈 칸으로
                    // 인접한 곳은 폭탄이 있든, 없든 빈 칸으로 만든다.(연쇄폭발은 없음)
                    for (int j = 0; j < 4; j++) {
                        int ni=cur.r+di[j], nj=cur.c+dj[j];
                        if(ni<0||ni>=R||nj<0||nj>=C) continue;
                        map[ni][nj]=0; // 빈 칸으로
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                sb.append(map[r][c]==0 ? '.' : 'O');
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }
}
