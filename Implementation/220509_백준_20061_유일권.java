//https://www.acmicpc.net/problem/20061
//Solved : 22/05/10

import java.util.*;
import java.io.*;

class Main{
    static int[][][] map = new int[2][6][4];
    static int rst = 0;
    static int[] dr={-1,0,1,0}, dc={0,-1,0,1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            move(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        sb.append(rst).append("\n").append(findBlock());
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    static void move(int type, int r, int c){
        for(int color = 0; color<2; color++){
            int nr = 0;
            if(type==3) nr++;
            while (nr<5){
                if(map[color][nr+1][c]!=0 || (type==2 && map[color][nr+1][c+1]!=0)) break;
                nr++;
            }

            map[color][nr][c] = 1;
            if(type==2) map[color][nr][c+1]=1;
            else if(type==3) map[color][nr-1][c]=1;

            chk_score(color);
            remove(color);

            if(color==0){
                nr = c;
                int nc = 3-r;
                if(type == 2){
                    type = 3;
                }else if(type==3) {
                    nc--;
                    type = 2;
                }
                r = nr;
                c = nc;
            }
        }
    }
    static void chk_score(int color){
        for(int i=5; i>1; i--){
            boolean flag = true;
            for(int j=0; j<4; j++){
                if(map[color][i][j] != 1){
                    flag = false;
                    break;
                }
            }
            if(flag){
                rst++;
                for(int k=i; k>0; k--){
                    map[color][k] = map[color][k-1].clone();
                }
                i++;
            }
        }
    }
    static void remove(int color){
        int lines = 0;
        for(int i=0; i<2; i++){
            for(int j=0; j<4; j++){
                if(map[color][i][j] == 1){
                    lines++;
                    break;
                }
            }
        }
        for(int i=5; i>=lines; i--) {
            map[color][i] = map[color][i - lines].clone();
        }
        for(int i=0; i<2; i++){
            for(int j=0; j<4; j++){
                map[color][i][j] = 0;
            }
        }
    }
    static int findBlock(){
        int sum=0;
        for(int c=0; c<2; c++){
            for(int i=1; i<6; i++){
                for(int j=0; j<4; j++){
                    if(map[c][i][j] == 1) sum++;
                }
            }
        }
        return sum;
    }
    static void print(){
        for(int i=0; i<6; i++){
            for(int c=0; c<2; c++){
                for(int j=0; j<4; j++){
                    System.out.print(map[c][i][j]+" ");
                }
                System.out.print("  ");
            }
            System.out.println();
        }
    }
}