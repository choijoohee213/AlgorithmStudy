//https://www.acmicpc.net/problem/15684
//Solved : 22/03/30

import java.util.*;
import java.io.*;

class Main{
    private static int N, H;
    private static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H + 1][N];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            map[a][b] = 1;
            map[a][b + 1] = -1;
        }

        if (over() > 3) {
            System.out.println("-1");
            return;
        } else {
            for (int i = 0; i <= 3; i++)
                if (dfs(0, 0, 0, i))
                    return;
        }
        System.out.println("-1");
    }

    private static boolean dfs(int x, int y, int cnt, int size) {
        if (cnt == size) {
            if (ladderCheck()) {
                System.out.println(size);
                return true;
            }
            return false;
        }

        for (int i = x; i < H; i++) {
            for (int j = y; j < N - 1; j++) {
                if (map[i][j] != 0 || map[i][j + 1] != 0) continue;

                map[i][j] = 1;
                map[i][j + 1] = -1;
                if (dfs(i, j + 2, cnt + 1, size)) return true;
                map[i][j] = 0;
                map[i][j + 1] = 0;
            }
            y = 0;
        }
        return false;
    }

    private static boolean ladderCheck() {
        for (int j = 0; j < N; j++) {
            int nx = 0, ny = j;

            while (nx <= H) {
                if (map[nx][ny] == 1) ny++;                 //1이면 우측
                else if (map[nx][ny] == -1) ny--;           //-1이면 좌측
                nx++;
            }
            if (ny != j) return false;
        }
        return true;
    }

    private static int over() {
        int num = 0;
        for (int j = 0; j < N - 1; j++) {
            int t = 0;
            for (int i = 0; i < H; i++)
                if (map[i][j] == 1) t++;
            if (t % 2 == 1) num++;
        }
        return num;
    }
}