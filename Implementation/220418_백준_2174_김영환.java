package com.younghwani.a220418;

import java.io.*;
import java.util.*;

public class Main_bj_2174_로봇시뮬레이션 {
    static class Robot {
        int r, c, d;
        public Robot(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
        public void changeDir(char dir) {
            if(dir=='L') { // 왼쪽으로 90도 회전
                this.d = d==0 ? 3 : d-1;
            } else if(dir=='R') { // 오른쪽으로 90도 회전
                this.d = d==3 ? 0 : d+1;
            }
        }
    }
    static int A, B, N, M, map[][], di[]={1, 0, -1, 0}, dj[]={0, 1, 0, -1};
    static Robot[] robots;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        // 가로, 세로
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        // 로봇, 명령
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[B+1][A+1];
        robots = new Robot[N+1];

        // N개의 로봇 초기 위치
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            String tmp = st.nextToken();
            int d = 0;
            if(tmp.equals("N")) d = 0;
            else if(tmp.equals("E")) d = 1;
            else if(tmp.equals("S")) d = 2;
            else if(tmp.equals("W")) d = 3;
            map[r][c] = i; // 맵에 로봇 표시
            robots[i] = new Robot(r, c, d); // 로봇 배열에 로봇 정보 추가
        }

        // M개의 명령
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            // 로봇, 명령, 반복횟수
            if(simul(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0), Integer.parseInt(st.nextToken()))) {
                return;
            }
        }
        System.out.print("OK"); // 잘 실행되었으면
        br.close();
    }

    static boolean simul(int num, char cmd, int cnt) {
        for (int i = 0; i < cnt; i++) {
            Robot robot = robots[num]; // 명령 수행할 로봇 번호
            if(cmd=='L' || cmd=='R') robot.changeDir(cmd);
            else if(cmd=='F') { // 방향대로 한 칸 앞으로 이동한다.
                int ni = robot.r + di[robot.d], nj = robot.c + dj[robot.d];
                if(ni<1||ni>B||nj<1||nj>A) { // 범위를 벗어난 경우
                    System.out.print("Robot "+num+" crashes into the wall");
                    return true;
                } else { // 범위 안일 때
                    if(map[ni][nj] != 0) { // 로봇이 서로 만난 경우
                        System.out.print("Robot "+num +" crashes into robot " + map[ni][nj]);
                        return true;
                    } else { // 빈칸인 경우 - 이동 가능
                        map[robot.r][robot.c] = 0;
                        map[ni][nj] = num;
                        robots[num] = new Robot(ni, nj, robot.d);
                    }
                }
            }
        }

        return false;
    }
}
