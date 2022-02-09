package beakjoon.q14888;
//https://www.acmicpc.net/problem/14888
//Solved : 22/02/08

import java.util.*;
import java.io.*;

public class Main {
    static int[] test;
    static int[] Oper = new int[4];
    static long Max = Long.MIN_VALUE;
    static long Min = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int num = Integer.parseInt(br.readLine());
        test = new int[num];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<num; i++){
            test[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++){
            Oper[i] = Integer.parseInt(st.nextToken());
        }
        mk_num(0, test[0]);
        sb.append(Max).append("\n").append(Min);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void mk_num(int time, long rst){
        if(time==test.length-1){
            Max = Max > rst ? Max : rst;
            Min = Min < rst ? Min : rst;
            return;
        }
        if(Oper[0]>0){
            Oper[0]--;
            mk_num(time+1, rst+test[time+1]);
            Oper[0]++;
        }
        if(Oper[1]>0){
            Oper[1]--;
            mk_num(time+1, rst-test[time+1]);
            Oper[1]++;
        }
        if(Oper[2]>0){
            Oper[2]--;
            mk_num(time+1, rst*test[time+1]);
            Oper[2]++;
        }
        if(Oper[3]>0){
            Oper[3]--;
            mk_num(time+1, rst/test[time+1]);
            Oper[3]++;
        }
    }
}
