package com.younghwani.a220328;

import java.io.*;
import java.util.*;
import java.util.stream.*;
/*
K세대 드래곤커브는 K-1세대 드래곤커브의 끝 점을 기존으로 90도 시계방향 회전 후, 그 끝점에 붙인 것이다.
0: 우, 1: 상, 2: 좌, 3: 하
테스트케이스에서 주어지는 x, y는 열의 이동, 행의 이동이니 기존에 사용하던 di, dj 개념 사용을 위해 입력 순서 변경
 */
public class Main_bj_15685_드래곤커브 {
    static int SIZE=101, N, di[]={0,-1,0,1}, dj[]={1,0,-1,0};
    static boolean[][] map;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new boolean[SIZE][SIZE];
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int[] in = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); // y,x,d,g
            list=new ArrayList<>();
            list.add(in[2]);
            curve(in[3]);
            mapping(in[1], in[0]);
        }
        // 정사각형 개수세기
        int cnt=0;
        for(int i=0;i<SIZE-1;i++)for(int j=0;j<SIZE-1;j++)if(map[i][j]&&map[i][j+1]&&map[i+1][j]&&map[i+1][j+1])cnt++;
        System.out.print(cnt);
        br.close();
    }
    // 현재까지 그려진 것을 뒤에서부터 보며 방향을 계산한다.
    static void curve(int gen) {
        for (int g=0; g<gen; g++) {
            int size=list.size();
            for (int i=1; i<=size; i++) list.add((list.get(size-i)+1)%4);
        }
    }
    // map에 이동한 것을 체크한다.
    static void mapping(int r, int c) {
        int size=list.size(), ni=r, nj=c;
        map[r][c]=true;
        for (int i=0; i<size; i++) {
            int d=list.get(i);
            ni+=di[d]; nj+=dj[d];
            map[ni][nj]=true;
        }
    }
}
