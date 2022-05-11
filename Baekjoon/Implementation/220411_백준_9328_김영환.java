package com.younghwani.a220411;

import java.io.*;
import java.util.*;
/*
열 수 없는 문을 리스트로 관리한다. -> 키를 비트마스크로 이용하면, 키 int 값이 달라졌을 때, 다시 bfs 방문하도록 할 수 있을 것 같음.
주운 열쇠를 set으로 관리한다. -> 주말에 공부한 비트마스크를 이용해보자?
문을 만났을 때, 열쇠 있으면 열고, 없으면 열수 없는 문 리스트에 추가만 해둔다.
열쇠를 주웠는데, 열 수 없는 문 리스트에 짝이 맞는 문이 있다면 그 위치로 이동해 문을 연다.
3
5 17
*****************
.............**$*
*B*A*P*C**X*Y*.X.
*y*x*a*p**$*$**$*
*****************
cz
5 11
*.*********
*...*...*x*
*X*.*.*.*.*
*$*...*...*
***********
0
7 7
*ABCDE*
X.....F
W.$$$.G
V.$$$.H
U.$$$.J
T.....K
*SQPML*
irony
>>>3
1
0
 */
public class Main_bj_9328_열쇠 {
    static int H, W, keySet, res, V[][], di[]={-1,0,1,0}, dj[]={0,1,0,-1};
    static char[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            // 지도 입력받기
            map = new char[H+2][W+2]; // 지도
            for (int i = 1; i <= H; i++) {
                char[] c = br.readLine().toCharArray();
                for (int j = 1; j <= W; j++) {
                    map[i][j] = c[j - 1];
                }
            }

            // 초기 열쇠
            keySet = 0;
            String initial = br.readLine();
            if (!initial.equals("0")) { // 0이면 초기 열쇠 없이 시작
                char[] c = initial.toCharArray();
                for (int i = 0; i < c.length; i++) {
                    keySet |= (1 << (c[i] - 'a')); // 열쇠 비트 정보 추가
                }
            }

            // 방문 배열 초기화
            V = new int[H+2][W+2];    // 방문 여부(keySet 값으로 파악)
            for (int i = 0; i < H + 2; i++) {
                for (int j = 0; j < W + 2; j++) {
                    V[i][j] = Integer.MIN_VALUE;
                }
            }

            res = 0;
            bfs();
            sb.append(res).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }
    static void bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        V[0][0] = keySet; // (0, 0) 부터 시작
        q.offer(new int[]{0, 0});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int ni = cur[0] + di[i], nj = cur[1] + dj[i];
                if(ni<0||ni>=H+2||nj<0||nj>=W+2||map[ni][nj]=='*'||V[ni][nj]==keySet) continue;
                V[ni][nj] = keySet; // 방문 여부 표시 -> 이후 다른 keySet이 V에 들어있다면 다시 bfs 순회할 것
                char point = map[ni][nj]; // 위치할 곳의 char 값
                // 문을 만나는 경우
                if(point>='A'&&point<='Z') {
                    // 문을 열면 빈칸으로 -> 아래에서 실행
                    // 열 수 없으면 넘어감(ex, 1111 & 0100 => 0이 나온다는 것은 겹치지 않는다는 것 -> 알맞는 열쇠 없음)
                    if((keySet & (1<<(point-'A'))) == 0) continue;
                }
                // 열쇠를 만나는 경우
                if(point>='a'&&point<='z') {
                    keySet |= (1<<(point-'a')); // keySet 값 업데이트
                }
                // 문서를 만나는 경우
                if(point=='$') res++;

                map[ni][nj] = '.'; // 처리 끝났으면 빈칸으로
                q.offer(new int[]{ni, nj});
            }
        }
    }
}


/* 잘 안됨..... 틀린게 없는 것 같은데....
// V[ni][nj]==keySet -> 초기값이 없을 때 V[ni][nj]는 0이고, keySet 또한 0이라 비교 시 문제가 될 수 있음을 간과했다.
public class Main_bj_9328_열쇠 {
    static int H, W, keySet, res, V[][], di[]={-1,0,1,0}, dj[]={0,1,0,-1};
    static char[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new char[H+2][W+2]; // 지도
            V = new int[H+2][W+2];    // 방문 여부(keySet 값으로 파악)

            // 지도 입력받기
            for (int i = 1; i <= H; i++) {
                char[] c = br.readLine().toCharArray();
                for (int j = 1; j <= W; j++) {
                    map[i][j] = c[j - 1];
                }
            }

            // 초기 열쇠
            keySet = 0;
            String initial = br.readLine();
            if (!initial.equals("0")) { // 0이면 초기 열쇠 없이 시작
                char[] c = initial.toCharArray();
                for (int i = 0; i < c.length; i++) {
                    keySet |= (1 << (c[i] - 'a')); // 열쇠 비트 정보 추가
                }
            }

            res = 0;
            bfs();
            sb.append(res).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }
    static void bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        V[0][0] = keySet; // (0, 0) 부터 시작
        q.offer(new int[]{0, 0});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int ni = cur[0] + di[i], nj = cur[1] + dj[i];
                if(ni<0||ni>=H+2||nj<0||nj>=W+2||map[ni][nj]=='*'||V[ni][nj]==keySet) continue;
                V[ni][nj] = keySet; // 방문 여부 표시 -> 이후 다른 keySet이 V에 들어있다면 다시 bfs 순회할 것
                char point = map[ni][nj]; // 위치할 곳의 char 값
                // 문을 만나는 경우
                if(point>='A'&&point<='Z') {
                    // 문을 열면 빈칸으로 -> 아래에서 실행
                    // 열 수 없으면 넘어감(ex, 1111 & 0100 => 0이 나온다는 것은 겹치지 않는다는 것 -> 알맞는 열쇠 없음)
                    if((keySet & (1<<(point-'A'))) == 0) continue;
                }
                // 열쇠를 만나는 경우
                if(point>='a'&&point<='z') {
                    keySet |= (1<<(point-'a')); // keySet 값 업데이트
                }
                // 문서를 만나는 경우
                if(point=='$') res++;

                map[ni][nj] = '.'; // 처리 끝났으면 빈칸으로
                q.offer(new int[]{ni, nj});
            }
        }
    }
}
 */