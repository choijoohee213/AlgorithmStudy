//https://www.acmicpc.net/problem/6087
//Solved : 22/03/13

import java.io.*;
import java.util.*;

class Main {
    static int w, h;
    static char[][] map;        //지도
    static int[][] cnt;         //전환 횟수
    static int[] dx = {-1, 1, 0, 0};    // 상하좌우
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new char[h][w];
        cnt = new int[h][w];
        for (int[] t : cnt) {
            Arrays.fill(t, Integer.MAX_VALUE);
        }

        boolean flag = false;
        int cx = 0, cy = 0, ex = 0, ey = 0;

        // map 과 C 의 위치 파악
        for (int i = 0; i < h; i++) {
            String s = br.readLine();
            for (int j = 0; j < w; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'C') {
                    if (!flag) {
                        cx = i;
                        cy = j;
                        flag = true;
                    } else {
                        ex = i;
                        ey = j;
                    }
                }
            }
        }

        int ans = bfs(cx, cy, ex, ey);
        bw.write(Integer.toString(ans));
        bw.close();
        br.close();
    }

    static int bfs(int cx, int cy, int ex, int ey) {
        PriorityQueue<Point> pq = new PriorityQueue<>();    // 거울 개수 작은 순 우선
        pq.offer(new Point(cx, cy, 0, -1));

        while (!pq.isEmpty()) {
            Point cur = pq.poll();  // 거울 사용이 적은 것부터 뽑기

            // 도착했으면 리턴
            if (cur.x == ex && cur.y == ey) {
                return cur.mirror;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (inRange(nx, ny) && map[nx][ny] != '*') {    // 범위 안쪽이며, 벽이 아니면
                    // 내가 여기까지 사용한 거울의 수가, 기존에 (nx, ny)에 도착하기까지 사용한 거울의 수보다 크면 더이상 탐색이 무의미
                    if (i == cur.direction || cur.direction == -1) {    // 방향이 같으면 거울개수 유지
                        if (cur.mirror <= cnt[nx][ny]) {
                            cnt[nx][ny] = cur.mirror;
                            pq.offer(new Point(nx, ny, cur.mirror, i));
                        }
                    } else {
                        if (cur.mirror + 1 <= cnt[nx][ny]) {    // 방향이 다르면 거울개수 추가
                            cnt[nx][ny] = cur.mirror + 1;
                            pq.offer(new Point(nx, ny, cur.mirror + 1, i));
                        }
                    }
                }
            }
        }
        return 0;
    }

    // 범위 체크
    static boolean inRange(int x, int y) {
        if (x >= 0 && x < h && y >= 0 && y < w) {
            return true;
        }
        return false;
    }

    static class Point implements Comparable<Point>{
        int x, y, mirror, direction;

        Point(int x, int y, int mirror, int direction) {
            this.x = x;
            this.y = y;
            this.mirror = mirror;
            this.direction = direction;
        }

        @Override
        public int compareTo(Point o) {
            return this.mirror - o.mirror;
        }
    }
}