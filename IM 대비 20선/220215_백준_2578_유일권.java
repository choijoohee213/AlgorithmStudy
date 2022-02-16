package d220213.q2578;
//https://www.acmicpc.net/problem/2578
//solved : 22/02/13
import java.util.*;
import java.io.*;

public class Main {
    static int[][] bingo;
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bingo = new int[5][5];                                  //빙고 배열
        StringTokenizer st;
        int rst=0;

        for(int i=0; i<5; i++){                                 //입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++){
                bingo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        loop:for(int i=0; i<5; i++){                            //빙고 확인
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++){
                int tmp = Integer.parseInt(st.nextToken());
                if(chk_bingo(tmp)){
                    rst = i*5+j+1;
                    break loop;
                }
            }
        }

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }

    static boolean chk_bingo(int num){          //빙고 갯수 확인 함수
        int r=0, c=0;
        loop:for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(bingo[i][j]==num){           //사회자가 불러준 숫자 위치 확인
                    r=i;
                    c=j;
                    bingo[i][j] = 0;
                    break loop;
                }
            }
        }
        rst = rst + chk_row(r) + chk_col(c) + chk_diag(r, c);   //체크한 숫자에 따라 빙고 갯수 더해주기
        return rst >= 3;                                        //3빙고 이상이면 true, 아니면 false
    }
    static int chk_row(int row){                                //입력받은 위치에서 가로 확인
        for(int i=0; i<5; i++){
            if(bingo[row][i] != 0) return 0;
        }
        return 1;
    }
    static int chk_col(int col){                                //입력받은 위치에서 세로 확인
        for(int i=0; i<5; i++){
            if(bingo[i][col] != 0) return 0;
        }
        return 1;
    }
    static int chk_diag(int row, int col){                      //대각선확인
        int rst = 0;
        if(row-col==0){                                         //우측 아래로 내려가는 대각선인경우
            rst++;
            for(int i =0; i<5; i++){
                if(bingo[i][i]!=0){
                    rst--;
                    break;
                }
            }
        }if(row+col==4){                                        //좌측 상단으로 가는 대각선인경우
            rst++;
            for(int i =0; i<5; i++){
                if(bingo[i][4-i]!=0){
                    rst--;
                    break;
                }
            }
        }if(row+col!=4&&row-col!=0){                            //둘다 아닌경우
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
        final int BOARD_SIZE = 5;                                       //총 가로세로 크기
        StringTokenizer st;
        for(int i = 0; i < BOARD_SIZE; i++){
            st = new StringTokenizer(input.readLine());
            for(int j = 0; j < BOARD_SIZE; j++){
                board.put(st.nextToken(), new int[]{i, j});             //빙고에 적혀있는 수에 따라서 위치를 맵에 저장
            }
        }

        int[] rowCnt = new int[BOARD_SIZE];                             //가로에 숫자가 5개가 되면 1빙고이므로 정수배열
        int[] colCnt = new int[BOARD_SIZE];                             //세로에 숫자가 5개가 되면 1빙고이므로 정수배열
        int leftCross = 0;                                              //대각선은 하나만 가능하므로 정수
        int rightCross = 0;

        int ans = 0;
        int bingo = 0;
        out: for(int i = 0; i < BOARD_SIZE; i++){
            st = new StringTokenizer(input.readLine());
            for(int j = 0; j < BOARD_SIZE; j++){
                int[] loc = board.get(st.nextToken());                  //맵에서 사회자의 숫자에 대한 위치 가져오기
                if(++rowCnt[loc[0]] == BOARD_SIZE)                      //가로 세로 값 더해서 빙고인지 확인
                    bingo++;
                if(++colCnt[loc[1]] == BOARD_SIZE)
                    bingo++;
                if(loc[0] + loc[1] == BOARD_SIZE-1){                    //오른쪽 대각선에 포함되면
                    if(++rightCross == BOARD_SIZE)
                        bingo++;
                }
                if(loc[0] == loc[1]){                                   //왼쪽 대각선에 포함되면
                    if(++leftCross == BOARD_SIZE)
                        bingo++;
                }
                if(bingo >= 3){                                         //만약 빙고면 값 출력
                    ans = j + BOARD_SIZE * i + 1;
                    break out;
                }
            }
        }

        System.out.println(ans);
    }
}

 */