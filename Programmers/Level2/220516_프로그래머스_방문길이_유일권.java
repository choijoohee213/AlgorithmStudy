//Solved :

import java.util.*;

class Solution01{
    static int[] dr = {1, 0, -1, 0}, dc = {0, 1, 0, -1};
    public static void main(String[] args){
        //Way
        System.out.println(solution("ULURRDLLU"));
        System.out.println(solution("LULLLLLLU"));
    }
    public static int solution(String dirs) {
        int r = 0, c = 0;
        List<Way> list = new ArrayList<>();
        int nr, nc;
        for(int i=0; i< dirs.length(); i++){
            char dir = dirs.charAt(i);
            int idx = 0;
            if(dir == 'L') idx = 3;
            else if(dir == 'R') idx = 1;
            else if(dir == 'D') idx = 2;
            nr = r + dr[idx];
            nc = c + dc[idx];
            boolean flag = true;
            Way now = new Way(r,c,nr,nc);
            if(!isIn(nr,nc)) continue;
            for (Way way : list) {
                if (way.isSame(now)) {
                    flag = false;
                    break;
                }
            }
            if(flag){
                list.add(now);
            }
            r = nr;
            c = nc;
        }
        return list.size();
    }
    static boolean isIn(int nr, int nc){
        return (nr>=-5 && nr<=5 && nc>=-5 && nc<=5);
    }
    static class Way{
        int r1, c1, r2, c2;

        public Way(int r1, int c1, int r2, int c2) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
        }
        public boolean isSame(Way C){
            return (this.r1==C.r1 && this.c1==C.c1 && this.r2==C.r2 && this.c2==C.c2)
                    || (this.r1==C.r2 && this.c1==C.c2 && this.r2==C.r1 && this.c2==C.c1);
        }
    }
}