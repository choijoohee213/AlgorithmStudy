//https://www.acmicpc.net/problem/16948
//Solved : 22/03/02

import java.util.*;
import java.io.*;

class Main{
    static int[] dr = new int[]{-2, -2, 0, 0, 2, 2};
    static int[] dc = new int[]{-1, 1, -2, 2, 1, -1};
    static int N;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];
        st = new StringTokenizer(br.readLine());

        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        int rst = BFS(r1, c1, r2, c2);

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }

    static int BFS(int r1, int c1, int r2, int c2){
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{r1,c1,0});        //위치(r,c) 그리고 이동 횟수 배열
        visited[r1][c1] = true;
        while(!que.isEmpty()){
            int[] p = que.poll();
            for(int i=0; i<6; i++){
                if(p[0]+dr[i]>=N || p[0]+dr[i]<0 || p[1]+dc[i]>=N || p[1]+dc[i]<0 || visited[p[0]+dr[i]][p[1]+dc[i]]) continue;
                //배열 범위 밖이거나, 이미 방문한 곳이면 스킵
                if(p[0]+dr[i]==r2 && p[1]+dc[i]==c2) return p[2]+1; //목표 지점이면 반환 후 종료
                que.add(new int[]{p[0]+dr[i], p[1]+dc[i], p[2]+1});
                visited[p[0]+dr[i]][p[1]+dc[i]] = true;
            }
        }
        return -1;  //결국 목표지점에 못 도착하였으므로 -1 반환
    }
}