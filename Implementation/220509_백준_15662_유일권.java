//https://www.acmicpc.net/problem/15662
//Solved : 22/05/09

import java.util.*;
import java.io.*;

class Main_15662 {
    static int[][] mag;
    static int[] dirs; // 자석의 회전하는 방향
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        mag = new int[T][8];
        for (int i = 0; i < T; i++) {
            char[] str = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                mag[i][j] = str[j]-'0';
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            dirs = new int[T];

            check(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
            rotate();
        }

        int rst = 0;
        for (int i = 0; i < T; i++) {
            rst += mag[i][0];
        }

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }

    static void check(int wheelNum, int dir) {
        dirs[wheelNum] = dir;

        int prev = wheelNum - 1;
        int next = wheelNum + 1;

        if (prev >= 0 && dirs[prev] == 0) {
            // 왼쪽 검사
            if (mag[prev][2] != mag[wheelNum][6]) {
                check(prev, dir * -1);
            }
        }

        if (next < T && dirs[next] == 0) {
            //오른쪽 검사
            if (mag[next][6] != mag[wheelNum][2]) {
                check(next, dir * -1);
            }
        }
    }

    static void rotate() {
        for (int i = 0; i < T; i++) {
            if (dirs[i] != 0) {
                int[] tmp = new int[8];

                int idx;
                for (int j = 0; j < 8; j++) {
                    idx = j + dirs[i];

                    if (idx == -1) idx = 7;
                    else if (idx == 8) idx = 0;

                    tmp[idx] = mag[i][j];
                }
                mag[i] = tmp;
            }
        }
    }
}