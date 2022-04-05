package beak2239;
//https://www.acmicpc.net/problem/2239

import java.io.*;

public class Main {

    static int[][] sudoku = new int[9][9];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<9; i++){
            int num = Integer.parseInt(br.readLine());
            for(int j=8; j>=0; j--){
                sudoku[i][j] = num%10;
                num = num/10;
            }
        }
        int[] now = next_zero(0, -1);
        make_sudoku(now[0], now[1]);
        print_sudoku();
    }

    static boolean make_sudoku(int now_row, int now_col){
        if(now_row==9){
            return true;
        }
        int[] next = next_zero(now_row, now_col);
        boolean[] chk = new boolean[10];                        //0이 없는거 1있는거
        chk = chk_row(now_row, chk);
        chk = chk_col(now_col, chk);
        chk = chk_square(now_row, now_col, chk);
        for(int i=1; i<=9; i++) {
            if(!chk[i]){
                sudoku[now_row][now_col] = i;
                boolean next_sudoku = make_sudoku(next[0], next[1]);
                if(next_sudoku){
                    return true;
                }
                sudoku[now_row][now_col] = 0;
            }
        }
        return false;
    }
    static boolean[] chk_row(int now_row, boolean[] chk){
        for(int i=0; i<9; i++){
            chk[sudoku[now_row][i]] = true;
        }
        return chk;
    }

    static boolean[] chk_col(int now_col, boolean[] chk){
        for(int i=0; i<9; i++){
            chk[sudoku[i][now_col]] = true;
        }
        return chk;
    }

    static boolean[] chk_square(int now_row, int now_col, boolean[] chk){
        now_col = (now_col/3)*3;
        now_row = (now_row/3)*3;
        for(int i=now_row; i<now_row+3; i++){
            for(int j=now_col; j<now_col+3; j++) {
                chk[sudoku[i][j]] = true;
                //System.out.print(sudoku[i][j]);
            }
        }
        return chk;
    }

    static int[] next_zero(int now_row, int now_col){
        int[] next = new int[2];
        for(int i = now_row; i<9; i++){
            if(i==now_row) {
                for (int j = now_col+1; j < 9; j++) {
                    if (sudoku[i][j] == 0) {
                        next[0] = i;
                        next[1] = j;
                        return next;
                    }
                }
            }else{
                for (int j = 0; j < 9; j++) {
                    if (sudoku[i][j] == 0) {
                        next[0] = i;
                        next[1] = j;
                        return next;
                    }
                }
            }
        }
        next[0] = 9;
        next[1] = 9;
        return next;
    }
    static void print_sudoku(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                sb.append(sudoku[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
/*
103000509
002109400
000704000
300502006
060000050
700803004
000401000
009205800
804000107

 */