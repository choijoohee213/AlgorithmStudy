package com.younghwani.a220209;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
* 9*9 배열
*/
public class Main_bj_2580_스도쿠 {
    static int[][] arr = new int[9][9];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_2580.txt"));
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        recur(0, 0);
        sc.close();
    }

    // 2. 재귀적인 방식으로 숫자를 채워나가기
    // 조건(빈칸 모두 채웠을 때)에 만족하는 경우 출력 후 프로그램 종료
    //  ㄴ 행을 모두 옮기고, 열 또한 모두 옮겨졌으면 ((8, 8) 위치에 존재하면) 프로그램 종료
    // 행과 열이 0인 지점 -> (1)에서 설정한 숫자 대입 가능 여부 조사 -> 만족하는 숫자만 진행하도록 설정
    // 일단 열에 대해 진행 후 열의 모든 원소 채워지면 행을 이동한다.
    public static void recur(int row, int col) {
        if (col == 9) { // 한 행의 모든 열을 돌았으면 다음 행으로 이동
            recur(row+1, 0);
            return;
        }
        if (row == 9) { // 모두 돈 경우이므로 종료
            StringBuilder sb = new StringBuilder();
            for (int[] ia : arr) {
                for (int i : ia) {
                    sb.append(i).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb.toString());
            System.exit(0);
        }
        if (arr[row][col] == 0) { // 해당 위치의 값이 0인 경우(값 채워야하는 경우)
            for (int i = 1; i <= 9; i++) {
                if (isOk(row, col, i)) {
                    arr[row][col] = i;
                    recur(row, col+1);
                }
            }
            arr[row][col]=0;
            return;
        }
        recur(row, col+1);
    }

    // 1. 숫자를 넣을 수 있는지 여부 조사 필요 (숫자의 중복 체크)
    public static boolean isOk(int row, int col, int val) {
        // 열 방향에 대해
        for (int i = 0; i < 9; i++) {
            if (val == arr[i][col]) return false;
        }
        // 행 방향에 대해
        for (int i = 0; i < 9; i++) {
            if (val == arr[row][i]) return false;
        }
        // 3*3 박스에 대해
        int r = row/3 * 3; // 지역적 공간의 first row
        int c = col/3 * 3; // 지역적 공간의 first col
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[r+i][c+j]==val) return false;
            }
        }
        return true; // 모든 case에서 중복 없으면 true 출력
    }
}
