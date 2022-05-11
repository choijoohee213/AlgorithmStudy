package com.younghwani.a220313;

import java.io.*;
import java.util.*;

public class Main_bj_17140_이차원배열과연산 {
    static class NC implements Comparable<NC> {
        int num, cnt;
        NC(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
        @Override
        public int compareTo(NC o) {
            if(this.cnt>o.cnt) return 1;
            else if(this.cnt==o.cnt) return this.num-o.num;
            else return -1;
        }
    }
    static int R, C, K, A[][]=new int[101][101], x=3, y=3;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= 3; j++) A[i][j] = Integer.parseInt(st.nextToken());
        }
        for (int t = 0; t <= 100; t++) { // 100초간 반복
            if(A[R][C]==K) {System.out.println(t); return;} // 찾는 값과 일치 시 종료
            sorting(); // 찾는 값 아니면 다시 정렬
        }
        System.out.println(-1); // 값 못찾으면 -1 출력
        br.close();
    }
    static void sorting() { // 행, 열의 개수에 따른 정렬
        if(x>=y) for (int i = 1; i <= x; i++) row(i); // 행이 길면 행에 대한 정렬
        else for (int i = 1; i <= y; i++) col(i);     // 열이 길면 열에 대한 정렬
    }
    static void row(int i) { // 숫자 개수 구해서 map에 담기 -> 이후 pq 이용해 정렬
        PriorityQueue<NC> pq = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int j = 1; j <= y; j++) {
            if(A[i][j]==0) continue;
            map.compute(A[i][j], (num, cnt) -> cnt==null?1:cnt+1);
        }
        map.forEach((k, v)-> pq.offer(new NC(k, v)));
        int c=1;
        while (!pq.isEmpty()) {
            NC cur = pq.poll();
            A[i][c++] = cur.num;
            A[i][c++] = cur.cnt;
        }
        y = Math.max(y, c);
        while (c<=99) {
            A[i][c++]=0;
            A[i][c++]=0;
        }
    }
    static void col(int i) { // 숫자 개수 구해서 map에 담기 -> 이후 pq 이용해 정렬
        PriorityQueue<NC> pq = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int j = 1; j <= x; j++) {
            if(A[j][i]==0) continue;
            map.compute(A[j][i], (num, cnt) -> cnt==null?1:cnt+1);
        }
        map.forEach((k, v)-> pq.offer(new NC(k, v)));
        int r=1;
        while (!pq.isEmpty()) {
            NC cur = pq.poll();
            A[r++][i] = cur.num;
            A[r++][i] = cur.cnt;
        }
        x = Math.max(x, r);
        while (r<=99) {
            A[r++][i]=0;
            A[r++][i]=0;
        }
    }
}
