package com.younghwani.a220516;

public class Solution_pro_방문길이 {
    public static void main(String[] args) {
        System.out.println(solution("ULURRDLLU"));
        System.out.println(solution("LULLLLLLU"));
    }

    static int solution(String dirs) {
        int answer = 0;
        int[] di = {-1, 0, 1, 0}, dj = {0, 1, 0, -1};
        boolean[][][][] V = new boolean[11][11][11][11]; // from-(r,c), to-(r,c)
        int r = 5, c = 5;
        char[] cmds = dirs.toCharArray();
        for (int i = 0; i < cmds.length; i++) {
            char cmd = cmds[i];
            int ni = r, nj = c;
            if (cmd == 'U') { // 상
                ni += di[0];
                nj += dj[0];
            } else if (cmd == 'R') { // 우
                ni += di[1];
                nj += dj[1];
            } else if (cmd == 'D') { // 하
                ni += di[2];
                nj += dj[2];
            } else if (cmd == 'L') { // 좌
                ni += di[3];
                nj += dj[3];
            }
            if(ni<0||ni>=11||nj<0||nj>=11) continue;
            if(!V[r][c][ni][nj]&&!V[ni][nj][r][c]) {
                V[r][c][ni][nj] = true;
                V[ni][nj][r][c] = true;
                answer++;
            }
            r = ni;
            c = nj;
        }
        return answer;
    }
}
