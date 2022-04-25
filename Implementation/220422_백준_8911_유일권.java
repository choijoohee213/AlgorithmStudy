//https://www.acmicpc.net/problem/8911
//Solved : 22/04/23

import java.util.*;
import java.io.*;

class Main_8911{
    static int[] dr = {-1,0,1,0}, dc = {0, 1, 0, -1};
    static int[] row, col, point;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        char[] str;
        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            str = br.readLine().toCharArray();
            row = new int[2];
            col = new int[2];
            point = new int[2];
            int dir = 0;
            for (char c : str) {
                if (c == 'F') {
                    point[0] += dr[dir];
                    point[1] += dc[dir];
                    chk();
                } else if (c == 'B') {
                    point[0] -= dr[dir];
                    point[1] -= dc[dir];
                    chk();
                } else if (c == 'R') {
                    dir = (dir + 1) % 4;
                } else {
                    dir = dir - 1 < 0 ? 3 : dir - 1;
                }
            }

            int rst = (row[1]-row[0]) * (col[1]-col[0]);
            sb.append(Integer.toString(rst)).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void chk() {
        row[0] = row[0] < point[0] ? row[0] : point[0];
        row[1] = row[1] > point[0] ? row[1] : point[0];
        col[0] = col[0] < point[1] ? col[0] : point[1];
        col[1] = col[1] > point[1] ? col[1] : point[1];
    }
}
/*
3
FFLF
FFRRFF
FFFBBBRFFFBBB

 */