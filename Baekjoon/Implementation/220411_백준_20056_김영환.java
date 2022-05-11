package com.younghwani.a220411;

import java.io.*;
import java.util.*;

public class Main_bj_20056_마법사상어와파이어볼 {
    static class FireBall {
        int r, c, m, s, d;
        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    static int[] di = {-1, -1, 0, 1, 1, 1, 0, -1}, dj = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 파이어볼 수
        int K = Integer.parseInt(st.nextToken()); // 명령 수

        List<FireBall> list = new ArrayList<>(); // 현재 파이어볼 정보
        Queue<FireBall>[][] map = new ArrayDeque[N][N]; // N*N 맵
        for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) map[i][j] = new ArrayDeque<>(); // 큐 초기화

        // 파이어볼 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) -1;
            int c = Integer.parseInt(st.nextToken()) -1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list.add(new FireBall(r, c, m, s, d));
        }

        while (K-- > 0) {
            // 파이어볼 이동
            for (FireBall f : list) {
                f.r = (N + f.r + di[f.d] * (f.s % N)) % N; // 방향 * 속도
                f.c = (N + f.c + dj[f.d] * (f.s % N)) % N; // 방향 * 속도
                map[f.r][f.c].add(f);
            }

            // 합치기 및 나누기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j].size() >= 2) { // 파이어볼 2개 이상일 때
                        int mass=0, speed=0, cnt=map[i][j].size(); // 질량, 속도의 합, 파이어볼 개수
                        boolean isOdd=true, isEven=true; // 모두 홀수 혹은 짝수인지 여부

                        // 합치기
                        while (!map[i][j].isEmpty()) {
                            FireBall f = map[i][j].poll();
                            // 질량, 속도의 합
                            mass += f.m;
                            speed += f.s;
                            // 홀수, 짝수 여부 판단
                            if (f.d % 2 == 0) isOdd = false;
                            else isEven = false;
                            // 파이어볼 비교 완료 - 리스트에서 삭제
                            list.remove(f);
                        }

                        // 나누기
                        int nm; // 새로운 질량
                        if((nm=mass/5) == 0) continue;

                        int ns = speed / cnt; // 새로운 속력

                        // 리스트에 추가 - isOdd, isEven 결과에 따른 방향 고려
                        if(isOdd || isEven) {
                            for (int k = 0; k <= 6; k+=2) list.add(new FireBall(i, j, nm, ns, k)); // 0, 2, 4, 6
                        } else {
                            for (int k = 1; k <= 7; k+=2) list.add(new FireBall(i, j, nm, ns, k)); // 1, 3, 5, 7
                        }

                        continue;
                    }
                    map[i][j].clear();
                }
            }
        }

        int res=0;
        for (FireBall f : list) res += f.m;
        System.out.print(res);
        br.close();
    }
}
