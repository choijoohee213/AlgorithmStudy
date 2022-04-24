package com.younghwani.a220421;
import java.io.*;
import java.util.*;

public class Main_bj_2933_미네랄 {
    static class Node {
        int r, c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int R, C, N, di[]={-1, 0, 1, 0}, dj[]={0, 1, 0, -1};
    static boolean[][] V;
    static char[][] map;
    static Queue<Node> q = new ArrayDeque<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char [R][C]; // 미네랄 정보
        // 정보 입력
        for (int i = 0; i < R; i++) map[i] = br.readLine().toCharArray();

        N = Integer.parseInt(br.readLine()); // 막대 던진 횟수
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) { // 막대를 던진 높이가 주어짐
            int row = R -Integer.parseInt(st.nextToken());
            broke(row,i); // 미네랄 파괴
            moveToBottom(); // 미네랄 클러스터가 공중에 떠있지 않도록 한다.
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) sb.append(map[i][j]);
            sb.append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }

    static void broke (int row, int i) {
        if(i % 2 == 0) { // 왼쪽부터 지우기 시작
            for (int j = 0; j < C; j++) {
                if(map[row][j] == 'x') { map[row][j] = '.'; break; } // 미네랄 찾으면 빈칸으로 설정함
            }
        } else { // 오른쪽부터 지우기 시작
            for (int j = C -1; j >= 0; j--) {
                if(map[row][j] == 'x') { map[row][j] = '.'; break; } // 미네랄 찾으면 빈칸으로 설정함
            }
        }
    }

    static void moveToBottom() {
        V = new boolean[R][C];
        // 땅에 인접한 미네랄 클러스터를 찾는다.(인접한 미네랄을 계속 탐색해 나간다.)
        for (int c = 0; c < C; c++) {
            if(map[R-1][c]=='.' || V[R-1][c]) continue;
            V[R-1][c] = true;
            q.add(new Node(R-1, c));
            while(!q.isEmpty()) {
                Node cur = q.poll();
                for (int i = 0; i < 4; i++) {
                    int ni = cur.r + di[i], nj = cur.c + dj[i];
                    if(ni<0||ni>=R||nj<0||nj>=C || V[ni][nj] || map[ni][nj]=='.') continue;
                    V[ni][nj] = true;
                    q.add(new Node(ni, nj));
                }
            }
        }
        // 공중에 있는 미네랄 클러스터 (위 작업에서 인접하다 탐색 안 된 경우)
        List<Node> MC = new ArrayList<>(); // 공중에 있는 미네랄 클러스터 정보
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(!V[i][j] && map[i][j]=='x') {
                    map[i][j] = '.';
                    MC.add(new Node(i, j));
                }
            }
        }
        if(MC.isEmpty()) return; // 공중에 있는 미네랄 클러스터 없으면 끝낸다.

        // 내리기
        loop: while(true) {
            for(Node node : MC) {
                int ni = node.r+1, nj = node.c;
                if(ni<0||ni>=R||nj<0||nj>=C || map[ni][nj]=='x') { // 범위 벗어나거나, 다른 미네랄 클러스터와 만나는 경우
                    break loop;
                }
            }
            for(Node node : MC) node.r++; // 내릴 수 있는 경우, 한 칸씩 내린다.
        }
        for(Node node : MC) map[node.r][node.c] = 'x'; // 계산한 새 좌표를 기준으로 클러스터를 맵에 반영해준다.
    }
}
