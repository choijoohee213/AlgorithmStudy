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
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<=N-K; i++){
            int rst = 0;
            for(int j=0; j<K; j++){
                rst += arr[i+j];
            }


            System.out.println(rst);
            max = max > rst ? max : rst;
        }
        System.out.println();
        bw.write(Integer.toString(max));
        bw.close();
        br.close();
    }
}
