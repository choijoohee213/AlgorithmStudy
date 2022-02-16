package beakjoon.d220213.q2559;
//https://www.acmicpc.net/problem/2559
//Solved : 22/02/13

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int max = Integer.MIN_VALUE;
        int[] arr= new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());          //입력 받아서 배열에 정리
        }

        for(int i=0; i<=N-K; i++){
            int rst = 0;
            for(int j=0; j<K; j++){                             //연속되는 K일만큼 더해서
                rst += arr[i+j];
            }
            max = max > rst ? max : rst;                        //큰값 max에 저장
        }
        bw.write(Integer.toString(max));
        bw.close();
        br.close();
    }
}
