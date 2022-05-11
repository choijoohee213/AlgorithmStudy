//https://www.acmicpc.net/problem/14891
//Solved : 22/03/28

import java.io.*;
import java.util.*;

class Main{
    static int[][] wheel = new int[4][8];   //전체 바퀴 숫자 저장
    static int[] isValid; // 회전하는 방향 저장 (0이면 정지, 1이면 시계, -1이면 반시계)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        for (int i = 0; i < 4; i++) {
            char[] str = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) wheel[i][j] = str[j]-'0';
        }

        int round = Integer.parseInt(br.readLine());

        for (int i = 0; i < round; i++) {
            st = new StringTokenizer(br.readLine());
            isValid = new int[4];                       //새로 선언 안할시 이전 값 땜에 오류

            int wheelNum = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            check(wheelNum, dir);
            rotate();
        }

        bw.write(Integer.toString(calc()));
        bw.close();
        br.close();
    }

    static int calc() {             //결과 계산
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            int num = wheel[i][0];

            if (num == 1) {
                sum += Math.pow(2, i);
            }
        }
        return sum;
    }

    static void check(int wheelNum, int dir) {      //양 옆 확인후 회전시킬지 여부 검사
        isValid[wheelNum] = dir;

        int prev = wheelNum - 1;
        int next = wheelNum + 1;

        if (prev >= 0 && isValid[prev] == 0) {
            // 왼쪽 검사
            if (wheel[prev][2] != wheel[wheelNum][6]) check(prev, dir * -1);
        }

        if (next <= 3 && isValid[next] == 0) {
            //오른쪽 바퀴 검사
            if (wheel[next][6] != wheel[wheelNum][2]) check(next, dir * -1);
        }
    }

    static void rotate() {     //방향에 따라 회전시키는 함수
        for (int i = 0; i < 4; i++) {
            if (isValid[i] != 0) {
                int[] temp = new int[8];

                int idx;
                for (int j = 0; j < 8; j++) {
                    idx = j + isValid[i];

                    if (idx == -1)  idx = 7;
                    else if (idx == 8) idx = 0;

                    temp[idx] = wheel[i][j];
                }
                wheel[i] = temp;
            }
        }
    }
}