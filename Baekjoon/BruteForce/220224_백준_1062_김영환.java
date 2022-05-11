package com.younghwani.a220224;

import java.io.*;
import java.util.*;
/*
지구온난화로 학교가 무너지는데 학생들을 집에 안보낼 생각 안하고, K개의 글자나 가르친다는 김지민 선생.
어찌되었든 학생들은 K개의 글자는 배웠고, K개의 글자로만 이뤄진 단어는 읽을 수 있게 됨.
김지민 선생은 어떤 K개의 글자를 가르쳐야 학생들이 읽을 수 있는 단어가 최대가 되는가?
a n t i c 를 최소 글자로 포함해야 한다.(anta ~ tica)
 */
public class Main_bj_1062_가르침 {
    static int N, K, cnt;
    static String[] arr;
    static boolean[] V = new boolean[26];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if(K<5) { // anta, tica 조차도 못 읽을 때
            System.out.println(0);
            System.exit(0);
        }
        if(K==26) { // 모든 글자 배웠을 때
            System.out.println(N);
            System.exit(0);
        }
        arr = new String[N];
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            tmp = tmp.substring(4, tmp.length()-4);
            arr[i] = tmp;
        }
        // a c i n t
        V[0]=true; V[2]=true; V[8]=true; V[13]=true; V[19]=true;
        dfs(0, 0);
        System.out.println(cnt);
        br.close();
    }
    static void dfs(int depth, int n) {
        if(depth==K-5) {
            int tmpCnt = 0;
            for (int i = 0; i < N; i++) {
                char[] chars = arr[i].toCharArray(); // anta tica 사이 글자들
                int tmp = 0;
                for(char c: chars) {
                    if (!V[c-'a']) break; // 글자 못읽으면
                    tmp++;
                }
                if(tmp==chars.length) tmpCnt++; // anta tica 사이의 글자 모두 읽었을 때
            }
            if(cnt<tmpCnt) cnt=tmpCnt;
            return;
        }
        for (int i = n; i < 26; i++) { // 모든 글자 조합
            if(V[i]) continue;
            V[i] = true;
            dfs(depth + 1, i);
            V[i] = false;
        }
    }
}
