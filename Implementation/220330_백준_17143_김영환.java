package com.younghwani.a220330;

import java.io.*;
import java.util.*;
import java.util.stream.*;
// 리펙터링에 너무 몰두하지 말자ㅜ 코드 블럭 잘못 지워서 틀렸음.
public class Main_bj_17143_낚시왕 {
    static class Shark implements Comparable<Shark> {
        int r, c, speed, d, size;
        public Shark(int r, int c, int speed, int d, int size) {
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.d = d;
            this.size = size;
        }
        public void changeDir() {
            if(this.d==0) this.d=1;
            else if(this.d==1) this.d=0;
            else if(this.d==2) this.d=3;
            else if(this.d==3) this.d=2;
        }
        @Override
        public int compareTo(Shark o) {
            return Integer.compare(this.r, o.r);
        }
    }
    static List<Shark> sharks = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            int[] in = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sharks.add(new Shark(in[0]-1, in[1]-1, in[2], in[3]-1, in[4]));
        }
        int res=0;
        int[] di={-1,1,0,0}, dj={0,0,1,-1}; // 상하우좌
        // 낚시왕의 이동
        for (int c = 0; c < C; c++) {
            // 상어 잡기
            Collections.sort(sharks);
            loop:for (int r = 0; r < R; r++) {
                for(Shark shark: sharks) {
                    if(shark.c==c&&shark.r==r) {
                        res += shark.size;
                        sharks.remove(shark);
                        break loop;
                    }
                }
            }
            // 상어 이동
            Shark[][] map = new Shark[R][C];
            int size = sharks.size();
            for (int i = 0; i < size; i++) {
                Shark shark = sharks.get(i);
                // 이동거리계산
                int dist = shark.speed;
                if(shark.d<=1) dist %= (R-1) * 2;   // 상하
                else dist %= (C-1) * 2;             // 좌우
                // 이동 거리만큼 새 위치 찾아 이동
                for (int s = 0; s < dist; s++) {
                    int ni=shark.r+di[shark.d], nj=shark.c+dj[shark.d];
                    if(ni<0||ni>=R||nj<0||nj>=C) { // 범위초과 시 위치 되돌려놓고, 방향 변경
                        shark.r -= di[shark.d];
                        shark.c -= dj[shark.d];
                        shark.changeDir();
                        continue;
                    }
                    shark.r = ni; shark.c = nj; // 새 위치 지정
                }
                // 여러 상어가 만나는 경우
                if(map[shark.r][shark.c]!=null) {
                    if (map[shark.r][shark.c].size < shark.size)
                        map[shark.r][shark.c] = shark;
                } else map[shark.r][shark.c] = shark;
            }
            // map에 저장된 shark를 sharks에 옮기기
            sharks.clear();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if(map[i][j]!=null) sharks.add(map[i][j]);
                }
            }
        }
        // 잡은 상어의 크기 합 출력
        System.out.print(res);
        br.close();
    }
}
