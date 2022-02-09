package com.younghwani.a220209;

import java.util.*;
import java.io.*;

public class Main_bj_6603_로또 {
    static int S;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_6603.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while(!(input=br.readLine()).equals("0")) {
            StringTokenizer st = new StringTokenizer(input);
            S = Integer.parseInt(st.nextToken());
            arr = new int[S];
            visited = new boolean[S];
            for (int i = 0; i < S; i++) arr[i] = Integer.parseInt(st.nextToken());
            dfs(0, 0);
            System.out.println();
        }
        br.close();
    }

    static void dfs(int depth, int n) {
        if(depth==6) {
            for (int i = 0; i < S; i++) {
                if (visited[i]) System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = n; i < S; i++) {
            visited[i] = true;
            dfs(depth+1, i+1);
            visited[i] = false;
        }
    }
}
