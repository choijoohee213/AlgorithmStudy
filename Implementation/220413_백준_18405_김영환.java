package com.younghwani.a220413;

import java.io.*;
import java.util.*;

public class Main_bj_18405_경쟁적전염 {
    static class Point {
        int no, r, c;
        public Point(int no, int r, int c) {
            this.no = no;
            this.r = r;
            this.c = c;
        }
    }
    static final int di[]={-1,0,1,0}, dj[]={0,1,0,-1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int [][] map = new int[N][N];
        List<Point>[] points = new List[K+1];
        for (int i = 0; i <= K; i++) points[i] = new ArrayList<>();
        //Point[] points = new Point[K+1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]!=0) {
                    points[map[i][j]].add(new Point(map[i][j], i, j));
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken())-1;
        int Y = Integer.parseInt(st.nextToken())-1;

        ArrayDeque<Point> q = new ArrayDeque<>();
        for (int i = 1; i <= K; i++) {
            for (int j = 0; j < points[i].size(); j++) {
                q.offer(points[i].get(j));
            }
        }
        while (S-->0) {
            int size = q.size();
            while (size-->0) {
                Point cur = q.poll();
                for (int i = 0; i < 4; i++) {
                    int ni=cur.r+di[i], nj=cur.c+dj[i];
                    if(ni<0||ni>=N||nj<0||nj>=N||map[ni][nj]>0) continue;
                    map[ni][nj]=cur.no;
                    q.offer(new Point(cur.no, ni, nj));
                }
            }
        }

        System.out.println(map[X][Y]);
        br.close();
    }
}
