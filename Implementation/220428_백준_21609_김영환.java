package com.younghwani.a220508;
import java.io.*;
import java.util.*;
class Main_bj_21609_상어중학교 {
    static class Block implements Comparable<Block> {
        int r, c, cnt, rCnt;
        public Block(int r, int c, int cnt, int rCnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.rCnt = rCnt;
        }
        @Override
        public int compareTo(Block o) {
            if (this.cnt == o.cnt) {
                if (this.rCnt == o.rCnt) {
                    if (this.r == o.r) {
                        return o.c - this.c;
                    }
                    return o.r - this.r;
                }
                return o.rCnt - this.rCnt;
            }
            return o.cnt - this.cnt;
        }
    }
    static int N, M, r, c, cnt, rCnt, res, map[][], di[]={-1,0,1,0}, dj[]={0,1,0,-1};
    static boolean[][][] V;
    static List<Block> blocks;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) map[i][j] = Integer.parseInt(st.nextToken());
        }

        V = new boolean[6][N][N]; // M: 1~5
        blocks = new ArrayList<>();

        while (true) {
            // 최대가 되는 블록의 그룹을 찾는다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j]<=0 || V[map[i][j]][i][j]) continue; // 0보다 작거나, 방문한 경우
                    r = i; c = j;
                    cnt = 0; rCnt = 0;
                    dfs(i, j, map[i][j]); // 블록 그룹 찾기
                    if (cnt + rCnt >= 2) { // 그룹에 속한 블록수 최소 2
                        blocks.add(new Block(r, c, cnt, rCnt));
                    }
                }
            }
            if (blocks.isEmpty()) break; // 더 이상 블록 그룹이 존재하지 않는 경우
            // 크기가 가장 큰 블록 그룹을 찾는다.
            Collections.sort(blocks);
            Block big = blocks.get(0);
            res += Math.pow(big.cnt, 2); // (블록 그룹에 포함된 블록 수) ^ 2
            blocks.clear(); // 블록 그룹 정보 초기화
            // 찾은 big 그룹을 제거한다.
            int b = map[big.r][big.c];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    V[b][i][j] = false; // big 그룹 탐색 위해 V 값 변경
                }
            }
            dfs(big.r, big.c, b); // 삭제할 주변 블록을 찾는다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (V[b][i][j]) {
                        map[i][j] = -2; // big 그룹 map 에서 제거(최소 블록인 -1보다 작게 함)
                    }
                }
            }
            // 격자에 중력이 작용한다.
            gravity();
            // 격자가 90도 반시계 방향으로 회전한다.
            int[][] temp = new int[N][N];
            for(int i=0; i<N; i++) for(int j=0; j<N; j++) temp[N-j-1][i]=map[i][j]; // 회전
            for(int i=0; i<N; i++) for(int j=0; j<N; j++) map[i][j]=temp[i][j]; // 맵에 회전 적용
            // 다시 격자에 중력이 작용한다.
            gravity();
            // 방문 배열 초기화
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 1; k <= M; k++) {
                        V[k][i][j] = false;
                    }
                }
            }
        }

        System.out.print(res);
        br.close();
    }
    static void dfs(int x, int y, int b) {
        cnt++;
        V[b][x][y] = true;
        if (map[x][y] == 0) rCnt++;
        else { // 무지개가 아니면 행 번호 최소 -> 열 번호 최소
            if (x < r) { r = x; c = y; }
            else if (x == r&&y < c) { r = x; c = y; }
        }
        for (int d = 0; d < 4; d++) {
            int ni = x+di[d], nj = y+dj[d];
            if (ni<0||ni>=N||nj<0||nj>=N || map[ni][nj]<=-1 || V[b][ni][nj]) continue;
            if (map[ni][nj]==0 || map[ni][nj]==b) { // 같은 색이거나 무지개면 같은 그룹
                dfs(ni, nj, b);
            }
        }
    }
    static void gravity() {
        Queue<Integer> q = new ArrayDeque<>();
        for (int j = 0; j < N; j++) {
            int x = N-1; // 행 번호가 큰 칸부터 탐색
            for (int i = N-1; i >= 0; i--) {
                if (map[i][j] == -1) { // 검은색 블록은 제외
                    while (!q.isEmpty()) map[x--][j] = q.poll(); // 이동
                    while (x > i) map[x--][j] = -2; // 빈 칸 처리
                    x--;
                } else { // 검은색 아니면 이동할 q에 담는다.
                    if (map[i][j] != -2) q.offer(map[i][j]);
                }
            }
            while (!q.isEmpty()) map[x--][j] = q.poll(); // 남은 이동할 블록 처리
            while (x >= 0) map[x--][j] = -2; // 이동 후 남은 빈 칸 처리
        }
    }
}