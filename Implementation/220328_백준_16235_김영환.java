package com.younghwani.a220328;

import java.io.*;
import java.util.*;
/*
입출력 순서를 활용해 문제를 풀었음에도, PriorityQueue를 사용해 문제를 겪은 문제.
 */
public class Main_bj_16235_나무재테크 {
    static int N, M, K, A[][], map[][], di[]={-1,-1,-1,0,0,1,1,1}, dj[]={-1,0,1,-1,1,-1,0,1};
    static Queue<Tree> trees = new LinkedList<>();
    static class Tree implements Comparable<Tree>{
        int x, y, age;
        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
        @Override
        public int compareTo(Tree o) {
            return this.age-o.age;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N+1][N+1]; // 겨울에 추가되는 양분의 양
        map = new int[N+1][N+1]; // 땅
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                map[i][j]=5;
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            trees.offer(new Tree(x, y, age));
        }
        Collections.sort((List<Tree>) trees); // 큐를 List로 캐스팅하여 정렬
        while (K-->0) {
            ArrayList<Tree> dead = spring();
            summer(dead);
            fall();
            winter();
        }
        System.out.print(trees.size()); // 나무의 개수를 출력
        br.close();
    }
    // 봄에는 자신의 나이만큼 양분을 먹는다. 나이가 1 증가한다. 여러 나무가 있다면 어린 나무부터 양분을 먹는다. 양분 부족시 못먹고 즉사.
    static ArrayList<Tree> spring() {
        ArrayList<Tree> dead = new ArrayList<>();
        int size=trees.size();
        while (size-->0) {
            Tree cur = trees.poll();
            if(cur.age > map[cur.x][cur.y]) {
                dead.add(cur);
            } else {
                map[cur.x][cur.y] -= cur.age;
                trees.add(new Tree(cur.x, cur.y, cur.age + 1));
            }
        }
        return dead;
    }
    // 봄에 즉사한 나무가 양분이 된다. 나이/2만큼이 양분으로 증가된다.
    static void summer(ArrayList<Tree> dead) {
        for (Tree tree: dead) {
            map[tree.x][tree.y] += tree.age/2;
        }
    }
    // 번식. 나무의 나이가 5의 배수일 때, 8방 인접 칸에 나이 1인 나무가 생긴다.
    static void fall() {
        ArrayList<Tree> tmp = new ArrayList<>();
        int size=trees.size();
        while (size-->0) { // 이러한 부분에서 순서대로 빼서 사용해야 하나, PriorityQueue를 잘못 사용해 반환 순서에 오류를 겪음. - 향후 Deque을 이용해 age1인 새로운 나무를 앞에 추가해도 좋을 듯?
            Tree cur = trees.poll();
            if(cur.age%5==0) {
                for (int i = 0; i < 8; i++) {
                    int ni=cur.x+di[i], nj=cur.y+dj[i];
                    if(ni<1||ni>N||nj<1||nj>N) continue;
                    trees.add(new Tree(ni, nj, 1));
                }
            }
            tmp.add(cur);
        }
        for (Tree tree: tmp) {
            trees.add(tree);
        }
    }
    // A배열 만큼 땅에 양분을 추가한다.
    static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j]+=A[i][j];
            }
        }
    }
}
