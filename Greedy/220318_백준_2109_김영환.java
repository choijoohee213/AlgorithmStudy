package com.younghwani.a220318;

import java.io.*;
import java.util.*;

public class Main_bj_2109_순회강연 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, ((o1, o2) -> o1[0] - o2[0] == 0 ? o2[1] - o1[1] : o2[0] - o1[0]));

        int[] dp = new int[10001];
        for (int i = 0; i < N; i++) {
            for (int j = arr[i][1]; j >= 1; j--) {
                if (dp[j]==0) {
                    dp[j]=arr[i][0];
                    break;
                }
            }
        }

        int total = 0;
        for(int t: dp) total += t;

        System.out.println(total);
        br.close();
    }
}
// Stream.of가 메모리를 많이 잡아먹나 봅니다. 내부에서 split을 이용한 것도 한 몫 한 것 같습니다.
// StringTokenizer를 사용한 로직으로 변경 시, 메모리 초과 해결됨.
/*
// 메모리 초과
import java.io.*;
import java.util.*;
import java.util.stream.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) arr[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr, ((o1, o2) -> o1[0] - o2[0] == 0 ? o2[1] - o1[1] : o2[0] - o1[0]));
        int[] dp = new int[10001];
        for (int i = 0; i < N; i++) {
            for (int j = arr[i][1]; j >= 1; j--) {
                if (dp[j]==0) {
                    dp[j]=arr[i][0];
                    break;
                }
            }
        }
        int total = 0;
        for(int t: dp) total += t;
        System.out.println(total);
        br.close();
    }
}
 */