package beakjoon.d220223.q4574;
//https://www.acmicpc.net/problem/4574
//Solved : 22/02/23

import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[][] sudoku;
    static boolean[][] used;
    static int[] dx = new int[]{0,1};
    static int[] dy = new int[]{1,0};
    static boolean printed;
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("res/beakjoon/q4574.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T=0;
        StringTokenizer st;

        while(true) {
            int N = Integer.parseInt(br.readLine());
            if(N==0) break;                                     //숫자 0이 나올때까지 반복이므로 0들어오면 루프 탈출
            sb.append("Puzzle ").append(++T).append("\n");

            sudoku = new int[9][9];                             //스도쿠 배열, 사용한 도미도 종류 확인용 배열
            used = new boolean[10][10];
            printed = false;                                    //같은 값이 2번 이상 출력되길래 추가(원인 미상..)

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int num1 = Integer.parseInt(st.nextToken());
                String place1 = st.nextToken();
                int num2 = Integer.parseInt(st.nextToken());
                String place2 = st.nextToken();
                used[num1][num2] = true;
                used[num2][num1] = true;
                sudoku[place1.charAt(0) - 'A'][place1.charAt(1) - '1'] = num1;  //배열 9칸이라 -1
                sudoku[place2.charAt(0) - 'A'][place2.charAt(1) - '1'] = num2;  //22
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < 10; i++) {
                used[i][i] = true;          //같은 숫자는 도미노X
                String place1 = st.nextToken();
                sudoku[place1.charAt(0) - 'A'][place1.charAt(1) - '1'] = i;     //333
            }
            int[] tmp = next_place(0,-1);

            dfs(tmp[0],tmp[1]);
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void dfs(int r, int c){
        if(r==9 && c==9 && !printed){
            for(int[] arr:sudoku){
                for(int i:arr) sb.append(i);
                sb.append("\n");
            }
            printed = true;                 //왜 같은 값이 2번..? 도대체 왜..?
            return;
        }
        for(int i=0; i<2; i++){
            int nX = r+dx[i], nY=c+dy[i];                               //다음 위치
            if(nX>=9||nY>=9||sudoku[nX][nY]!=0) continue;               //스도쿠 밖이면 스킵
            for(int j=1; j<=9; j++){                                    //1-2~9-8까지 모두 확인
                for(int k=1; k<=9; k++){
                    if(used[j][k]||used[k][j]||!chk_num(j,r,c)||!chk_num(k,nX,nY)) continue;
                    //사용한 것, 숫자가 맞지 않는 것 스킵
                    used[j][k] = true;          //사용한거랑 회전 된것도 사용했다 체크
                    used[k][j] = true;
                    sudoku[r][c] = j;           //스도쿠에 대입
                    sudoku[nX][nY] = k;         //22
                    int[] next = next_place(r, c);
                    dfs(next[0], next[1]);
                    used[j][k] = false;         //원래 값으로 돌려놓기
                    used[k][j] = false;         //22
                    sudoku[r][c] = 0;           //333
                    sudoku[nX][nY] = 0;         //4444
                }
            }
        }
    }
    static int[] next_place(int r, int c){      //다음 0의 위치 찾아 배열로 반환, 없으면 {9,9}
        for(int i=r; i<9; i++){
            if(i==r){
                for(int j=c+1; j<9; j++) if(sudoku[i][j]==0) return new int[]{i, j};
            }else{
                for(int j=0; j<9; j++) if(sudoku[i][j]==0) return new int[]{i, j};
            }
        }
        return new int[]{9,9};
    }
    static boolean chk_num(int num, int r, int c){      //가로, 세로, 자기가 속한 네모의 숫자 확인
        for(int i=0; i<9; i++){
            if(sudoku[i][c]==num||sudoku[r][i]==num) return false;
        }
        r=(r/3)*3;
        c=(c/3)*3;
        for(int i=r; i<r+3; i++){
            for(int j=c; j<c+3; j++){
                if(sudoku[i][j]==num) return false;
            }
        }
        return true;
    }
}
