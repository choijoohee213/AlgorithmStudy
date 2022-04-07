package com.younghwani.a220406;

import java.io.*;
import java.util.*;
// 상어가 먹을 수 있는 물고기 번호의 최댓값 구하기
public class Main_bj_19236_청소년상어 {
    static int di[]={-1,-1,0,1,1,1,0,-1}, dj[]={0,-1,-1,-1,0,1,1,1}, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[4][4];
        List<Fish> fishes = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 4; j++) {
                int num=Integer.parseInt(st.nextToken());
                int dir=Integer.parseInt(st.nextToken()) - 1;
                fishes.add(new Fish(i, j, num, dir, true));
                map[i][j]=num;
            }
        }
        // 작은 순서부터 이동
        Collections.sort(fishes, new Comparator<Fish>() {
            @Override
            public int compare(Fish o1, Fish o2) {
                return o1.num-o2.num;
            }
        });
        // 0,0부터 상어 이동 시작
        Fish fish = fishes.get(map[0][0]-1);
        Shark shark = new Shark(0, 0, fish.dir, fish.num); // 0,0에서 시작하고, 물고기 방향을 가지며, 먹은 물고기 번호의 합
        fish.live=false;
        map[0][0]=-1; // 상어 위치 표시
        move(map, shark, fishes); // 물고기, 상어를 이동
        System.out.print(res);
        br.close();
    }
    static void move(int[][] map, Shark shark, List<Fish> fishes) {
        if(res<shark.eat) res=shark.eat; // 최대 물고기 숫자합 구하기

        // 물고기 이동
        loop:for(Fish fish: fishes) {
            if(!fish.live) continue;
            for (int i = 0; i < 8; i++) {
                int nd=(fish.dir+i)%8;
                int ni=fish.r+di[nd], nj=fish.c+dj[nd];
                if(ni<0||ni>=4||nj<0||nj>=4||map[ni][nj]<=-1) continue;
                map[fish.r][fish.c]=0;
                if (map[ni][nj]==0) { // 이동한 칸이 빈칸이라면
                    fish.r=ni;
                    fish.c=nj;
                } else { // 이동한 칸에 물고기가 있다면
                    // swap
                    Fish tmp = fishes.get(map[ni][nj]-1); // 원래 있던 물고기
                    tmp.r = fish.r;
                    tmp.c = fish.c;
                    map[fish.r][fish.c]=tmp.num;
                    // move
                    fish.r=ni;
                    fish.c=nj;
                }
                map[ni][nj]=fish.num;
                fish.dir=nd;
                continue loop;
            }
        }

        // 상어의 이동
        for (int len = 1; len < 4; len++) {
            int ni=shark.r+di[shark.dir]*len, nj= shark.c+dj[shark.dir]*len;
            if(ni<0||ni>=4||nj<0||nj>=4||map[ni][nj]<=0) continue;
            // deep copy를 통해 다음 이동(재귀)으로 값을 넘긴다. 그럼 재귀 끝나고 돌아왔을 때 값을 다시 변경해주지 않아도 된다.
            int[][] cpMap = new int[4][4];
            for (int i = 0; i < 4; i++) cpMap[i]=map[i].clone();
            List<Fish> cpFishes = new ArrayList<>();
            fishes.forEach(f -> cpFishes.add(new Fish(f.r, f.c, f.num, f.dir, f.live)));
            // 물고기 먹은 후 dfs
            cpMap[shark.r][shark.c]=0; // 현재 상어가 위치한 곳에 있는 물고기를 잡아먹는다. -> 이후 빈칸으로 처리
            Fish fish = cpFishes.get(map[ni][nj]-1); // 이동할 위치에 있는 물고기의 정보를 가져온다.
            Shark nShark = new Shark(fish.r, fish.c, fish.dir, shark.eat+fish.num); // 새로운 위치로 이동한 상어
            fish.live=false; // 먹힌 물고기 사망 처리
            cpMap[fish.r][fish.c]=-1; // 상어 위치 -1로 표시
            // 재귀
            move(cpMap, nShark, cpFishes);
        }
    }
    static class Shark {
        int r, c, dir, eat;
        public Shark(int r, int c, int dir, int eat) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.eat = eat;
        }
    }
    static class Fish {
        int r, c, num, dir;
        boolean live;
        public Fish(int r, int c, int num, int dir, boolean live) {
            this.r = r;
            this.c = c;
            this.num = num;
            this.dir = dir;
            this.live = live;
        }
    }
}

/*
7 6 2 3 15 6 9 8
3 1 1 8 14 7 10 1
6 1 13 6 4 3 11 4
16 1 8 7 5 2 12 2
>>>33
16 7 1 4 4 3 12 8
14 7 7 6 3 4 10 2
5 2 15 2 8 3 6 4
11 8 2 4 13 5 9 4
>>>43
12 6 14 5 4 5 6 7
15 1 11 7 3 7 7 5
10 3 8 3 16 6 1 1
5 8 2 7 13 6 9 2
>>>76
2 6 10 8 6 7 9 4
1 7 16 6 4 2 5 8
3 7 8 6 7 6 14 8
12 7 15 4 11 3 13 3
>>>39
 */