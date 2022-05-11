package com.younghwani.a220426;
import java.io.*;
import java.util.*;
/*
3차원 미로(5*5*5)
상, 하, 좌, 우, 위, 아래
(0, 0, 0) -> (5, 5, 5) 과 같이 출입구는 면을 공유하지 않아야 한다.
0: 참가자 못 들어감, 1: 참가자 들어갈 수 있음
주어진 판을 시계, 반시계 방향으로 회전 가능
주어진 판을 쌓은 순서는 참가자 자유
가장 적은 이동 횟수로 탈출(탈출 불가 -1 출력)
 */
class Main_bj_16985_Maaaaze {
    static class Point {
        int h, r, c, cnt;
        public Point(int h, int r, int c, int cnt) {
            this.h = h;
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    static int[][][] map = new int[5][5][5];
    static int[] order = {0, 0, 0, 0, 0};
    static int[] di = {1, -1, 0, 0, 0, 0}, dj = {0, 0, 1, -1, 0, 0}, dh = {0, 0, 0, 0, 1, -1};
    static int res = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 3차원 맵의 정보를 받는다.
        for (int h = 0; h < 5; h++) {
            for (int i = 0; i < 5; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 5; j++) map[h][i][j] = Integer.parseInt(st.nextToken());
            }
        }
        perm(0, new boolean[5]);
        System.out.print(res==Integer.MAX_VALUE ? -1 : res); // 탈출 가능한 이동 횟수가 여전히 MAX_VALUE면 경로 없음.
        br.close();
    }
    static void perm(int cnt, boolean[] V) {
        if (cnt == 5) {
            int[][][] copied = new int[5][5][5];
            for (int h = 0; h < 5; h++) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        copied[h][i][j] = map[order[h]][i][j];
                    }
                }
            }
            dfs(0, new int[5][5][5], copied); // 조합을 통해 판이 쌓인 순서대로 dfs 적용
            return;
        }
        for (int i = 0; i < 5; i++) {
            if(V[i]) continue;
            V[i] = true; order[i] = cnt;
            perm(cnt+1, V);
            V[i] = false; order[i] = 0;
        }
    }
    static void dfs(int depth, int[][][] rot, int[][][] map) { // 각 층, 회전된 배열, 조합에서 넘어온 배열
        if (depth == 5) {
            if (rot[0][0][0]==1 && rot[4][4][4]==1) { // 시작과 끝점 모두 통과 가능해야 함.
                int cnt = bfs(rot);
                if (cnt!=-1) res=Math.min(res, cnt);
            }
            return;
        }
        for (int d = 0; d < 4; d++) { // 회전
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if(map[depth][i][j]!=1) continue; // 통과 못하는 경우는 넘어감
                    if     (d==0) rot[depth][j][4-i] = 1;
                    else if(d==1) rot[depth][4-i][4-j] = 1;
                    else if(d==2) rot[depth][4-j][i] = 1;
                    else if(d==3) rot[depth][i][j] = 1;
                }
            }
            dfs(depth+1, rot, map);
            rot[depth] = new int[5][5]; // dfs 다시 돌아오면 초기화
        }
    }
    static int bfs(int[][][] rot) { // 이동 가능한 최소 횟수 찾기
        Queue<Point> q = new LinkedList<>();
        boolean[][][] V = new boolean[5][5][5];
        q.offer(new Point(0, 0, 0, 0));
        while (!q.isEmpty()) {
            Point cur = q.poll();
            if (cur.h==4 && cur.r==4 && cur.c==4) return cur.cnt; // 도착 지점 도착 시
            if(V[cur.h][cur.r][cur.c]) continue;
            V[cur.h][cur.r][cur.c] = true;
            for (int d = 0; d < 6; d++) {
                int nh = cur.h+dh[d], ni = cur.r+di[d], nj = cur.c+dj[d];
                if(nh<0||nh>=5||ni<0||ni>=5||nj<0||nj>=5 || V[nh][ni][nj] || rot[nh][ni][nj]!=1) continue; // 범위 체크, 방문 여부 체크, 이동 가능여부 판단
                q.offer(new Point(nh, ni, nj, cur.cnt+1));
            }
        }
        return -1;
    }
}
