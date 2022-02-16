package beakjoon.d220211.q2564;
//https://www.acmicpc.net/problem/2564
//Solved : 22/02/11

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());       //가로,세로 변수 설정
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(br.readLine());        //상점 수
        int round = R*2+C*2;                            //총 둘레
        int[] stores = new int[N];                      //상점 위치 배열

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            if(dir==1){                                 //북쪽인 경우
                stores[i] = Integer.parseInt(st.nextToken());
            }else if(dir==4){                           //동쪽인 경우
                stores[i] = R + Integer.parseInt(st.nextToken());
            }else if(dir==2){                           //남쪽인 경우
                stores[i] = R*2 + C - Integer.parseInt(st.nextToken());
            }else{                                      //서쪽인 경우
                stores[i] = R*2 + C*2 - Integer.parseInt(st.nextToken());
            }
        }
        int now=0;                                      //동근이의 위치 위 상점과 마찬가지
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
            int dist = Math.abs(now-stores[i]);             //둘의 차이가 길이
            rst += dist < round-dist ? dist : round-dist;   //둘중 더 작은 길이를 더해준다.
            //System.out.println(rst);
        }
        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
}
