//https://www.acmicpc.net/problem/2174
//Solved : 22/04/18

import java.util.*;
import java.io.*;

class Main_2174 {
    static class Robot{
        int r,c,d;
        public Robot(int r, int c, int d) {
            super();
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    static int A,B,N,M;
    static int[][] map;
    static int[] dr = {-1,0,1,0},  dc = {0,-1,0,1}; // 상 좌 하 우
    static List<Robot> robots = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb= new StringBuilder();

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        map = new int[B][A];
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        robots.add(new Robot(0, 0, 0)); // 인덱스 맞추기

        for(int n=1; n<=N; n++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken()) - 1;
            int r = B - Integer.parseInt(st.nextToken()) ;
            int d = 0;
//            int d = switch (st.nextToken().charAt(0)) {
//                case 'W' -> 1;  // 좌
//                case 'S' -> 2;  // 하
//                case 'E' -> 3;  // 우
//                default -> 0;   // 상
//            };
            char tmp = st.nextToken().charAt(0);
            if(tmp=='W') d=1;
            else if(tmp=='S') d=2;
            else if(tmp=='E') d=3;

            robots.add(new Robot(r, c, d));
            map[r][c] = n;
        }
        boolean flag = false;
        loop : for(int m=0; m<M; m++) {
            st = new StringTokenizer(br.readLine());
            int no = Integer.parseInt(st.nextToken());
            char cmd = st.nextToken().charAt(0);
            int repeat = Integer.parseInt(st.nextToken());

            if(cmd == 'L') {
                repeat = repeat % 4;
                int nd = robots.get(no).d;
                for(int i=0; i<repeat; i++) {
                    nd = (nd+1) % 4;
                }
                robots.get(no).d = nd;
            }else if(cmd == 'R') {
                repeat = repeat % 4;
                int nd = robots.get(no).d;
                for(int i=0; i<repeat; i++) {
                    nd = (nd+3) % 4;
                }
                robots.get(no).d = nd;
            }else {
                int nr = robots.get(no).r;
                int nc = robots.get(no).c;
                int d = robots.get(no).d;
                for(int i=0; i<repeat; i++) {
                    nr = nr + dr[d];
                    nc = nc + dc[d];
                    if(nr<0 || nc<0 || nr>=B || nc>=A) { // 맵 밖 탈출
                        sb.append("Robot ").append(no).append(" crashes into the wall");
                        flag = true;
                        break loop;
                    }
                    if(map[nr][nc] != 0) { // 다른 로봇과 부딪침
                        sb.append("Robot ").append(no).append(" crashes into robot ").append(map[nr][nc]);
                        flag = true;
                        break loop;
                    }
                }
                // 이동에 문제 없으면 맵, 로봇 수정
                map[robots.get(no).r][robots.get(no).c] = 0;
                map[nr][nc] = no;
                robots.get(no).r = nr;
                robots.get(no).c = nc;
            }
        }
        if(!flag) {
            sb.append("OK");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}