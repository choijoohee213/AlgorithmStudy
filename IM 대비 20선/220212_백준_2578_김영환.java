package com.younghwani.a220212;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main_bj_2578_빙고 {
    static int cnt=0;
    static int[][] pan = new int[5][5];
    static boolean[][] checked = new boolean[5][5];
    static List<Integer> call = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_2578.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) pan[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //System.out.println(Arrays.deepToString(pan));
        for (int i = 0; i < 5; i++) Stream.of(br.readLine().split(" ")).forEach(n->call.add(Integer.parseInt(n)));
        //System.out.println(call.toString());
        for (int i: call) {
            cnt++;
            if(isBingo(i)) break;
        }
        System.out.println(cnt);
        br.close();
    }

    static boolean isBingo(int num) {
        // 현재 체크한 숫자 찾기
        loop:for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (pan[i][j] == num) {
                    checked[i][j] = true;
                    break loop;
                }
            }
        }
        // 빙고 여부 확인하기
        if (cnt >= 5) { // 빙고 외치려면 최소 5개 이상의 숫자는 받아야 한다.
            int tmpCnt = 0;
            for (int i = 0; i < 5; i++) {
                int r_ok = 0; int c_ok = 0;
                for (int j = 0; j < 5; j++) if(checked[i][j]) r_ok++; // 해당하는 row 탐색
                for (int j = 0; j < 5; j++) if(checked[j][i]) c_ok++; // 해당하는 col 탐색
                if (r_ok==5) tmpCnt++;
                if (c_ok==5) tmpCnt++;
            }
            if (tmpCnt>=3) return true;
            // row, col 이 가운데(2)인 대각선 체크도 필요(X 형태)
            int ltr_ok=0; int rtl_ok=0;
            for (int i = 0; i < 5; i++) {
                if(checked[i][i]) ltr_ok++;
                if(checked[i][4-i]) rtl_ok++;
            }
            if (ltr_ok==5) tmpCnt++;
            if (rtl_ok==5) tmpCnt++;
            if (tmpCnt>=3) return true;
        }
        return false;
    }
}
