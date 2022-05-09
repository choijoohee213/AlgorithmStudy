//https://www.acmicpc.net/problem/5212
//Solved : 22/05/09

import java.io.*;
import java.util.*;

class Main_5212 {
    static class Node{
        int y,x;
        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    static int R, C;
    static int[] dy = {-1,1,0,0}, dx= {0,0,-1,1};
    static char[][] map;
    static boolean[][] visit;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visit = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        check();
        bw.write(print());
        bw.close();
        br.close();
    }
    private static String print() {
        StringBuilder sb = new StringBuilder();
        int minR = 10, maxR = 0, minC = 10, maxC = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 'X') {
                    minR = minR < i ? minR : i;
                    maxR = maxR > i ? maxR : i;
                    minC = minC < j ? minC : j;
                    maxC = maxC > j ? maxC : j;
                }
            }
        }
        for (int i = minR; i <=maxR; i++) {
            for (int j = minC; j <=maxC; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static void check() { // 육지에 대해서 4방탐색해서 바다 체크 배열 범위 벗어난곳도 바다
        Queue<Node> q = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 'X') {
                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        int ny = i + dy[k];
                        int nx = j + dx[k];
                        if(ny < 0 || nx < 0 || ny>= R || nx >= C) {
                            count++;
                            continue;
                        }
                        if(map[ny][nx] =='X')
                            continue;
                        count++;
                    }
                    if(count >= 3)
                        q.offer(new Node(i,j));
                }
            }
        }
        while(!q.isEmpty()) {
            Node n = q.poll();
            map[n.y][n.x] = '.';
        }
    }
}