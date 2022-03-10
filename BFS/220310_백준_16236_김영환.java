package com.younghwani.a220216;

import java.io.*;
import java.util.*;

public class Main_16236_아기상어 {
    static final int[] di = new int[]{-1, 0, 0, 1};
    static final int[] dj = new int[]{0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_16236.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        int[] cur = null;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]==9) {
                    cur = new int[]{i, j};
                    arr[i][j]=0;
                }
            }
        }
        //System.out.println(Arrays.deepToString(arr));
        //System.out.println(Arrays.toString(cur));

        int size = 2; // 아기 상어 크기
        int eat = 0; // 먹이 먹은 개수
        int dist = 0; // 총 이동 거리

        // bfs
        while (true) {
            // 이동거리 정렬 -> y좌표 정렬 -> x좌표 정렬
            PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) ->
                    o1[2]!=o2[2] ? Integer.compare(o1[2], o2[2]) : (o1[0]!=o2[0] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]))
            );
            boolean[][] V = new boolean[N][N];
            queue.add(new int[]{cur[0], cur[1], 0}); // 행, 열, 이동거리
            V[cur[0]][cur[1]] = true;
            boolean isEat = false;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                int r = cur[0], c=cur[1], d=cur[2];
                // 먹이 먹을 수 있는 경우
                if (arr[r][c]!=0 && arr[r][c]<size) {
                    arr[r][c] = 0; // 먹이 먹음
                    eat++;
                    dist += d;
                    isEat = true;
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    int ni = r+di[i];
                    int nj = c+dj[i];
                    if (ni >= 0 && nj >= 0 && ni < N && nj < N && !V[ni][nj] && arr[ni][nj] <= size) {
                        queue.add(new int[]{ni, nj, d+1});
                        V[ni][nj]=true;
                    }
                }
            }
            // 더 이상 먹을 수 있는 물고기 없는 경우
            if(!isEat) break;
            // 상어 성장
            if(size==eat) { size++; eat=0; }
        }
        System.out.println(dist);
        br.close();
    }
}
