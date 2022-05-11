package beakjoon.d220209.q6603;
//https://www.acmicpc.net/problem/6603
//Solved : 22/02/09

import java.util.*;
import java.io.*;

public class Main {
    static int[] arr;
    static int[] picked;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            if(a==0) break;
            arr = new int[a];
            picked = new int[6];
            for(int i=0; i<a; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            mk_Arr(0,0);
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    public static void mk_Arr(int num, int point){
        if(num==6){
            for(int i : picked){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        if(6-num > arr.length-point) return;
        for(int i=0; i<arr.length-point; i++){
            picked[num] = arr[point+i];
            mk_Arr(num+1, point+1+i);
            picked[num] = 0;
        }
    }
}
