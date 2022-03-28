//https://www.acmicpc.net/problem/15685
//Solved : 22/03/28

import java.util.*;
import java.io.*;

class Main{
    static boolean[][] map = new boolean[101][101];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int rst = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());   // 방향
            int g = Integer.parseInt(st.nextToken());   // 세대

            dc(x, y, d, g);
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1])   rst++;
            }
        }

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }

    public static void dc(int x, int y, int d, int g) {
        List<Integer> list = new ArrayList<>();
        list.add(d);

        for (int i = 1; i <= g; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                list.add((list.get(j) + 1) % 4);        //반시계방향으로 돌려서 방향만 리스트에 추가
            }
        }

        map[y][x] = true;
        for (int i  : list) {       //리스트에 저장된 방향을 돌며 방문처리
            x += dx[i];
            y += dy[i];
            map[y][x] = true;
        }
    }
}