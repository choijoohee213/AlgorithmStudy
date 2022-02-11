package com.younghwani.a220211;

import java.io.*;
import java.util.*;

public class Main_bj_2527_직사각형 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_2527.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 0; tc < 4; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] x1 = new int[2]; int[] y1 = new int[2]; // 첫번째 직사각형의 x, y
            int[] x2 = new int[2]; int[] y2 = new int[2]; // 두번째 직사각형의 x, y
            for (int i = 0; i < 2; i++) {
                x1[i] = Integer.parseInt(st.nextToken());
                y1[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < 2; i++) {
                x2[i] = Integer.parseInt(st.nextToken());
                y2[i] = Integer.parseInt(st.nextToken());
            }
            // 1번 직사각형 => (x1[0], y1[0]), (x1[1], y1[1])
            // 2번 직사각형 => (x2[0], y2[0]), (x2[1], y2[1])

            // 공통부분이 없음 - d => 모두 해당되지 않을 때(x축 또는 y축이 무조건 작거나 큰 경우)
            if ((x1[0] > x2[1]) || (x1[1] < x2[0]) || (y1[0] > y2[1]) || (y1[1] < y2[0])) {
                System.out.println('d');
            }
            // 점 - c => 두 직사각형의 양 끝점이 만날 때(점 1개만 일치할 경우)
            else if ((x1[0] == x2[1] && y1[0] == y2[1]) || (x1[1] == x2[0] && y1[1] == y2[0])
                    || (x1[0] == x2[1] && y1[1] == y2[0]) || (x1[1] == x2[0] && y1[0] == y2[1])) {
                System.out.println('c');
            }
            // 선분 - b => 한 라인은 겹치고, 한 라인은 안겹칠 때(아래 또는 위 x축 또는 y축이 각각 위 또는 아래 x축 또는 y축과 겹칠 때)
            else if ((x1[0] == x2[1]) || (x1[1] == x2[0]) || (y1[0] == y2[1]) || (y1[1] == y2[0])) {
                System.out.println('b');
            }
            // 직사각형 - a => 두 직사각형이 겹칠 때
            else System.out.println('a');
        }
    }
}



/* 100까지 잘 도는데 실패......
public class Main_bj_2527_직사각형 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_2527.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 0; tc < 4; tc++) {
            int x[] = new int[4]; int y[] = new int[4];     // x 좌표값, y 좌표값
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < 4; i++) {   // 좌표 입력받기
                x[i] = Integer.parseInt(st.nextToken());
                y[i] = Integer.parseInt(st.nextToken());
            }
            //System.out.println(Arrays.toString(x));
            //System.out.println(Arrays.toString(y));

            // 1번 직사각형 => (x[0], y[0]), (x[1], y[1])
            // 2번 직사각형 => (x[2], y[2]), (x[3], y[3])

            // 점 - c => 두 직사각형의 양 끝점이 만날 때
            if((x[0]==x[3]&&y[0]==y[3])||(x[0]==x[3]&&y[1]==y[2])||(x[1]==x[2]&&y[1]==y[2])||(x[1]==x[2]&&y[0]==y[3])){
                System.out.println("c");
            }
            // 선분 - b => 한 라인은 겹치고, 한 라인은 안겹칠 때
            else if((x[0]==x[3]&&y[1]!=y[2])||(x[1]==x[2]&&y[1]!=y[2])||(x[1]!=x[2]&&y[0]==y[3])||(x[0]!=x[3]&&y[0]==y[3])){
                System.out.println("b");
            }
            // 공통부분이 없음 - d => 모두 해당되지 않을 때
            else if(x[3]<x[0] || y[1]<y[2] || x[1]<x[2] || y[3]<y[0]){
                System.out.println("d");
            }
            // 직사각형 - a => 두 직사각형이 겹칠 때
            else {
                System.out.println("a");
            }
        }
        br.close();
    }
}
 */



/*
메모리 초과 오류 발생함.
겹치는 모든 조건을 계산 후 조건문으로 분기하는 방식으로 변경이 필요할 것 같음.
 */
/*
public class Main_bj_2527_직사각형 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_2527.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 0; tc < 4; tc++) {
            int x[] = new int[4]; int y[] = new int[4];     // x 좌표값, y 좌표값
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < 4; i++) {   // 좌표 입력받기
                x[i] = Integer.parseInt(st.nextToken());
                y[i] = Integer.parseInt(st.nextToken());
            }
            int[][] pan = new int[Arrays.stream(x).max().getAsInt()+1][Arrays.stream(y).max().getAsInt()+1];
            for (int i = 0; i < 2; i++) {   // 겹치는 부분 계산하기1(판에 입력)
                for (int h = x[2*i]; h <= x[2*i+1]; h++) {
                    for (int k = y[2*i]; k < y[2*i+1]; k++) {
                        pan[h][k]++;
                    }
                }
            }
            //System.out.println(Arrays.deepToString(pan));
            int width = 0; int height = 0;  // 겹치는 범위의 너비 및 높이
            loop:for (int i = 0; i < pan.length; i++) {  // 겹치는 부분 계산하기2(겹치는 범위 계산)
                for (int j = 0; j < pan[0].length; j++) {
                    if (pan[i][j]==2) { // 겹치는 첫 부분 찾음 -> 길이 너비 잰 후 빠져나오기
                        int row = i;
                        int col = j;
                        while(pan[i][col++]==2 && width<=1) width++;
                        while(pan[row++][j]==2 && height<=1) height++;
                        break loop;
                    }
                }
            }
            // a: 직사각형, b: 선분, c: 점, d: 공통부분 없음
            if (width == 0 && height == 0) {
                System.out.println("d");
            } else if (width > 1 && height > 1) {
                System.out.println("a");
            } else if (width == 1 && height == 1) {
                System.out.println("c");
            } else if (width == 1 || height == 1) {
                System.out.println("b");
            }
        }
        br.close();
    }
}
*/