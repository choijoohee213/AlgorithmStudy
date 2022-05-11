package com.younghwani.a220510;
import java.io.*;
import java.util.*;
public class Main_bj_11967_불켜기 {
    static int N, cnt, di[] = {-1, 0, 1, 0}, dj[] = {0, 1, 0, -1};
    static boolean[][] V, on, canMove;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        // N × N개의 방이 있는 거대한 헛간
        V = new boolean[N][N];
        on = new boolean[N][N];
        canMove = new boolean[N][N];
        // 스위치 정보 입력받기, (x, y)방에서 (a, b)방의 불을 켜고 끌 수 있음, 한 방에 여러 스위치, 한 라이트를 조절하는 여러 스위치 가능
        List<int[]>[][] switchs = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                switchs[i][j] = new ArrayList<>(); // 리스트 배열 초기화
            }
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1; // (0, 0) 시작을 위해 -1
            int y = Integer.parseInt(st.nextToken())-1;
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            switchs[x][y].add(new int[]{a, b});
        }
        // 불 켤 수 있는 방 개수 탐색
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        V[0][0] = on[0][0] = true; // (1, 1)방에서 출발한다.
        cnt++;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            // 이동 가능 여부 탐색(상우하좌)
            for(int i = 0; i < 4; i++) {
                int ni = cur[0] + di[i], nj = cur[1] + dj[i];
                if(ni<0||ni>=N||nj<0||nj>=N) continue; // 범위 체크
                canMove[ni][nj] = true;
            }
            // 스위치 적용
            List<int[]> S = switchs[cur[0]][cur[1]]; // cur의 스위치 리스트
            for(int i = 0; i < S.size(); i++) {
                int[] s = S.get(i);
                if(!on[s[0]][s[1]]) { on[s[0]][s[1]] = true; cnt++; } // light off 상태(불키면 카운트 증가)
                if(canMove[s[0]][s[1]] && !V[s[0]][s[1]]) { // 이동 가능 및 방문 아직 안한 곳
                    V[s[0]][s[1]] = true; // 방문 처리
                    q.offer(new int[]{s[0], s[1]});
                }
            }
            // 이동가능하면 상우하좌 이동
            for(int i = 0; i < 4; i++) {
                int ni = cur[0] + di[i], nj = cur[1] + dj[i];
                if(ni<0||ni>=N||nj<0||nj>=N||!canMove[ni][nj]||!on[ni][nj]||V[ni][nj]) continue;//범위,이동,라이트온,방문
                V[ni][nj] = true; // 방문 처리
                q.offer(new int[]{ni, nj});
            }
        }
        // 불켜진 방 수 출력
        System.out.print(cnt);
        br.close();
    }
}
