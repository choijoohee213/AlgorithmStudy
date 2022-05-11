//https://www.acmicpc.net/problem/20057
//Solved : 22/04/15

import java.util.*;
import java.io.*;

class Main {
    static int[][] dsx = {{-1,1,-2,-1,1,2,-1,1,0}, {-1,-1,0,0,0,0,1,1,2},    // 모래가 퍼지는 x방향
            {1,-1,2,1,-1,-2,1,-1,0}, {1,1,0,0,0,0,-1,-1,-2}};
    static int[][] dsy = {{1,1,0,0,0,0,-1,-1,-2},{-1,1,-2,-1,1,2,-1,1,0},    // 모래가 퍼지는 y방향
            {-1,-1,0,0,0,0,1,1,2},{1,-1,2,1,-1,-2,1,-1,0}};
    static int[] sand = {1,1,2,7,7,2,10,10,5};								 // 모래 양
    static int[] dr = {0, 1, 0, -1}, dc = {-1, 0, 1, 0};
    static int[][] map;
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for(int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < n ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int r = n/2;
        int c = n/2;
        int out = 0;		//밖으로 나가는 모래 양
        int cnt = 1;		//지금 보고있는 방향으로 가는 횟수

        loop:while(true) {
            for(int d = 0 ; d < 4 ; d++) {

                if(d == 2) cnt++; //가로방향마다 가는 거리 늘어나므로 +1

                for(int i = 0 ; i < cnt ; i++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(nr < 0 || nc < 0 || nr >= n || nc >= n) break loop;

                    int spreadSand = 0;				// 이동한 총 모래의 양
                    for(int j = 0 ; j < 9 ; j++) {
                        int nsr = nr + dsx[d][j];
                        int nsc = nc + dsy[d][j];
                        int moved = (map[nr][nc] * sand[j]) / 100;
                        spreadSand += moved;
                        // 날아간 모래가 밖이면 결과에, 안이면 해당 구역에 더한다
                        if(nsr < 0 || nsc < 0 || nsr >= n || nsc >= n) out += moved;
                        else map[nsr][nsc] += moved;
                    }
                    // 알파 계산
                    int nar = nr + dr[d];
                    int nac = nc + dc[d];

                    if(nar < 0 || nac < 0 || nar >= n || nac >= n) out += (map[nr][nc] - spreadSand);
                    else map[nar][nac] += map[nr][nc] - spreadSand;

                    map[nr][nc] = 0;
                    r = nr;
                    c = nc;
                }
            }
            cnt++;
        }
        bw.write(Integer.toString(out));
        bw.close();
        br.close();
    }

}