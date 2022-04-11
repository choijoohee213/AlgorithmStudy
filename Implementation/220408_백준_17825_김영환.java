package com.younghwani.a220408;

import java.io.*;
import java.util.*;
/*
4개의 말
파란 칸에서 이동하면 파란 화살표, 아니면 빨간 화살표
10개의 턴
이동 종료 칸에 다른 말 있으면 그 말을 못 고름
이동 마칠 때마다 칸에 적힌 수가 점수에 추가됨
 */
public class Main_bj_17825_주사위윷놀이 {
    static class Node {
        int score, blue, red;
        boolean isBlue = false;
        public Node(int score, int red) {
            this.score = score;
            this.red = red;
        }
    }
    static int max, perm[], step[], now[];
    static boolean[] exist;
    static Node[] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new Node[43]; // 도착점까지 포함해서 총 43개의 이동 칸 존재
        perm = new int[10]; // 각 턴별 주사위 수
        step = new int[10]; // 단계(turn)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 10; i++) step[i] = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= 40; i += 2) map[i] = new Node(i, i + 2); // 빨간 화살표 지정 방향
        map[10].isBlue = map[20].isBlue = map[30].isBlue = true;
        map[10].blue = 11;
        map[20].blue = 17;
        map[30].blue = 31;
        // 문제상 score와 풀이상 노드 번호 매핑
        map[11] = new Node(13, 13);
        map[13] = new Node(16, 15);
        map[15] = new Node(19, 25);
        map[17] = new Node(22, 19);
        map[19] = new Node(24, 25);
        map[25] = new Node(25, 37);
        map[31] = new Node(28, 33);
        map[33] = new Node(27, 35);
        map[35] = new Node(26, 25);
        map[37] = new Node(30, 39);
        map[39] = new Node(35, 40);
        map[42] = new Node(0, 42);

        perm[0] = 0;
        permutation(0);
        System.out.print(max);
        br.close();
    }

    static void permutation(int cnt) {
        if (cnt == 9) {
            now = new int[4]; // 말은 4개
            exist = new boolean[43];
            int score = 0;
            for (int i = 0; i < 10; i++) {
                int end = move(perm[i], step[i]);
                if (end == -1) return;
                now[perm[i]] = end;
                score += map[end].score;
            }
            if(max<score) max = score;
            return;
        }
        for (int i = 0; i < 4; i++) {
            perm[cnt] = i;
            permutation(cnt + 1);
        }
    }

    static int move(int horse, int step) {
        int tmp = now[horse]; // 이동할 말
        for (int i = 0; i < step; i++) {
            if(i==0 && map[tmp].isBlue) tmp = map[tmp].blue; // 파란색 화살표
            else tmp = map[tmp].red; // 빨간색 화살표
        }
        if (tmp <= 40 && exist[tmp]) { // 이동할 곳에 이미 말 있을 때, 도착점도 아닐 때
            return -1;
        } else { // 말의 이동
            exist[now[horse]] = false; // 이전 위치
            exist[tmp] = true; // 이동할 위치
            return tmp;
        }
    }
}
