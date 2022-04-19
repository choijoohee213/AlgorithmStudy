//https://www.acmicpc.net/problem/21608
//Solved : 22/04/15

import java.util.*;
import java.io.*;

class Main_21608 {
    static int N;
    static int[] dr = {-1,0,1,0},  dc = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        int size = (int) Math.pow(N, 2);

        ArrayList<Set<Integer>> like = new ArrayList<>();
        for(int i = 0; i <= size; i++){
            like.add(new HashSet<Integer>());
        }

        int idx;
        int[] order = new int[size];
        for(int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            idx = Integer.parseInt(st.nextToken());
            order[i] = idx;
            for(int j = 0; j < 4; j++) {
                like.get(idx).add(Integer.parseInt(st.nextToken()));
            }
        }

        int[][] sit = new int[N][N];

        int nr, nc, score = 0, maxS, ty = -1, tx = -1;
        for(idx = 0; idx < size; idx++) {
            maxS = -1;		// 인접한 좋은 학생이 0명일때를 위해 -1
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(sit[i][j] != 0) continue;

                    score = 0;
                    // 현재 좌석의 상 하 좌 우를 탐색하여 점수를 매긴다.
                    for(int k = 0; k < 4; k++) {
                        nr = i + dr[k];
                        nc = j + dc[k];
                        if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;    //배열범위 밖 스킵
                        // 해당하는 좌석에 선호하는 학생이 있으면
                        if(like.get(order[idx]).contains(sit[nr][nc]))
                            score += 5;
                        else if(sit[nr][nc] == 0)
                            score += 1;
                    }
                    // 해당 좌석의 점수가 높으면, 차후에 그 위치에 배치하도록 위치를 저장한다.
                    if(score > maxS) {
                        ty = i;
                        tx = j;
                        maxS = score;
                    }
                }
            }

            sit[ty][tx] = order[idx];
        }

        int result = 0, cnt;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                cnt = 0;

                for(int k = 0; k < 4; k++) {
                    nr = i + dr[k];
                    nc = j + dc[k];
                    if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    if(like.get(sit[i][j]).contains(sit[nr][nc])) {
                        // 각 좌석의 상하좌우에 선호하는 학생이 앉아 있으면
                        cnt++;
                    }
                }
                if(cnt != 0) result += (int) Math.pow(10, cnt - 1);
            }
        }

        System.out.println(result);

        br.close();
    }
}