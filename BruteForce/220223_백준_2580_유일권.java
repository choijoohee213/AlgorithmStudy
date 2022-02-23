package beakjoon.d220223.q2580;
//https://www.acmicpc.net/problem/2580
//sovled : long ago...

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[][] sudoku = new int[9][9];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for(int i=0; i<9; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) sudoku[i][j] = Integer.parseInt(st.nextToken());
        }

        int[] now = next_zero(0, -1);
        make_sudoku(now[0], now[1]);

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                sb.append(sudoku[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static boolean make_sudoku(int now_row, int now_col){
        if(now_row==9) return true;                             //끝까지 들어왔음 정답이므로

        int[] next = next_zero(now_row, now_col);
        boolean[] chk = new boolean[10];                        //false면 가로, 세로, 네모에 없는 수
        chk_row(now_row, chk);                                  //배열
        chk_col(now_col, chk);                                  //수정
        chk_square(now_row, now_col, chk);                      //중..
        for(int i=1; i<=9; i++) {
            if(!chk[i]){                                        //없을때만
                sudoku[now_row][now_col] = i;                   //스도쿠에 대입하고
                if(make_sudoku(next[0], next[1])) return true;  //답이 나오면 걍 주루룩 나가기
                sudoku[now_row][now_col] = 0;                   //답 아니니 원상태 복귀
            }
        }
        return false;                                           //모두 확인했는데 안되면 위에서 수정
    }
    static void chk_row(int now_row, boolean[] chk){
        for(int i=0; i<9; i++) chk[sudoku[now_row][i]] = true;
    }

    static void chk_col(int now_col, boolean[] chk){
        for(int i=0; i<9; i++) chk[sudoku[i][now_col]] = true;
    }

    static void chk_square(int now_row, int now_col, boolean[] chk){
        now_col = (now_col/3)*3;
        now_row = (now_row/3)*3;
        for(int i=now_row; i<now_row+3; i++){
            for(int j=now_col; j<now_col+3; j++) chk[sudoku[i][j]] = true;
        }
    }

    static int[] next_zero(int now_row, int now_col){           //다음 빈칸 찾아서 반환
        for(int i = now_row; i<9; i++){
            if(i==now_row) {
                for (int j = now_col+1; j < 9; j++) if (sudoku[i][j] == 0) return new int[]{i,j};
            }else{
                for (int j = 0; j < 9; j++)if (sudoku[i][j] == 0) return new int[]{i,j};
            }
        }
        return new int[]{9,9};
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