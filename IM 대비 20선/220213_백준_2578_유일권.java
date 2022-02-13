package d220213.q2578;
//https://www.acmicpc.net/problem/2578
//solved ; 22/02/13
import java.util.*;
import java.io.*;

public class Main {
    static int[][] bingo;
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bingo = new int[5][5];
        StringTokenizer st;
        int rst=0;

        for(int i=0; i<5; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++){
                bingo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        loop:for(int i=0; i<5; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++){
                int tmp = Integer.parseInt(st.nextToken());
//                System.out.println(tmp);
                if(chk_bingo(tmp)){
                    rst = i*5+j+1;
                    break loop;
                }
//                for(int k=0; k<5; k++){
//                    System.out.println(Arrays.toString(bingo[k]));
//                }
//                System.out.println();
            }
        }
//        for(int k=0; k<5; k++){
//            System.out.println(Arrays.toString(bingo[k]));
//        }

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }

    static boolean chk_bingo(int num){
        int r=0, c=0;
        loop:for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(bingo[i][j]==num){
                    r=i;
                    c=j;
                    bingo[i][j] = 0;
                    break loop;
                }
            }
        }
        rst = rst + chk_row(r) + chk_col(c) + chk_diag(r, c);
//        System.out.print("num = "+num);
//        System.out.println(" rst = "+rst);
//        System.out.println("row "+chk_row(r)+" col " + chk_col(c)+" diag " + chk_diag(r,c));
        return rst >= 3;
    }
    static int chk_row(int row){
        for(int i=0; i<5; i++){
            if(bingo[row][i] != 0) return 0;
        }
        return 1;
    }
    static int chk_col(int col){
        for(int i=0; i<5; i++){
            if(bingo[i][col] != 0) return 0;
        }
        return 1;
    }
    static int chk_diag(int row, int col){
        int rst = 0;
        if(row-col==0){
            rst++;
            for(int i =0; i<5; i++){
                if(bingo[i][i]!=0){
                    rst--;
                    break;
                }
            }
        }if(row+col==4){
            rst++;
            for(int i =0; i<5; i++){
                if(bingo[i][4-i]!=0){
                    rst--;
                    break;
                }
            }
        }if(row+col!=4&&row-col!=0){
            rst = 0;
        }
        return rst;
    }
}
/*

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {


    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        Map<String, int[]> board = new HashMap<>();
        final int BOARD_SIZE = 5;
        StringTokenizer st;
        for(int i = 0; i < BOARD_SIZE; i++){
            st = new StringTokenizer(input.readLine());
            for(int j = 0; j < BOARD_SIZE; j++){
                board.put(st.nextToken(), new int[]{i, j});
            }
        }

        int[] rowCnt = new int[BOARD_SIZE];
        int[] colCnt = new int[BOARD_SIZE];
        int leftCross = 0;
        int rightCross = 0;

        int ans = 0;
        int bingo = 0;
        out: for(int i = 0; i < BOARD_SIZE; i++){
            st = new StringTokenizer(input.readLine());
            for(int j = 0; j < BOARD_SIZE; j++){
                int[] loc = board.get(st.nextToken());
                if(++rowCnt[loc[0]] == BOARD_SIZE)
                    bingo++;
                if(++colCnt[loc[1]] == BOARD_SIZE)
                    bingo++;
                if(loc[0] + loc[1] == BOARD_SIZE-1){
                    if(++rightCross == BOARD_SIZE)
                        bingo++;
                }
                if(loc[0] == loc[1]){
                    if(++leftCross == BOARD_SIZE)
                        bingo++;
                }
                if(bingo >= 3){
                    ans = j + BOARD_SIZE * i + 1;
                    break out;
                }
            }
        }

        System.out.println(ans);
    }
}

 */