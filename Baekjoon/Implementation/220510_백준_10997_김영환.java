package com.younghwani.a220510;

import java.io.*;

class Main_bj_10997_별찍기22 {
    static int[] di={0, 1, 0, -1}, dj={-1, 0, 1, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N==1) { System.out.print("*"); return; }

        int R = 4*N-1, C = 4*N-3; // R: 7, 11, 15, ...; C: 5, 9, 13, ... 둘 다 4씩 증가하는 등차수열
        int[][] map = new int[R][C];

        int r = 0, c = C-1;
        map[r][c] = 1;

        loop:while (true) {
            for (int i = 0; i < 4; i++) {
                boolean flag = true;
                while (true) {
                    int ni = r+di[i], nj = c+dj[i]; // 이동할 좌표
                    if(ni<0||ni>=R||nj<0||nj>=C) break;
                    int ni2 = r+2*di[i], nj2 = c+2*dj[i];
                    if(ni2>=0&&ni2<R&&nj2>=0&&nj2<C&&map[ni2][nj2]==1) break; // 배열 범위 안이고, 이동할 곳 바로 옆에 * 이미 있으면 건너 뜀
                    r = ni; c = nj;
                    flag = false;
                    map[r][c] = 1;
                }
                if(flag) break loop;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            if(i==1) sb.append("*").append("\n");
            else {
                for (int j = 0; j < C; j++) sb.append(map[i][j]==1 ? "*" : " ");
                sb.append("\n");
            }
        }
        System.out.print(sb.toString());
        br.close();
    }
}

/*

>>>input
2
>>>result
*****
*
* ***
* * *
* * *
*   *
*****

*/
