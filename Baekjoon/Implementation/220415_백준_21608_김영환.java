package com.younghwani.a220417;

import java.io.*;
import java.util.*;

public class Main_bj_21608_상어초등학교 {
    static class Seat implements Comparable<Seat> {
        int r, c, like, empty;
        public Seat(int r, int c, int like, int empty) {
            this.r = r;
            this.c = c;
            this.like = like;
            this.empty = empty;
        }
        @Override
        public int compareTo(Seat o) { // 좋아하는 학생 -> 빈 칸 -> 행 -> 열
            return Integer.compare(o.like, this.like) != 0 ?
                    Integer.compare(o.like, this.like) :
                    Integer.compare(o.empty, this.empty) != 0 ?
                    Integer.compare(o.empty, this.empty) :
                    Integer.compare(this.r, o.r) != 0 ?
                    Integer.compare(this.r, o.r) :
                    Integer.compare(this.c, o.c);
        }
    }

    static int N, map[][], stud[][], di[]={-1, 1, 0, 0}, dj[]={0, 0, -1, 1}; // 입력 값의 순서, 상하좌우
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        stud = new int[N*N+1][4];

        // 학생 번호와 그 학생이 좋아하는 학생 4명의 번호를 입력받는다.
        for (int i = 0; i < N*N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) stud[num][j] = Integer.parseInt(st.nextToken());
            seating(num);
        }

        // 학생의 만족도를 구한다. 인접한 칸의 좋아하는 학생 수를 기준으로 한다.(0, 1, 10, 100, 1000)
        int res = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int satis = 0;
                for (int k = 0; k < 4; k++) {
                    int ni = i+di[k], nj = j+dj[k];
                    if(ni<1||ni>N||nj<1||nj>N) continue;
                    for (int l = 0; l < 4; l++) if(stud[map[i][j]][l] == map[ni][nj]) satis++;
                }
                if(satis>0) res += Math.pow(10, satis - 1);
            }
        }

        System.out.print(res);
        br.close();
    }

    static void seating(int num) {
        List<Seat> list = new ArrayList<>();
        // 위치 별, 학생 번호의 인접한 좋아하는 학생 수, 빈칸 수를 확인한다.
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(map[i][j]!=0) continue; // 빈 칸일 때, 새로운 자리 계산한다.
                int like = 0, empty = 0;
                for (int k = 0; k < 4; k++) {
                    int ni = i+di[k], nj = j+dj[k];
                    if(ni<1||ni>N||nj<1||nj>N) continue;
                    boolean flag = false;
                    for (int l = 0; l < 4; l++) if(stud[num][l] == map[ni][nj]) flag=true;
                    if(flag) like++;
                    if(map[ni][nj] == 0) empty++;
                }
                list.add(new Seat(i, j, like, empty));
            }
        }
        Collections.sort(list);
        Seat s = list.get(0);
        map[s.r][s.c] = num;
    }
}
