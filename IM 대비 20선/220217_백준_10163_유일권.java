package beakjoon.d220218.q10163;
//https://www.acmicpc.net/problem/10163
//Solved : 22/02/18
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int[][] map = new int[1002][1002];          //모든 맵

        int N = Integer.parseInt(br.readLine());    //색종이 수
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            for(int j=r; j<r+w; j++){
                for(int k=c; k<c+h; k++){
                    map[j][k]=i;                        //색종이 위치만큼 숫자 변경
                }
            }
        }

        for(int i=1; i<=N; i++){                        //갯수만큼 모든 맵 돌면서 확인
            int rst=0;
            for(int[] j: map){
                for(int k:j) {
                    if (k == i) {
                        rst++;
                    }
                }
            }
            sb.append(rst).append(" ");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
