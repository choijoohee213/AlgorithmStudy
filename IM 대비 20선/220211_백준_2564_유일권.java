package beakjoon.d220211.q2564;
//https://www.acmicpc.net/problem/2468
//Solved : 22/02/11

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(br.readLine());
        int round = R*2+C*2;
        int[] stores = new int[N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            if(dir==1){
                stores[i] = Integer.parseInt(st.nextToken());
            }else if(dir==4){
                stores[i] = R + Integer.parseInt(st.nextToken());
            }else if(dir==2){
                stores[i] = R*2 + C - Integer.parseInt(st.nextToken());
            }else{
                stores[i] = R*2 + C*2 - Integer.parseInt(st.nextToken());
            }
        }
        int now=0;
        st = new StringTokenizer(br.readLine());
        int dir = Integer.parseInt(st.nextToken());
        if(dir==1){
            now = Integer.parseInt(st.nextToken());
        }else if(dir==4){
            now = R + Integer.parseInt(st.nextToken());
        }else if(dir==2){
            now = R*2 + C - Integer.parseInt(st.nextToken());
        }else{
            now = R*2 + C*2 - Integer.parseInt(st.nextToken());
        }

        int rst = 0;
        for(int i=0; i<N; i++){
            int dist = Math.abs(now-stores[i]);
            rst += dist < round-dist ? dist : round-dist;
            //System.out.println(rst);
        }
        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
}
